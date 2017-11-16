package com.lo52.dewback.shuttlesmgmt.stock_activity.model;

/**
 * Created by promet on 10/11/17.
 */

public class AchatDataBean {
    private String buyer;
    private String reference;
    private Double price;
    private Integer quantity;
    private int overviewImgRes;

    public AchatDataBean(String buyer, String reference, Double price, Integer quantity, int overviewImgRes) {
        this.buyer = buyer;
        this.reference = reference;
        this.price = price;
        this.quantity = quantity;
        this.overviewImgRes = overviewImgRes;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getOverviewImgRes() {
        return overviewImgRes;
    }

}


