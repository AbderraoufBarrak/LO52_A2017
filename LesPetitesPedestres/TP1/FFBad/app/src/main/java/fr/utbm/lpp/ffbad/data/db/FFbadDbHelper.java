package fr.utbm.lpp.ffbad.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        deleteEntries(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }

    private void createEntries(SQLiteDatabase db) {
        db.execSQL(FFBadDbContract.Shuttlecock.SQL_CREATE_ENTRIES);
        db.execSQL(FFBadDbContract.Trader.SQL_CREATE_ENTRIES);
        db.execSQL(FFBadDbContract.Customer.SQL_CREATE_ENTRIES);
        db.execSQL(FFBadDbContract.Sale.SQL_CREATE_ENTRIES);
        db.execSQL(FFBadDbContract.Purchase.SQL_CREATE_ENTRIES);

        createShuttlecock(db, "Yonex", "AS30", 500, 27);
        createShuttlecock(db, "RSL", "Grade 3", 5000, 16.70);
        createShuttlecock(db, "RSL", "Grade A9", 10000, 13.70);
        createShuttlecock(db, "RSL", "Grade A1", 6000, 21);
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
}
