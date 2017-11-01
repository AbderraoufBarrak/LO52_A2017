package com.example.shuttlesmgmt.db;

import android.provider.BaseColumns;

/**
 * Created by Michel on 2017/10/27.
 */

public class ShuttlesSchema {

    public static final String CREATE_TAB = "CREATE TABLE ";
    public static final String COMMA = ",";
    public static final String SEMI_COLON = ";";
    public static final String START = " ( ";
    public static final String END = " )" + SEMI_COLON;
    public static final String INTEGER = " INTEGER";
    public static final String PK_AUTO = " PRIMARY KEY AUTOINCREMENT";
    public static final String INTEGER_PK_AUTO = INTEGER + PK_AUTO + COMMA;
    public static final String DATE = " DATE";
    public static final String TEXT = " TEXT";
    public static final String FK = "FOREIGN KEY" + START;
    public static final String REFERENCE = ") REFERENCES ";
    public static final String DROP = "DROP TABLE IF EXISTS ";

    public class Customer implements BaseColumns{
        //Table Customers
        public static final String CUSTOMER_ID = "customer_id";
        public static final String CUSTOMER_NAME = "customer_name";
        public static final String CUSTOMER_ADDRESSE = "customer_add";
        public static final String CUSTOMER_PHONE = "customer_phone";
        public static final String CUSTOMER_TABLE_NAME = "customers";
        public static final String CUSTOMER_TABLE_CREATE =
                CREATE_TAB + CUSTOMER_TABLE_NAME + START  +
                        CUSTOMER_ID + INTEGER_PK_AUTO +
                        CUSTOMER_NAME + TEXT  + COMMA +
                        CUSTOMER_ADDRESSE + TEXT  + COMMA +
                        CUSTOMER_PHONE + TEXT + END;
        public static final String CUSTOMER_TABLE_DROP = DROP + CUSTOMER_TABLE_NAME + SEMI_COLON;

        String[] CUSTOMERS_COLUMNS = new String[]{
                CUSTOMER_ID,
                CUSTOMER_NAME,
                CUSTOMER_ADDRESSE,
                CUSTOMER_PHONE
        };
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
                CREATE_TAB  + ORDER_TABLE_NAME + START +
                        ORDER_ID + INTEGER_PK_AUTO +
                        ORDER_PRODUCT_ID + INTEGER + COMMA +
                        ORDER_DATE + DATE + COMMA +
                        ORDER_CUSTOMER_ID + INTEGER + COMMA +
                        ORDER_QUANTITY + INTEGER + COMMA +
                        ORDER_TOTAL_PRICE + INTEGER + COMMA +
                        ORDER_ISPAID + INTEGER + COMMA +
                        FK + ORDER_PRODUCT_ID + REFERENCE + Product.PRODUCT_TABLE_NAME + START + Product.PRODUCT_ID + "), "+
                        FK + ORDER_CUSTOMER_ID + REFERENCE + Customer.CUSTOMER_TABLE_NAME + START + Customer.CUSTOMER_ID + " ) "+END;
        public static final String ORDER_TABLE_DROP = DROP + ORDER_TABLE_NAME + SEMI_COLON;

        String[] ORDERS_COLUMS = new String[]{
               ORDER_ID,
                ORDER_PRODUCT_ID,
                ORDER_CUSTOMER_ID,
                ORDER_DATE,
                ORDER_QUANTITY,
                ORDER_TOTAL_PRICE,
                ORDER_ISPAID,
        };
    }

    public class Supplier implements BaseColumns{
        //Table Suppliers
        public static final String SUPPLIER_ID = "supplier_id";
        public static final String SUPPLIER_NAME = "supplier_name";
        public static final String SUPPLIER_ADDRESSE = "supplier_add";
        public static final String SUPPLIER_PHONE = "supplier_phone";
        public static final String SUPPLIER_TABLE_NAME = "suppliers";
        public static final String SUPPLIER_TABLE_CREATE =
                CREATE_TAB  + SUPPLIER_TABLE_NAME + START +
                        SUPPLIER_ID + INTEGER_PK_AUTO +
                        SUPPLIER_NAME + TEXT  + COMMA +
                        SUPPLIER_ADDRESSE + TEXT  + COMMA +
                        SUPPLIER_PHONE + TEXT + END;
        public static final String SUPPLIER_TABLE_DROP = DROP + SUPPLIER_TABLE_NAME + SEMI_COLON;

        String[] SUPPLIERS_COLUMS = new String[]{
                SUPPLIER_ID,
                SUPPLIER_NAME,
                SUPPLIER_ADDRESSE,
                SUPPLIER_PHONE
        };
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
                CREATE_TAB  + PRODUCT_TABLE_NAME + START +
                        PRODUCT_ID + INTEGER_PK_AUTO +
                        PRODUCT_NAME + TEXT + COMMA +
                        PRODUCT_REFERENCE +TEXT + COMMA +
                        PRODUCT_STOCK + INTEGER + COMMA +
                        PRODUCT_PRICE + INTEGER + COMMA +
                        PRODUCT_SUPPLIER_ID + INTEGER + COMMA +
                        FK + PRODUCT_SUPPLIER_ID + REFERENCE + Supplier.SUPPLIER_TABLE_NAME + START + Supplier.SUPPLIER_ID + " ) " + END;
        public static final String PRODUCT_TABLE_DROP = DROP + PRODUCT_TABLE_NAME + SEMI_COLON;

        String[] PRODUCTS_COLUMNS = new String[]{
                PRODUCT_ID,
                PRODUCT_NAME,
                PRODUCT_REFERENCE,
                PRODUCT_STOCK,
                PRODUCT_PRICE,
                PRODUCT_SUPPLIER_ID
        };
    }
}
