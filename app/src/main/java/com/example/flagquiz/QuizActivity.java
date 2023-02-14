package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private TextView correctTotal, wrongTotal, emptyTotal,textViewQuestion;
    private ImageView flag, next;
    private Button a,b,c,d;

    private FlagsDatabase flagsDatabase;
    private ArrayList<FlagsModel> questionsArray;

    int correct =0, wrong =0, empty =0, questions = 0;

    private FlagsModel correctFlag;
    private ArrayList<FlagsModel> wrongOptionsList;

    HashSet<FlagsModel> mixOptions = new HashSet<>();
    ArrayList<FlagsModel> options = new ArrayList<>();

    boolean buttonControl = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        correctTotal = findViewById(R.id.textViewCorrect);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        wrongTotal = findViewById(R.id.textViewWrong);
        emptyTotal = findViewById(R.id.textViewEmpty);
        flag  = findViewById(R.id.imageViewFlag);
        next = findViewById(R.id.imageView2);
        a = findViewById(R.id.buttonA);
        b = findViewById(R.id.buttonB);
        c = findViewById(R.id.buttonC);
        d = findViewById(R.id.buttonD);

        flagsDatabase = new FlagsDatabase(QuizActivity.this);
        questionsArray = new FlagsDAO().getRandomTenQuestion(flagsDatabase);

        loadQuestions();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!buttonControl && questions <9){
                    empty++;
                    emptyTotal.setText("empty : "+ empty);
                    questions++;
                    loadQuestions();
                }else if(buttonControl && questions<9){


                    questions++;
                    loadQuestions();

                    a.setClickable(true);
                    b.setClickable(true);
                    c.setClickable(true);
                    d.setClickable(true);

                    a.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    b.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    c.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    d.setBackgroundColor(getResources().getColor(R.color.purple_500));
                }
                else if(questions == 9){
                    Intent i = new Intent(QuizActivity.this, ResultActivity.class);
                    i.putExtra("correct",correct);
                    i.putExtra("wring", wrong);
                    i.putExtra("empty", empty);
                    startActivity(i);
                    finish();
                }

                buttonControl = false;
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(a);

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(b);

            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answerControl(c);

            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answerControl(d);

            }
        });

    }
    public void loadQuestions(){

        textViewQuestion.setText("Question : "+(questions+1));

        correctFlag = questionsArray.get(questions);
        flag.setImageResource(getResources().getIdentifier(correctFlag.getFlag_image(),"drawable", getPackageName()));
        wrongOptionsList = new FlagsDAO().getRandomThreeOptions(flagsDatabase, correctFlag.getFlag());

        mixOptions.clear();
        mixOptions.add(correctFlag);
        mixOptions.add(wrongOptionsList.get(0));
        mixOptions.add(wrongOptionsList.get(1));
        mixOptions.add(wrongOptionsList.get(2));

        options.clear();
        for (FlagsModel flg : mixOptions){
            options.add(flg);
        }
        a.setText(options.get(0).getFlag_name());
        b.setText(options.get(1).getFlag_name());
        c.setText(options.get(2).getFlag_name());
        d.setText(options.get(3).getFlag_name());
    }

    public void answerControl( Button button){
        String buttonText = button.getText().toString();
        String correctAnswer = correctFlag.getFlag_name();
        if (buttonText.equals(correctAnswer)){
            correct++;
            button.setBackgroundColor(Color.GREEN);
        }
        else {
            wrong++;
            button.setBackgroundColor(Color.RED);

            if (a.getText().toString().equals(correctAnswer)){
                a.setBackgroundColor(Color.GREEN);
            }
            if (b.getText().toString().equals(correctAnswer)){
                b.setBackgroundColor(Color.GREEN);
            }
            if (c.getText().toString().equals(correctAnswer)){
                c.setBackgroundColor(Color.GREEN);
            }
            if (d.getText().toString().equals(correctAnswer)){
                d.setBackgroundColor(Color.GREEN);
            }
        }

        a.setClickable(false);
        b.setClickable(false);
        c.setClickable(false);
        d.setClickable(false);

        correctTotal.setText("correct : "+correct);
        wrongTotal.setText("wrong : "+wrong);
        buttonControl = true;
    }
}