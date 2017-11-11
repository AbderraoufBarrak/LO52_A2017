package com.example.shuttlesmgmt.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barrak on 10/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "achatsManager";
    private static final String TABLE_ACHATS = "achats";
    private static final String KEY_ID = "id";
    private static final String KEY_CUSTOMER_NAME = "customername";
    private static final String KEY_REFERENCE = "reference";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_TOTAL = "total";
    private static final String KEY_STATUS = "status";

    private static final String TABLE_STOCK = "stock";
    private static final String KEY_GRADE = "grade";
    private static final String KEY_UNITPRICE = "unitprice";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ACHAT_TABLE = "CREATE TABLE " + TABLE_ACHATS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_CUSTOMER_NAME + " TEXT,"
                + KEY_REFERENCE + " TEXT,"
                + KEY_QUANTITY + " INTEGER,"
                + KEY_TOTAL + " INTEGER,"
                + KEY_STATUS + " INTEGER" + ")";
        db.execSQL(CREATE_ACHAT_TABLE);
        String CREATE_STOCK_TABLE = "CREATE TABLE " + TABLE_STOCK + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_REFERENCE + " TEXT,"
                + KEY_GRADE + " TEXT,"
                + KEY_QUANTITY + " INTEGER,"
                + KEY_UNITPRICE + " INTEGER" + ")";
        db.execSQL(CREATE_STOCK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACHATS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
        onCreate(db);
    }

    public void addAchat (Achat achat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CUSTOMER_NAME, achat.getCustomerName());
        values.put(KEY_REFERENCE, achat.getReference());
        values.put(KEY_QUANTITY, achat.getQuantity());
        values.put(KEY_TOTAL, achat.getTotalPrice());
        values.put(KEY_STATUS, achat.getStatus());

        db.insert(TABLE_ACHATS, null, values);
        db.close();

    }
    public void addStock (Stock stock) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REFERENCE, stock.getReference());
        values.put(KEY_GRADE, stock.getGrade());
        values.put(KEY_QUANTITY, stock.getQuantity());
        values.put(KEY_UNITPRICE, stock.getUnitPrice());

        db.insert(TABLE_STOCK, null, values);
        db.close();

    }
    Achat getAchat (int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ACHATS, new String[] {KEY_ID,KEY_CUSTOMER_NAME, KEY_REFERENCE, KEY_QUANTITY, KEY_TOTAL, KEY_STATUS}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Achat achat = new Achat(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)));

        return achat;

    }
    public Stock getStock (int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STOCK, new String[] {KEY_ID, KEY_REFERENCE, KEY_GRADE, KEY_QUANTITY, KEY_UNITPRICE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Stock stock = new Stock(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)));

        return stock;

    }
    public Stock getStockByReference (String reference) {
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor cursor = db.query(TABLE_STOCK, new String[] {KEY_ID, KEY_REFERENCE, KEY_GRADE, KEY_QUANTITY, KEY_UNITPRICE}, KEY_REFERENCE + "=?",
        //      new String[]{reference}, null, null, null, null);
        Cursor cursor = db.query(TABLE_STOCK, new String[] {KEY_ID, KEY_REFERENCE, KEY_GRADE, KEY_QUANTITY, KEY_UNITPRICE},
                KEY_REFERENCE+" like " + "'%"+reference+"%'", null, null, null, null);
        Stock stock = new Stock();
        //Stock stock = new Stock(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)));
        if(cursor.moveToFirst()){
            do {

                stock.setId(Integer.parseInt(cursor.getString(0)));
                stock.setReference(cursor.getString(1));
                stock.setGrade(cursor.getString(2));
                stock.setQuantity(Integer.parseInt(cursor.getString(3)));
                stock.setUnitPrice(Integer.parseInt(cursor.getString(4)));



            } while (cursor.moveToNext());
        }

        return stock;

    }

    public List<Achat> getAllAchats(){
        List<Achat> achatList = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+TABLE_ACHATS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                Achat achat = new Achat();
                Log.i("setId get all achats", cursor.getString(0));
                Log.i("CustomerName get all", cursor.getString(1));
                Log.i("Ref get all achats", cursor.getString(2));
                Log.i("Quant get all achats", cursor.getString(3));
                Log.i("TotPrice get all achats", cursor.getString(4));



                achat.setId(Integer.parseInt(cursor.getString(0)));
                achat.setCustomerName(cursor.getString(1));
                achat.setReference(cursor.getString(2));
                achat.setQuantity(Integer.parseInt(cursor.getString(3)));
                achat.setTotalPrice(Integer.parseInt(cursor.getString(4)));
                achat.setStatus(Integer.parseInt(cursor.getString(5)));

                achatList.add(achat);
            } while (cursor.moveToNext());
        }

        return achatList;
    }
    public void createTableStock(){
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_STOCK_TABLE = "CREATE TABLE " + TABLE_STOCK + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_REFERENCE + " TEXT,"
                + KEY_GRADE + " TEXT,"
                + KEY_QUANTITY + " INTEGER,"
                + KEY_UNITPRICE + " INTEGER" + ")";
        db.execSQL(CREATE_STOCK_TABLE);
    }
    public List<Stock> getAllStock(){
        List<Stock> stockList = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+TABLE_STOCK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                Stock stock = new Stock();
                Log.i("setId get all achats", cursor.getString(0));
                Log.i("Reference get all", cursor.getString(1));
                Log.i("Grade get all achats", cursor.getString(2));
                Log.i("Quant get all achats", cursor.getString(3));
                Log.i("UnitPri get all achats", cursor.getString(4));



                stock.setId(Integer.parseInt(cursor.getString(0)));
                stock.setReference(cursor.getString(1));
                stock.setGrade(cursor.getString(2));
                stock.setQuantity(Integer.parseInt(cursor.getString(3)));
                stock.setUnitPrice(Integer.parseInt(cursor.getString(4)));


                stockList.add(stock);
            } while (cursor.moveToNext());
        }

        return stockList;
    }
    public int getAchatsCount (){
        String countQuery = "SELECT * FROM " + TABLE_ACHATS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }
    public int getStockCount (){
        String countQuery = "SELECT * FROM " + TABLE_STOCK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }
    /**
     * Remove all users and groups from database.
     */
    public void removeAll()
    {
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete(TABLE_ACHATS, null, null);
        db.delete(TABLE_STOCK, null, null);

    }
}
