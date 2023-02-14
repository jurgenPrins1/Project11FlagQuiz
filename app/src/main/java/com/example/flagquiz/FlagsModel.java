package com.example.flagquiz;

public class FlagsModel {

    private int flag;
    private String flag_name, flag_image;

    public FlagsModel() {
    }

    public FlagsModel(int flag, String flag_name, String flag_image) {
        this.flag = flag;
        this.flag_name = flag_name;
        this.flag_image = flag_image;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getFlag_name() {
        return flag_name;
    }

    public void setFlag_name(String flag_name) {
        this.flag_name = flag_name;
    }

    public String getFlag_image() {
        return flag_image;
    }

    public void setFlag_image(String flag_image) {
        this.flag_image = flag_image;
    }
}
