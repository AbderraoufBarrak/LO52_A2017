package com.lo52.dewback.shuttlesmgmt.stock_activity.model;

/**
 * Created by Guillaume on 20/10/2017.
 */

public class StockDataBean {
    private String brand;
    private String name;
    private Double price;
    private Integer stock;
    private int overviewImgRes;

    public StockDataBean(String brand, String name, Double price, Integer stock, int overviewImgRes) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.overviewImgRes = overviewImgRes;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public int getOverviewImgRes() {
        return overviewImgRes;
    }
}
