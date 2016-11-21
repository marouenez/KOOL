package com.supcom.gr39.kool;

import android.widget.CheckBox;

/**
 * Created by marouenez on 07/11/16.
 */

public class BasketModel {

    private int quantity;
    private String name;
    private String note ;
    private String price;


    public BasketModel(int quantity ,String product,String note , String price) {

        this.name = product;
        this.price = price;
        this.quantity = quantity;
        this.note = note;
    }


    public String getName() {
        return name;
    }


    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setProduct(String product) {
        this.name = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}