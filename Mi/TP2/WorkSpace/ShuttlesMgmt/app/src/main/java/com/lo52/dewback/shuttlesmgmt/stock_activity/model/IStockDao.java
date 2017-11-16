package com.lo52.dewback.shuttlesmgmt.stock_activity.model;

import com.lo52.dewback.shuttlesmgmt.stock_activity.model.beans.StockDataBean;

import java.util.List;

/**
 * Created by Notmoo on 16/11/2017.
 */

public interface IStockDao {
    List<StockDataBean> getStock();
}
