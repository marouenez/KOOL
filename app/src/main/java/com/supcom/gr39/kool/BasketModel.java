package com.supcom.gr39.kool;

/**
 * Created by marouenez on 07/11/16.
 */

public class BasketModel {

    private int quantity;
    private String product;

    private String price;

    public BasketModel(int quantity ,String product, String price) {

        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }


    public String getProduct() {
        return product;
    }


    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}