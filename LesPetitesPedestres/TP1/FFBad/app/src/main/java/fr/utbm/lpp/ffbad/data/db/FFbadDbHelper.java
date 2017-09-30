package fr.utbm.lpp.ffbad.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FFbadDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FFBad.db";

    public FFbadDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createEntries(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        deleteEntries(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void createEntries(SQLiteDatabase db) {
        Log.i("DbHelper", "Recreate entries");
        db.execSQL(FFBadDbContract.Shuttlecock.SQL_CREATE_ENTRIES);
        db.execSQL(FFBadDbContract.Trader.SQL_CREATE_ENTRIES);
        db.execSQL(FFBadDbContract.Customer.SQL_CREATE_ENTRIES);
        db.execSQL(FFBadDbContract.Sale.SQL_CREATE_ENTRIES);
        db.execSQL(FFBadDbContract.Purchase.SQL_CREATE_ENTRIES);

        createShuttlecock(db, "Yonex", "AS30", 500, 27);
        createShuttlecock(db, "RSL", "Grade 3", 5000, 16.70);
        createShuttlecock(db, "RSL", "Grade A9", 10000, 13.70);
        createShuttlecock(db, "RSL", "Grade A1", 6000, 21);

        createCustomer(db, "FFBad Belfort", 1);
        createCustomer(db, "Florian Staine", 2);

        createSale(db, 1, 2, 3.50, 4, true);
        createSale(db, 2, 2, 4.50, 4, false);
    }

    private void deleteEntries(SQLiteDatabase db) {
        db.execSQL(FFBadDbContract.Shuttlecock.SQL_DELETE_ENTRIES);
        db.execSQL(FFBadDbContract.Trader.SQL_DELETE_ENTRIES);
        db.execSQL(FFBadDbContract.Customer.SQL_DELETE_ENTRIES);
        db.execSQL(FFBadDbContract.Sale.SQL_DELETE_ENTRIES);
        db.execSQL(FFBadDbContract.Purchase.SQL_DELETE_ENTRIES);
    }

    private long createShuttlecock (SQLiteDatabase db, String brand, String ref, int stock, double price) {
        ContentValues values = new ContentValues();

        values.put(FFBadDbContract.Shuttlecock.COLUMN_NAME_BRAND, brand);
        values.put(FFBadDbContract.Shuttlecock.COLUMN_NAME_REFERENCE, ref);
        values.put(FFBadDbContract.Shuttlecock.COLUMN_NAME_STOCK, stock);
        values.put(FFBadDbContract.Shuttlecock.COLUMN_NAME_PRICE, price);

        long rowID = db.insert(FFBadDbContract.Shuttlecock.TABLE_NAME, null, values);
        return rowID;
    }

    private long createCustomer(SQLiteDatabase db, String name, int type) {
        ContentValues values = new ContentValues();

        values.put(FFBadDbContract.Customer.COLUMN_NAME_NAME, name);
        values.put(FFBadDbContract.Customer.COLUMN_NAME_NAME, type);

        return db.insert(FFBadDbContract.Customer.TABLE_NAME, null, values);
    }

    private long createSale(SQLiteDatabase db, int customer_id, int shuttlecock_id, double price, int quantity, boolean is_paid) {
        ContentValues values = new ContentValues();

        values.put(FFBadDbContract.Sale.COLUMN_NAME_CUSTOMER, customer_id);
        values.put(FFBadDbContract.Sale.COLUMN_NAME_SHUTTLECOCK, shuttlecock_id);
        values.put(FFBadDbContract.Sale.COLUMN_NAME_PRICE, price);
        values.put(FFBadDbContract.Sale.COLUMN_NAME_QUANTITY, quantity);
        values.put(FFBadDbContract.Sale.COLUMN_NAME_IS_PAID, is_paid);

        return db.insert(FFBadDbContract.Sale.TABLE_NAME, null, values);
    }
}
