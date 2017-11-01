package com.example.shuttlesmgmt.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Michel on 2017/10/30.
 */

public class Order {
    private long id;
    private long idCustomer;
    private long idProduct;
    private Date date;
    private boolean isPaid;
    private int quantity;
    private double total_price;
    private String[] order;

    public Order(){

    }

    public Order(long id, Date date, boolean isPaid, int quantity, double price, long idC, long idP){
        this.id = id;
        this.date = date;
        this.isPaid = isPaid;
        this.quantity = quantity;
        this.total_price = price;
        this.idCustomer = idC;
        this.idProduct = idP;
    }

    public long getIdCustomer(){
        return idCustomer;
    }

    public void setIdCustomer(long id){
        this.idCustomer = id;
    }

    public long getIdProduct(){
        return idProduct;
    }

    public void setIdProduct(long id){
        this.idProduct = id;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date d){
        this.date = d;
    }

    public boolean getIsPaid(){
        return isPaid;
    }

    public void setIsPaid(boolean b){
        this.isPaid = b;
    }

    public void setQuantity(int q){
        this.quantity = q;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setPrice(double p){
        this.total_price = p;
    }

    public double getPrice(){
        return total_price;
    }

    public String[] getOrder(){
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        String date = s.format(getDate());
        return order = new String[]{
              date,
                Boolean.toString(getIsPaid()),
                Integer.toString(getQuantity()),
                Double.toString(getPrice()),
                Long.toString(getIdCustomer()),
                Long.toString(getIdProduct())
        };
    }
}
