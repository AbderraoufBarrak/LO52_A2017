package fr.utbm.lo52.shuttlesmgmt.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import fr.utbm.lo52.shuttlesmgmt.models.Acheter;
import fr.utbm.lo52.shuttlesmgmt.models.Acheteur;
import fr.utbm.lo52.shuttlesmgmt.models.Volants;


/**
 * Created by ennajihihoussame on 03/10/2017.
 */

public class ActivityDbHelper extends SQLiteOpenHelper {

    //// VOLANTS
    public static final String VOLANTS_TABLE = "VOLANTS";
    public static final String VOLANTS_REFERENCE = "REFERENCE";
    public static final String VOLANTS_QUANTITE = "QUANTITE";
    public static final String VOLANTS_ID_STOCK = "ID_STOCK";
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
    ///////////
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_VOLANTS_TABLE =
            "CREATE TABLE " + VOLANTS_TABLE + " ("
                    + VOLANTS_REFERENCE + INTEGER_TYPE + " PRIMARY KEY,"
                    + VOLANTS_QUANTITE + INTEGER_TYPE + COMMA_SEP
                    + VOLANTS_ID_STOCK + INTEGER_TYPE
                    + " )";
    private static final String SQL_CREATE_ACHETER_TABLE =
            "CREATE TABLE " + ACHETER_TABLE + " ("
                    + ACHETER_REFERENCE + INTEGER_TYPE + " PRIMARY KEY,"
                    + ACHETER_QUANTITE + INTEGER_TYPE + COMMA_SEP
                    + ACHETER_ID_ACHETEUR + INTEGER_TYPE + COMMA_SEP
                    + ACHETER_QUAN + INTEGER_TYPE + COMMA_SEP
                    + ACHETER_PAI + INTEGER_TYPE
                    + " )";
    private static final String SQL_CREATE_ACHETEUR_TABLE =
            "CREATE TABLE " + ACHETEUR_TABLE + " ("
                    + ACHETEUR_ID_ACHETEUR + INTEGER_TYPE + " PRIMARY KEY"
                    + " )";
    private static final String SQL_DELETE_VOLANTS = "DROP TABLE IF EXISTS " + VOLANTS_TABLE;
    private static final String SQL_DELETE_ACHETER = "DROP TABLE IF EXISTS " + ACHETER_TABLE;
    private static final String SQL_DELETE_ACHETEUR = "DROP TABLE IF EXISTS " + ACHETEUR_TABLE;

    public ActivityDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_VOLANTS_TABLE);
        db.execSQL(SQL_CREATE_ACHETER_TABLE);
        db.execSQL(SQL_CREATE_ACHETEUR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_VOLANTS);
        db.execSQL(SQL_DELETE_ACHETER);
        db.execSQL(SQL_DELETE_ACHETEUR);
        onCreate(db);
    }


    public final long insertVolants(Volants entry) {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "Inserting Volants");
        }
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(VOLANTS_REFERENCE, entry.getReference());
        values.put(VOLANTS_QUANTITE, entry.getQuantite());
        values.put(VOLANTS_ID_STOCK, entry.getId_stock());
        // Insert the new row, returning the primary key value of the new row
        return db.insert(VOLANTS_TABLE, "null", values);
    }

    public final long insertAcheteur(Acheteur entry) {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "Inserting ACHETEUR");
        }
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ACHETEUR_ID_ACHETEUR, entry.getId_acheteur());
        // Insert the new row, returning the primary key value of the new row
        return db.insert(ACHETEUR_TABLE, "null", values);
    }

    public final long insertAcheter(Acheter entry) {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "Inserting ACHETER");
        }
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ACHETER_REFERENCE, entry.getReference());
        values.put(ACHETER_QUANTITE, entry.getQuantite());
        values.put(ACHETER_ID_ACHETEUR, entry.getId_acheteur());
        values.put(ACHETER_QUAN, entry.getQuan());
        values.put(ACHETER_PAI, entry.getPai());
        // Insert the new row, returning the primary key value of the new row
        return db.insert(ACHETER_TABLE, "null", values);
    }

    public final ArrayList<Volants> readVolants() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                VOLANTS_REFERENCE,
                VOLANTS_QUANTITE,
                VOLANTS_ID_STOCK
        };

        // sort ASC based on the time of the entry
        String sortOrder = VOLANTS_REFERENCE + " ASC";
        String selection = null;

        Cursor cursor = db.query(
                VOLANTS_TABLE,              // The table to query
                projection,                 // The columns to return
                selection,                  // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                       // don't group the rows
                null,                       // don't filter by row groups
                sortOrder                   // The sort order
        );

        ArrayList<Volants> result = new ArrayList<Volants>();
        int count = cursor.getCount();
        if (count > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Volants entry = new Volants(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
                result.add(entry);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return result;
    }
    public final ArrayList<Acheteur> readAcheteur() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                ACHETEUR_ID_ACHETEUR
        };

        // sort ASC based on the time of the entry
        String sortOrder = ACHETEUR_ID_ACHETEUR + " ASC";
        String selection = null;

        Cursor cursor = db.query(
                ACHETEUR_TABLE,              // The table to query
                projection,                 // The columns to return
                selection,                  // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                       // don't group the rows
                null,                       // don't filter by row groups
                sortOrder                   // The sort order
        );

        ArrayList<Acheteur> result = new ArrayList<Acheteur>();
        int count = cursor.getCount();
        if (count > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Acheteur entry = new Acheteur(cursor.getInt(0));
                result.add(entry);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return result;
    }
    public final ArrayList<Acheter> readAcheter() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                ACHETER_REFERENCE,
                ACHETER_QUANTITE,
                ACHETER_ID_ACHETEUR,
                ACHETER_QUAN,
                ACHETER_PAI
        };

        // sort ASC based on the time of the entry
        String sortOrder = ACHETER_ID_ACHETEUR + " ASC";
        String selection = null;

        Cursor cursor = db.query(
                ACHETER_TABLE,              // The table to query
                projection,                 // The columns to return
                selection,                  // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                       // don't group the rows
                null,                       // don't filter by row groups
                sortOrder                   // The sort order
        );

        ArrayList<Acheter> result = new ArrayList<Acheter>();
        int count = cursor.getCount();
        if (count > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Acheter entry = new Acheter(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
                result.add(entry);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return result;
    }

    public final int deleteAllFromVolants() {
        Log.d(TAG, "Delete all lines from Volants !!");
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(VOLANTS_TABLE, null, null);
    }
    public final int deleteAllFromAcheter() {
        Log.d(TAG, "Delete all lines Acheter !!");
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(ACHETER_TABLE, null, null);
    }
    public final int deleteAllFromAcheteur() {
        Log.d(TAG, "Delete all lines acheteur !!");
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(ACHETEUR_TABLE, null, null);
    }
}
