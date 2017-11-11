package com.example.shuttlesmgmt.SQLiteDatabase;

/**
 * Created by Barrak on 10/11/2017.
 */

public class Achat {

    int id;
    String CustomerName;
    String Reference;
    Integer Quantity;
    Integer TotalPrice;
    Integer Status;

    public Achat() {
    }

    public Achat(String customerName, String reference, Integer quantity, Integer totalPrice, Integer status) {
        CustomerName = customerName;
        Reference = reference;
        Quantity = quantity;
        TotalPrice = totalPrice;
        Status = status;
    }

    public Achat(int id, String customerName, String reference, Integer quantity, Integer totalPrice, Integer status) {
        this.id = id;
        CustomerName = customerName;
        Reference = reference;
        Quantity = quantity;
        TotalPrice = totalPrice;
        Status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        TotalPrice = totalPrice;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }
}
