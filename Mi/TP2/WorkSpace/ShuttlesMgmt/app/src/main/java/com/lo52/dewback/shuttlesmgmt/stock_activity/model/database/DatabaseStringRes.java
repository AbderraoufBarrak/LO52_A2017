package com.lo52.dewback.shuttlesmgmt.stock_activity.model.database;

/**
 * Created by Notmoo on 16/11/2017.
 */

class DatabaseStringRes {
        static final String TABLE_SHUTTLE = "Table_shuttle";
        static final String COL_SHUTTLE_ID = "Shuttle_id";
        static final String COL_SHUTTLE_BRAND = "Shuttle_brand";
        static final String COL_SHUTTLE_NAME = "Shuttle_name";
        static final String COL_SHUTTLE_PRICE = "Shuttle_price";
        static final String COL_SHUTTLE_STOCK = "Shuttle_stock";

        static final String TABLE_ORDER = "Table_order";
        static final String COL_ORDER_ID = "Order_id";
        static final String COL_ORDER_PRODUCT_ID = "Order_shuttle_id";
        static final String COL_ORDER_PRODUCT_AMOUNT = "Order_shuttle_amount";
        static final String COL_ORDER_BUYER_NAME = "Order_buyer_name";
        static final String COL_ORDER_PAID_STATUS = "Order_paid";
}
