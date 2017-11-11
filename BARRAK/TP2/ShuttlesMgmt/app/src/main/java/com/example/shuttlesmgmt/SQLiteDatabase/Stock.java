package com.example.shuttlesmgmt.SQLiteDatabase;

/**
 * Created by Barrak on 10/11/2017.
 */

public class Stock {
    int id;
    String Reference;
    String Grade;
    Integer Quantity;
    Integer UnitPrice;


    public Stock() {
    }

    public Stock(String reference, String grade, Integer quantity, Integer unitprice) {
        Reference = reference;
        Grade = grade;
        Quantity = quantity;
        UnitPrice = unitprice;
    }

    public Stock(int id, String reference, String grade, Integer quantity, Integer unitPrice) {
        this.id = id;
        Reference = reference;
        Grade = grade;
        Quantity = quantity;
        UnitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        UnitPrice = unitPrice;
    }
}
