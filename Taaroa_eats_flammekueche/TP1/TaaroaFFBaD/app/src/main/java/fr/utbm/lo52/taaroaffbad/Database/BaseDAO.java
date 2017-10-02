package fr.utbm.lo52.taaroaffbad.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Jordan on 25/09/2017.
 */


public abstract class BaseDAO {

    //TODO: /!\ incrémenter s'il y a changement de la structure de la BDD
    protected final static int VERSION = 10;
    protected final static String NOM = "database.db";

    protected SQLiteDatabase sqlite = null;
    protected static Database handler = null;

    public BaseDAO(Context pContext) {
        this.handler = new Database(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        sqlite = handler.getWritableDatabase(); // ferme automatiquement la base
        return sqlite;
    }

    public void close() {
        sqlite.close();
    }

    public SQLiteDatabase getDb() {
        return sqlite;
    }
}