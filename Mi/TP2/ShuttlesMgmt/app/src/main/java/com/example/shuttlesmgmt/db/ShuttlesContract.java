package com.example.shuttlesmgmt.db;

import android.provider.BaseColumns;

/**
 * Created by Michel on 2017/10/27.
 */

public class ShuttlesContract {

    public class Customer implements BaseColumns{
        //Table Customers
        public static final String CUSTOMER_ID = "customer_id";
        public static final String CUSTOMER_NAME = "customer_name";
        public static final String CUSTOMER_ADDRESSE = "customer_add";
        public static final String CUSTOMER_PHONE = "customer_phone";
        public static final String CUSTOMER_TABLE_NAME = "customers";
        public static final String CUSTOMER_TABLE_CREATE =
                "CREATE TABLE " + CUSTOMER_TABLE_NAME + " ( " +
                        CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        CUSTOMER_NAME + " TEXT," +
                        CUSTOMER_ADDRESSE + " TEXT, " +
                        CUSTOMER_PHONE + " TEXT );";
        public static final String CUSTOMER_TABLE_DROP = "DROP TABLE IF EXISTS " + CUSTOMER_TABLE_NAME + ";";
    }

    public class Order implements BaseColumns{
        //Table Orders
        public static final String ORDER_ID = "order_id";
        public static final String ORDER_PRODUCT_ID = Product.PRODUCT_ID;
        public static final String ORDER_DATE = "order_date";
        public static final String ORDER_ISPAID = "order_ispaid";
        public static final String ORDER_QUANTITY = "quantity";
        public static final String ORDER_TOTAL_PRICE = "total_price";
        public static final String ORDER_CUSTOMER_ID = Customer.CUSTOMER_ID;
        public static final String ORDER_TABLE_NAME = "orders";
        public static final String ORDER_TABLE_CREATE =
                "CREATE TABLE " + ORDER_TABLE_NAME + "( "+
                        ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ORDER_PRODUCT_ID + " INTEGER," +
                        ORDER_DATE + " DATE," +
                        ORDER_CUSTOMER_ID + " INTEGER," +
                        ORDER_QUANTITY + " INTEGER," +
                        ORDER_TOTAL_PRICE + " INTEGER, " +
                        ORDER_ISPAID + " INTEGER,"+
                        "FOREIGN KEY(" + ORDER_PRODUCT_ID + ") REFERENCES " + Product.PRODUCT_TABLE_NAME + "(" + Product.PRODUCT_ID + ")," +
                        "FOREIGN KEY(" + ORDER_CUSTOMER_ID + ") REFERENCES " + Customer.CUSTOMER_TABLE_NAME + "(" + Customer.CUSTOMER_ID + ");";
        public static final String ORDER_TABLE_DROP = "DROP TABLE IF EXISTS " + ORDER_TABLE_NAME + ";";
    }

    public class Supplier implements BaseColumns{
        //Table Suppliers
        public static final String SUPPLIER_ID = "supplier_id";
        public static final String SUPPLIER_NAME = "supplier_name";
        public static final String SUPPLIER_ADDRESSE = "supplier_add";
        public static final String SUPPLIER_PHONE = "supplier_phone";
        public static final String SUPPLIER_TABLE_NAME = "suppliers";
        public static final String SUPPLIER_TABLE_CREATE =
                "CREATE TABLE " + SUPPLIER_TABLE_NAME + "(" +
                        SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        SUPPLIER_NAME + " TEXT," +
                        SUPPLIER_ADDRESSE + " TEXT," +
                        SUPPLIER_PHONE + " TEXT );";
        public static final String SUPPLIER_TABLE_DROP = "DROP TABLE IF EXISTS" + SUPPLIER_TABLE_NAME + ";";
    }

    public class Product implements BaseColumns{
        //Table Products
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PRODUCT_REFERENCE = "product_ref";
        public static final String PRODUCT_STOCK = "product_stock";
        public static final String PRODUCT_PRICE = "product_price";
        public static final String PRODUCT_SUPPLIER_ID = Supplier.SUPPLIER_ID;
        public static final String PRODUCT_TABLE_NAME = "products";
        public static final String PRODUCT_TABLE_CREATE =
                "CREATE TABLE " + PRODUCT_TABLE_NAME + "("+
                        PRODUCT_NAME + " TEXT, "+
                        PRODUCT_REFERENCE + " TEXT," +
                        PRODUCT_STOCK + " INTEGER, "+
                        PRODUCT_PRICE + " INTEGER, " +
                        PRODUCT_SUPPLIER_ID + "INTEGER," +
                        "FOREIGN KEY(" + PRODUCT_SUPPLIER_ID + ") REFERENCES " + Supplier.SUPPLIER_TABLE_NAME + "(" + Supplier.SUPPLIER_ID +");";
        public static final String PRODUCT_TABLE_DROP = "DROP TABLE IF EXISTS" + PRODUCT_TABLE_NAME + ";";
    }
}
