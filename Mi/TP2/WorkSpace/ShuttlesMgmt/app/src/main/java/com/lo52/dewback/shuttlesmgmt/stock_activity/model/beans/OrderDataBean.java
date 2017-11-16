package com.lo52.dewback.shuttlesmgmt.stock_activity.model.beans;

/**
 * Created by Guillaume on 10/11/2017.
 */

public class OrderDataBean {
    private Integer orderId;
    private Integer productId;
    private Integer productAmount;
    private String buyerName;
    private boolean orderPaid;

    public OrderDataBean() {
    }

    public OrderDataBean(Integer orderId, Integer productId, Integer productAmount, String buyerName, boolean orderPaid) {
        this.orderId = orderId;
        this.productId = productId;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
