package fr.utbm.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.utbm.database.DatabaseHandler;

/**
 * Created by Exige on 23/09/2017.
 */

public abstract class DAOManager {

    protected final static int VERSION = 1;
    protected final static String NAME = "database.db";

    protected SQLiteDatabase sqLiteDatabase = null;
    protected DatabaseHandler dbHandler = null;

    public DAOManager() {

    }

    public DAOManager(Context pContext) {
        this.dbHandler = new DatabaseHandler(pContext, NAME, null, VERSION);
    }

    public SQLiteDatabase open() {
        sqLiteDatabase = dbHandler.getWritableDatabase();
        return sqLiteDatabase;
    }

    public void close() {
        sqLiteDatabase.close();
    }

    public SQLiteDatabase getDb() {
        return sqLiteDatabase;
    }
}
