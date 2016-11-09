package com.supcom.gr39.kool;

/**
 * Created by marouenez on 08/11/16.
 */

public class ItemsOr {

    private int quantity;
    private String nameIt;
    private int price;

    ItemsOr(){}

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getNameIt() {
        return nameIt;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNameIt(String name) {
        this.nameIt = name;
    }
}
