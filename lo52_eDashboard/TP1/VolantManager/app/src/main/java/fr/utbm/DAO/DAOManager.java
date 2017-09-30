package fr.utbm.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.utbm.database.DatabaseHandler;

/**
 * Created by Exige on 23/09/2017.
 */

public abstract class DAOManager {

    protected final static int VERSION = 2; // Incrémente-moi si la structure de la base de données change !
    protected final static String NAME = "database.db";

    protected SQLiteDatabase sqLiteDatabase = null;
    protected DatabaseHandler dbHandler = null;

    /**
     * Constructeur par défaut
     */
    public DAOManager() {

    }

    /**
     * Constructeur prenant en compte le contexte
     * @param pContext
     */
    public DAOManager(Context pContext) {
        this.dbHandler = new DatabaseHandler(pContext, NAME, null, VERSION);
    }

    /**
     * Méthode open
     * @return
     */
    public SQLiteDatabase open() {
        sqLiteDatabase = dbHandler.getWritableDatabase();
        return sqLiteDatabase;
    }

    /**
     * Méthode close
     */
    public void close() {
        sqLiteDatabase.close();
    }

    /**
     * Renvoie la variable faisant référence à la base de données
     * @return
     */
    public SQLiteDatabase getDb() {
        return sqLiteDatabase;
    }
}
