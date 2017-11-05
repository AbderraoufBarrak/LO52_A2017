package com.example.shuttlesmgmt.entity;

/**
 * Created by Michel on 2017/10/30.
 */

public class Supplier {
    private long id;
    private String name;
    private String add;
    private String phone;
    private String[] supplier;

    public Supplier(){

    }

    public Supplier(long id, String name, String add, String phone){
        this.id = id;
        this.name = name;
        this.add = add;
        this.phone = phone;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
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

    public void setPhone(String p){
        this.phone = p;
    }

    public String[] getSupplier(){
        return supplier = new String[]{
                getName(),
                getAdd(),
                getPhone()
        };
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", add='" + add + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
