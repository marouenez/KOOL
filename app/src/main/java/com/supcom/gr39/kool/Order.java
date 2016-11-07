package com.supcom.gr39.kool;

public class Order {

    public String tableId;
    public String name;
    public int quantity;

    public Order (){}
    public String getTable() {
        return tableId;
    }
    public void setTable(String table){
        this.tableId=table;
    }
    public String getMeal() {
        return name;
    }
    public void setMeal(String meal){
        this.name=meal;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }

}