package com.supcom.gr39.kool;

public class Dish {
    private String mText1;
    private String mText2;
    private String url;

    Dish (String text1, String text2, String url){
        mText1 = text1;
        mText2 = text2;
        this.url = url;
    }

    public String getTitleDish() {
        return mText1;
    }

    public void setTitleDish(String mText1) {
        this.mText1 = mText1;
    }

    public String getDesc() {
        return mText2;
    }

    public void setDesc(String mText2) {
        this.mText2 = mText2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }
}