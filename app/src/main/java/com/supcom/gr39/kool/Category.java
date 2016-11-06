package com.supcom.gr39.kool;

public class Category {
    private String name;
    private String numOfItems;
    //private int thumbnail;
    String url ;

    public Category() {
    }

    public Category(String name, String numOfItems, String url) {
        this.name = name;
        this.numOfItems = numOfItems;
        this.url=url;
        //this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(String numOfItems) {
        this.numOfItems = numOfItems;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}