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
    private static final String VOLANTS = "Volants";
    private static final String STOCK  = "Stocks";
    private static final String VENDU_PAR = "Vendu_par";
    private static final String DISTRIBUTEUR = "Distributeur";
    private static final String ACHETER= "Acheteur";
    private static final String ACHETEUR = "Acheter";
    ///////////To remove or not? //
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    /** Common column name and location */
    public static final String KEY_ROWID= "id";

    /** VOLANTS table columns */
    private static final String KEY_REFERENCE= "Reference";
    private static final String KEY_QUANTITE = "Quantite";
    private static final String KEY_ID_STOCK = "ID Stock";
    /** VENDU_PAR table columns */
    private static final String KEY_REFERENCE= "Reference";
    private static final String KEY_QUANTITE = "Quantite";
    private static final String KEY_ID_DISTRIBUTEUR = "ID Distributeur";
    private static final String KEY_PRIX = "Prix";
    /** STOCKS table columns */
    private static final String ID_STOCK= "Id Stock";
    private static final String NOM_STOCK = "Nom Stock";
    /** DISTRIBUTEUR table columns */
    private static final String KEY_ID_DISTRIBUTEUR = "ID Distributeur";
    // ACHETER
    public static final String ACHETER_TABLE = "ACHETER";
    public static final String ACHETER_REFERENCE = "REFERENCE";
    public static final String ACHETER_QUANTITE = "QUANTITE";
    public static final String ACHETER_ID_ACHETEUR = "ID_ACHETEUR";
    public static final String ACHETER_QUAN = "QUAN";
    public static final String ACHETER_PAI = "PAI";
    // ACHETEUR
    public static final String ACHETEUR_TABLE = "ACHETEUR";
    public static final String ACHETEUR_ID_ACHETEUR = "ID_ACHETEUR";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LO52_Project_Mages.db";
    private static final String TAG = "ActivityDbHelper";

    /**Creation tables*/

    /** VENDU_PAR Table: create statement */
    private static final String CREATE_VENDU_PAR_TABLE =
            "create table " + VENDU_PAR + "("
                    + KEY_REFERENCE + " integer primary key not null,"
                    + KEY_QUANTITE + " integer primary key not null,"
                    + KEY_ID_DISTRIBUTEUR+ " integer primary key not null,"
                    + KEY_PRIX + " integer primary key not null" + ")";

    /** DISTRIBUTEUR Table: create statement */
    private static final String CREATE_DISTRIBUTEUR_TABLE =
            "create table " + DISTRIBUTEUR + "("
                    + KEY_ID_DISTRIBUTEUR+ " integer primary key not null"
                    + KEY_NOM_STOCK + " integer primary key not null,"+ ")";
    /** STOCKS Table: create statement */
    private static final String CREATE_STOCKS_TABLE =
            "create table " + STOCKS + "("
                    + KEY_ID_STOCK+ " integer primary key not null"
                    + KEY_NOM_STOCK + " integer primary key not null,"+ ")";
    /** VOLANTS Table: create statement */
    private static final String CREATE_VOLANTS_TABLE =
            "create table " + VOLANTS_TABLE + "("
                    + KEY_ROWID + " integer primary key not null,"
                    + KEY_REFERENCE + " text not null,"
                    + KEY_ID_STOCK + " integer not null,"
                    + KEY_QUANTITE + " integer not null" + ")";


       /**ACHETER TABLE **/
private static final String SQL_CREATE_ACHETER_TABLE =
        "CREATE TABLE " + ACHETER_TABLE + " ("
                + ACHETER_REFERENCE + INTEGER_TYPE + " PRIMARY KEY,"
                + ACHETER_QUANTITE + INTEGER_TYPE + COMMA_SEP
                + ACHETER_ID_ACHETEUR + INTEGER_TYPE + COMMA_SEP
                + ACHETER_QUAN + INTEGER_TYPE + COMMA_SEP
                + ACHETER_PAI + INTEGER_TYPE
                + " )";


    /**ACHETEUR TABLE*/
    private static final String SQL_CREATE_ACHETEUR_TABLE =
            "CREATE TABLE " + ACHETEUR_TABLE + " ("
                    + ACHETEUR_ID_ACHETEUR + INTEGER_TYPE + " PRIMARY KEY"
                    + " )";
    private static final String SQL_DELETE_VOLANTS = "DROP TABLE IF EXISTS " + VOLANTS_TABLE;
    private static final String SQL_DELETE_ACHETER = "DROP TABLE IF EXISTS " + ACHETER_TABLE;
    private static final String SQL_DELETE_ACHETEUR = "DROP TABLE IF EXISTS " + ACHETEUR_TABLE;




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

