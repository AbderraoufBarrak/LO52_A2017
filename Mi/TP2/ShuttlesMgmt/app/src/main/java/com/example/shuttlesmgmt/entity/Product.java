package com.example.shuttlesmgmt.entity;

/**
 * Created by Michel on 2017/10/30.
 */

public class Product {
    private long id;
    private long idSupplier;
    private String name;
    private String ref;
    private int quantity;
    private double price;
    private String[] product;

    public Product(){

    }

    public Product(long id, long idS, String name, String ref, int quantity, double price){
        this.id = id;
        this.idSupplier = idS;
        this.name = name;
        this.ref = ref;
        this.quantity = quantity;
        this.price = price;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setIdSupplier(long id){
        this.idSupplier = id;
    }

    public long getIdSupplier(){
        return idSupplier;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setRef(String ref){
        this.ref = ref;
    }

    public String getRef(){
        return ref;
    }

    public void setQuantity(int q){
        this.quantity = q;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setPrice(double p){
        this.price = p;
    }

    public double getPrice(){
        return price;
    }

    public String[] getProduct(){
        return product = new String[]{
            getName(),
            getRef(),
            Integer.toString(getQuantity()),
            Double.toString(getPrice()),
                Long.toString(getIdSupplier())
        };
    }
}
