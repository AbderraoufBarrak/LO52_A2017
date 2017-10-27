package com.example.kiera.shuttlesmgmt;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kiera on 27/10/2017.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    public static final String STOCK_REF = "reference";
    public static final String STOCK_MARQUE = "marque_id";
    public static final String STOCK_IMG = "img_tube";
    public static final String STOCK_PRIX = "prix_unit";
    public static final String STOCK_QTT = "quantite";

    public static final String STOCK_TABLE = "LO52_STOCK";
    private static final String STOCK_CREATE =
            "CREATE TABLE " + STOCK_TABLE + " (" +
            STOCK_REF + " TEXT PRIMARY KEY, " +
            STOCK_MARQUE + " TEXT, " +
            STOCK_IMG + " INTEGER, " +
            STOCK_PRIX + " REAL, " +
            STOCK_QTT + " INTEGER);";

    public static final String STOCK_TABLE_DROP = "DROP TABLE IF EXISTS " + STOCK_TABLE + ";";


    public static final String ACHAT_ID = "achat_id";
    public static final String ACHAT_ACHETEUR = "acheteur";
    public static final String ACHAT_REF = "reference";
    public static final String ACHAT_QTT = "qtt_achetee";
    public static final String ACHAT_PRIX = "prix";
    public static final String ACHAT_PAYE = "paye";

    public static final String ACHAT_TABLE = "LO52_ACHAT";
    private static final String ACHAT_CREATE =
            "CREATE TABLE " + ACHAT_TABLE + " (" +
                    ACHAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ACHAT_ACHETEUR + " TEXT, " +
                    ACHAT_REF + " TEXT, " +
                    ACHAT_PRIX + " REAL, " +
                    ACHAT_PAYE + " INTEGER, " +
                    ACHAT_QTT + " INTEGER);";


    public static final String ACHAT_TABLE_DROP = "DROP TABLE IF EXISTS " + ACHAT_TABLE + ";";


    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STOCK_CREATE);
        loadStockDefault(db);
        db.execSQL(ACHAT_CREATE);
        loadAchatDefault(db);
    }

    private void loadAchatDefault(SQLiteDatabase db) {
        ContentValues v1 = new ContentValues();
        v1.put(ACHAT_REF, "AS30");
        v1.put(ACHAT_ACHETEUR, "Madame Michu");
        v1.put(ACHAT_QTT, 1);
        v1.put(ACHAT_PRIX, 27.0);
        v1.put(ACHAT_PAYE, 1);
        db.insert(ACHAT_TABLE, null, v1);

        ContentValues v2 = new ContentValues();
        v2.put(ACHAT_REF, "Grade 3");
        v2.put(ACHAT_ACHETEUR, "Madame Michu");
        v2.put(ACHAT_QTT, 1);
        v2.put(ACHAT_PRIX, 16.7);
        v2.put(ACHAT_PAYE, 0);
        db.insert(ACHAT_TABLE, null, v2);

        ContentValues v3 = new ContentValues();
        v3.put(ACHAT_REF, "AS30");
        v3.put(ACHAT_ACHETEUR, "Societe Racket");
        v3.put(ACHAT_QTT, 20);
        v3.put(ACHAT_PRIX, 540.0);
        v3.put(ACHAT_PAYE, 0);
        db.insert(ACHAT_TABLE, null, v3);

        ContentValues v4 = new ContentValues();
        v4.put(ACHAT_REF, "Grade A9");
        v4.put(ACHAT_ACHETEUR, "Societe Racket");
        v4.put(ACHAT_QTT, 10);
        v4.put(ACHAT_PRIX, 137.0);
        v4.put(ACHAT_PAYE, 1);
        db.insert(ACHAT_TABLE, null, v4);
    }

    private void loadStockDefault(SQLiteDatabase db) {
        ContentValues v1 = new ContentValues();
        v1.put(STOCK_REF, "AS30");
        v1.put(STOCK_IMG, R.drawable.t1);
        v1.put(STOCK_MARQUE, "Yonex");
        v1.put(STOCK_PRIX, 27.0);
        v1.put(STOCK_QTT, 500);
        db.insert(STOCK_TABLE, null, v1);

        ContentValues v2 = new ContentValues();
        v2.put(STOCK_REF, "Grade 3");
        v2.put(STOCK_IMG, R.drawable.t2);
        v2.put(STOCK_MARQUE, "RSL");
        v2.put(STOCK_PRIX, 16.7);
        v2.put(STOCK_QTT, 5000);
        db.insert(STOCK_TABLE, null, v2);

        ContentValues v3 = new ContentValues();
        v3.put(STOCK_REF, "Grade A9");
        v3.put(STOCK_IMG, R.drawable.t3);
        v3.put(STOCK_MARQUE, "RSL");
        v3.put(STOCK_PRIX, 13.7);
        v3.put(STOCK_QTT, 10000);
        db.insert(STOCK_TABLE, null, v3);

        ContentValues v4 = new ContentValues();
        v4.put(STOCK_REF, "Grade A1");
        v4.put(STOCK_IMG, R.drawable.t4);
        v4.put(STOCK_MARQUE, "RSL");
        v4.put(STOCK_PRIX, 21.0);
        v4.put(STOCK_QTT, 6000);
        db.insert(STOCK_TABLE, null, v4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(STOCK_TABLE_DROP);
        db.execSQL(ACHAT_TABLE_DROP);
        onCreate(db);
    }
}
