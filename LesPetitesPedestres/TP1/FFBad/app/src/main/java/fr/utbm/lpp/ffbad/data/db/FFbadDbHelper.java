package fr.utbm.lpp.ffbad.data.db;

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
    }

    private void deleteEntries(SQLiteDatabase db) {
        db.execSQL(FFBadDbContract.Shuttlecock.SQL_DELETE_ENTRIES);
        db.execSQL(FFBadDbContract.Trader.SQL_DELETE_ENTRIES);
        db.execSQL(FFBadDbContract.Customer.SQL_DELETE_ENTRIES);
        db.execSQL(FFBadDbContract.Sale.SQL_DELETE_ENTRIES);
        db.execSQL(FFBadDbContract.Purchase.SQL_DELETE_ENTRIES);
    }
}
