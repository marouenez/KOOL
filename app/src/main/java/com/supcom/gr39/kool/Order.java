package com.supcom.gr39.kool;

public class Order {

    public String name;
    public int quantity;

    public Order (){}

    public String getMeal() {
        return name;
    }
    public void setMeal(String name){
        this.name=name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }

}