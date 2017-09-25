package fr.utbm.lo52.taaroaffbad.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Jordan on 25/09/2017.
 */


public abstract class BaseDAO {
    // Nous sommes à la première version de la base
    // Si je décide de la mettre à jour, il faudra changer cet attribut
    protected final static int VERSION = 1;
    // Le nom du fichier qui représente ma base
    protected final static String NOM = "database.db";

    protected SQLiteDatabase sqlite = null;
    protected static Database handler = null;

    public BaseDAO(Context pContext) {
        this.handler = new Database(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        sqlite = handler.getWritableDatabase();
        return sqlite;
    }

    public void close() {
        sqlite.close();
    }

    public SQLiteDatabase getDb() {
        return sqlite;
    }
}