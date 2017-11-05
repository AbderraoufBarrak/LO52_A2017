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
    private String CustomerName;
    private String ProductName;
    private String[] order;

    public Order(){

    }

    //pour un total imposé
    public Order(long id, Date date, boolean isPaid, int quantity, long idC, long idP, double price){
        this.id = id;
        this.date = date;
        this.isPaid = isPaid;
        this.quantity = quantity;
        this.idCustomer = idC;
        this.idProduct = idP;
        this.total_price = price;
    }

    public Order(long id, Date date, boolean isPaid, int quantity, long idC, long idP){
        this.id = id;
        this.date = date;
        this.isPaid = isPaid;
        this.quantity = quantity;
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

    public String getCustomerName(){
        return CustomerName;
    }

    public void setCustomerName(String n){
        this.CustomerName = n;
    }

    public String getProductName(){
        return ProductName;
    }

    public void setProductName(String n){
        this.ProductName = n;
    }

    public String[] getOrder(){
        return order = new String[]{
              StringToDate(getDate()),
                Boolean.toString(getIsPaid()),
                Integer.toString(getQuantity()),
                Long.toString(getIdCustomer()),
                Long.toString(getIdProduct())
        };
    }

    public String StringToDate(Date d){
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        return s.format(d);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idCustomer=" + idCustomer +
                ", idProduct=" + idProduct +
                ", date=" + date +
                ", isPaid=" + isPaid +
                ", quantity=" + quantity +
                ", total_price=" + total_price +
                '}';
    }
}
