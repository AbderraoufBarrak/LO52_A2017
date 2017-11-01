package com.example.shuttlesmgmt.db;

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

    public DBHandler(Context c, SQLiteDatabase.CursorFactory f){
        super(c, DATABASE_NAME, f, DATABASE_VERSION);
        this.context = c;
    }

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
        db.execSQL(ShuttlesSchema.Customer.CUSTOMER_TABLE_DROP);
        db.execSQL(ShuttlesSchema.Supplier.SUPPLIER_TABLE_DROP);
        db.execSQL(ShuttlesSchema.Product.PRODUCT_TABLE_DROP);
        db.execSQL(ShuttlesSchema.Order.ORDER_TABLE_DROP);
    }

    private void createDB(SQLiteDatabase db){
        db.execSQL(ShuttlesSchema.Customer.CUSTOMER_TABLE_CREATE);
        db.execSQL(ShuttlesSchema.Supplier.SUPPLIER_TABLE_CREATE);
        db.execSQL(ShuttlesSchema.Product.PRODUCT_TABLE_CREATE);
        db.execSQL(ShuttlesSchema.Order.ORDER_TABLE_CREATE);
    }

}
