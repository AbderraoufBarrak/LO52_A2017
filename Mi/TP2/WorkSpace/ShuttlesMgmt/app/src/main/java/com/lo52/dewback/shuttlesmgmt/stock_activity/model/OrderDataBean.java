package com.lo52.dewback.shuttlesmgmt.stock_activity.model;

/**
 * Created by Guillaume on 10/11/2017.
 */

public class OrderDataBean {
    //TODO remove products name and brand, they will be retreived outside of this bean thanks to product id
    private Integer orderId;
    private StockDataBean product;
    private Integer productAmount;
    private String buyerName;
    private boolean orderPaid;

    public OrderDataBean() {
    }

    public OrderDataBean(Integer orderId, StockDataBean product, Integer productAmount, String buyerName, boolean orderPaid) {
        this.orderId = orderId;
        this.product = product;
        this.productAmount = productAmount;
        this.buyerName = buyerName;
        this.orderPaid = orderPaid;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public StockDataBean getProduct() {
        return product;
    }

    public void setProduct(StockDataBean product) {
        this.product = product;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public boolean isOrderPaid() {
        return orderPaid;
    }

    public void setOrderPaid(boolean orderPaid) {
        this.orderPaid = orderPaid;
    }
}
