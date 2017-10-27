package com.example.shuttlesmgmt.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Michel on 2017/10/8.
 */

public class DBHandler extends SQLiteOpenHelper{

    //databse version
    private static final int DATABASE_VERSION = 1;
    //database name
    private static final String DATABASE_NAME = "shuttles.db";
    private Context context;

    public DBHandler(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = c;
    }

    //creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        createDB(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteDB(db);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
    private void deleteDB(SQLiteDatabase db){
        db.execSQL(ShuttlesContract.Customer.CUSTOMER_TABLE_DROP);
        db.execSQL(ShuttlesContract.Supplier.SUPPLIER_TABLE_DROP);
        db.execSQL(ShuttlesContract.Product.PRODUCT_TABLE_DROP);
        db.execSQL(ShuttlesContract.Order.ORDER_TABLE_DROP);
    }

    private void createDB(SQLiteDatabase db){
        db.execSQL(ShuttlesContract.Customer.CUSTOMER_TABLE_CREATE);
        db.execSQL(ShuttlesContract.Supplier.SUPPLIER_TABLE_CREATE);
        db.execSQL(ShuttlesContract.Product.PRODUCT_TABLE_CREATE);
        db.execSQL(ShuttlesContract.Order.ORDER_TABLE_CREATE);
        createData(db);
    }

    private long createCustomer( SQLiteDatabase db, String name, String add, String phone){
        ContentValues values = new ContentValues();
        values.put(ShuttlesContract.Customer.CUSTOMER_NAME, name);
        values.put(ShuttlesContract.Customer.CUSTOMER_ADDRESSE, add);
        values.put(ShuttlesContract.Customer.CUSTOMER_PHONE, phone);
        return db.insert(ShuttlesContract.Customer.CUSTOMER_TABLE_NAME, null, values);
    }

    private long createSupplier(SQLiteDatabase db, String name, String add, String phone){
        ContentValues values = new ContentValues();
        values.put(ShuttlesContract.Supplier.SUPPLIER_NAME, name);
        values.put(ShuttlesContract.Supplier.SUPPLIER_ADDRESSE, add);
        values.put(ShuttlesContract.Supplier.SUPPLIER_PHONE, phone);
        return db.insert(ShuttlesContract.Supplier.SUPPLIER_TABLE_NAME, null, values);
    }

    private long createProduct(SQLiteDatabase db, String name, String ref, int stock, double price, int supplier){
        ContentValues values = new ContentValues();
        values.put(ShuttlesContract.Product.PRODUCT_NAME, name);
        values.put(ShuttlesContract.Product.PRODUCT_REFERENCE, ref);
        values.put(ShuttlesContract.Product.PRODUCT_STOCK, stock);
        values.put(ShuttlesContract.Product.PRODUCT_PRICE, price);
        values.put(ShuttlesContract.Product.PRODUCT_SUPPLIER_ID,supplier);
        return db.insertOrThrow(ShuttlesContract.Product.PRODUCT_TABLE_NAME, null, values);
    }

    private long createOrder(SQLiteDatabase db, int productID, int quantity, int customer, String date, boolean isPaid){
        ContentValues values = new ContentValues();
        values.put(ShuttlesContract.Order.ORDER_PRODUCT_ID, productID);
        values.put(ShuttlesContract.Order.ORDER_QUANTITY, quantity);
        values.put(ShuttlesContract.Order.ORDER_CUSTOMER_ID, customer);
        values.put(ShuttlesContract.Order.ORDER_DATE, date);
        values.put(ShuttlesContract.Order.ORDER_ISPAID, isPaid);
        return db.insert(ShuttlesContract.Order.ORDER_TABLE_NAME, null, values);
    }

    public void createDataCustomer(SQLiteDatabase db){
        createCustomer(db, "UTBM", "BEFLORT", "0303030303");
        createCustomer(db, "TITO", "PARIS", "0606060606");
        createCustomer(db, "TATA", "AMERIQUE","0909090909");
    }

    public void createDataProduct(SQLiteDatabase db){
        createProduct(db, "Yonex", "AS30", 500, 29, 1);
        createProduct(db, "RSL", "Grade 3", 5000, 8.70, 2);
        createProduct(db, "RSL", "Grade A9", 0, 13.70, 3);
        createProduct(db, "RSL", "Grade A1", 60, 21, 4);
    }

    public void createDataSupplier(SQLiteDatabase db){
        createSupplier(db, "RANGEX", "BELGIQUE", "0320000230");
        createSupplier(db, "RTB", "AFRIQUE", "03200002222");
        createSupplier(db, "TEST", "BELGIQUE", "0320333230");
        createSupplier(db, "TES4", "AMERIQUE", "0321111230");

    }

    public void createDataOrder(SQLiteDatabase db){
        createOrder(db, 1, 10, 1, "27/10/17", true);
        createOrder(db, 2, 500, 1, "27/10/17", false);
        createOrder(db, 4, 10, 1, "27/10/17", true);
        createOrder(db, 4, 40, 1, "27/10/17", false);
    }

    public void createData(SQLiteDatabase db){
        createDataCustomer(db);
        createDataProduct(db);
        createDataSupplier(db);
        createDataOrder(db);
    }
}
