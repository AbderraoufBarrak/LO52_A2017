package com.example.gl52.lo52_application_mages_BD;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sbouaala on 30/09/2017
 */


public class DbHelper extends SQLiteOpenHelper {

    /** Database name */
    private static final String DATABASE_NAME = "LO52_Project_Mages";

    /** Database version */
    private static final int DATABASE_VERSION = 1;

    /** Table names */
    private static final String VOLANTS         = "Volants";
    private static final String STOCK         = "Stocks";
    private static final String VENDU_PAR    = "Vendu_par";
    private static final String DISTRIBUTEUR     = "Distributeur";
    private static final String ACHETER   = "Acheteur";
    private static final String ACHETEUR   = "Acheter";


    /** Common column name and location */
    public static final String KEY_ROWID            = "id";

    /** VOLANTS table columns */
    private static final String KEY_REFERENCE        = "Reference";
    private static final String KEY_QUANTITE = "Quantite";
    private static final String KEY_ID_STOCK = "ID Stock";

    /** VOLANTS Table: create statement */
    private static final String CREATE_VOLANTS_TABLE =
            "create table " + VOLANTS_TABLE + "("
                    + KEY_ROWID + " integer primary key not null,"
                    + KEY_REFERENCE + " text not null,"
                    + KEY_ID_STOCK + " integer not null,"
                    + KEY_QUANTITE + " integer not null" + ")";
/*.............../


     */
    /** Constructor */
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    /** Creating tables */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VOLANTS_TABLE);
        db.execSQL(CREATE_ACHETER_TABLE);
        db.execSQL(CREATE_ACHETEUR_TABLE);
        db.execSQL(CREATE_VENDU_PAR_TABLE);
        db.execSQL(CREATE_DISTRIBUTEUR_TABLE);
        db.execSQL(CREATE_STOCK_TABLE);
    }

    @Override
    // TODO: change this so that updating doesn't delete old data
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + VOLANTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ACHETER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ACHETEUR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + STOCK_LINKS);
        db.execSQL("DROP TABLE IF EXISTS " + VENDU_PAR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DISTRIBUTEUR_TABLE);
        onCreate(db);
    }

// ############################## create methods ###################################### //

