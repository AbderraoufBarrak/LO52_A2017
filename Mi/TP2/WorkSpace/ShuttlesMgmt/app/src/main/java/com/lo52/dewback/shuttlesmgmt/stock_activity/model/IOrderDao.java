package com.lo52.dewback.shuttlesmgmt.stock_activity.model;

import com.lo52.dewback.shuttlesmgmt.stock_activity.model.beans.OrderDataBean;

import java.util.List;

/**
 * Created by Notmoo on 16/11/2017.
 */

public interface IOrderDao {
    List<OrderDataBean> getOrders();
    OrderDataBean getOrder(Integer orderId);
}
