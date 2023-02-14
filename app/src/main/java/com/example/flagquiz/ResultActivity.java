package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.temporal.Temporal;

public class ResultActivity extends AppCompatActivity {

    private TextView correct,wrong,empty,success;
    private Button again,quit;
    int correctTotal,wrongTotal,emptyTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        correct = findViewById(R.id.textViewTotalCorrect);
        wrong = findViewById(R.id.textViewTotalWrong);
        empty = findViewById(R.id.textViewTotalEmpty);
        success = findViewById(R.id.textViewTotalSuccess);
        again = findViewById(R.id.buttonAgain);
        quit = findViewById(R.id.buttonQuit);

        correctTotal = getIntent().getIntExtra("correct",0);
        wrongTotal = getIntent().getIntExtra("wrong", 0);
        emptyTotal = getIntent().getIntExtra("empty",0);

        correct.setText("total correct answer : "+ correctTotal);
        wrong.setText("total wrong answer : "+wrongTotal);
        empty.setText("total empty answer : "+emptyTotal);
        success.setText("succes rate : "+(correctTotal*10));

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();

            }
        });
    }
}