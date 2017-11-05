package com.example.shuttlesmgmt.entity;

/**
 * Created by Michel on 2017/10/27.
 */

public class Customer {
    private long id;
    private String name;
    private String add;
    private String phone;
    private String[] customer;

    public Customer(){

    }

    public Customer(long id, String name, String add, String phone){
        this.id = id;
        this.name = name;
        this.add = add;
        this.phone = phone;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        this.name = n;
    }

    public String getAdd(){
        return add;
    }

    public void setAdd(String add){
        this.add = add;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String[] getCustomer(){
        customer = new String[]{
                getName(),
                getAdd(),
                getPhone()
        };
        return customer;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", add='" + add + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
