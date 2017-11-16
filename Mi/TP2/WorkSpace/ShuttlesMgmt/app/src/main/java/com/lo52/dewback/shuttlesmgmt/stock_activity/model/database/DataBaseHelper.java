package com.lo52.dewback.shuttlesmgmt.stock_activity.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Notmoo on 16/11/2017.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String CREATE_TABLE_SHUTTLE = "CREATE TABLE " + DatabaseStringRes.TABLE_SHUTTLE + " ("
            + DatabaseStringRes.COL_SHUTTLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"
            + ", " + DatabaseStringRes.COL_SHUTTLE_BRAND + " TEXT NOT NULL"
            + ", " + DatabaseStringRes.COL_SHUTTLE_NAME + " TEXT NOT NULL"
            + ", " + DatabaseStringRes.COL_SHUTTLE_PRICE + " REAL NOT NULL"
            + ", " + DatabaseStringRes.COL_SHUTTLE_STOCK + " INTEGER NOT NULL"
            + ");";

    private static final String CREATE_TABLE_ORDER = "CREATE TABLE " + DatabaseStringRes.TABLE_ORDER + " ("
            + DatabaseStringRes.COL_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"
            + ", " + DatabaseStringRes.COL_ORDER_PRODUCT_ID + " INTEGER NOT NULL"
            + ", " + DatabaseStringRes.COL_ORDER_PRODUCT_AMOUNT + " INTEGER NOT NULL"
            + ", " + DatabaseStringRes.COL_ORDER_BUYER_NAME + " TEXT NOT NULL"
            + ", " + DatabaseStringRes.COL_ORDER_PAID_STATUS + " INTEGER NOT NULL"
            + ", " + "FOREIGN KEY(" + DatabaseStringRes.COL_ORDER_PRODUCT_AMOUNT + ") REFERENCES " + DatabaseStringRes.TABLE_SHUTTLE + "(" + DatabaseStringRes.COL_SHUTTLE_ID +")"
            + ");";

    private static final String INSERT_SHUTTLE_DATA = "INSERT INTO " + DatabaseStringRes.TABLE_SHUTTLE + " VALUES ( null, 'Yonex', 'YONEX_AS30', 27.0, 500);"
            + "INSERT INTO " + DatabaseStringRes.TABLE_SHUTTLE + " VALUES ( null, 'RSL', 'Grade 3', 16.7, 5000);"
            + "INSERT INTO " + DatabaseStringRes.TABLE_SHUTTLE + " VALUES ( null, 'RSL', 'Grade A9', 13.7, 10000);"
            + "INSERT INTO " + DatabaseStringRes.TABLE_SHUTTLE + " VALUES ( null, 'RSL', 'Grade A1', 21.0, 6000);";

    private static final String INSERT_ORDER_DATA = "INSERT INTO " + DatabaseStringRes.TABLE_ORDER + " VALUES ( null, 0, 10, 'John Carter', 0);";

    private static final String CREATE_BDD = CREATE_TABLE_SHUTTLE + CREATE_TABLE_ORDER + INSERT_SHUTTLE_DATA + INSERT_ORDER_DATA;

    private static String DB_NAME = "TP2_PART5_BDD";
    private static int DB_VERSION = 1;
    private SQLiteDatabase mDataBase;

    public DataBaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public synchronized void close()
    {
        if(mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBOpenHelper", "Mise à jour de la version " + oldVersion
                + " vers la version " + newVersion
                + ", les anciennes données seront détruites ");
        // Drop the old database
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseStringRes.TABLE_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseStringRes.TABLE_SHUTTLE);
        // Create the new one
        onCreate(db);
    }
}
