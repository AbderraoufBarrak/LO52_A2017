package io.hervenrv.shuttlesmgmt.BDD.Produit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Utilisateur on 17/11/2017.
 */

public abstract class DAOProduitBase {

    protected final static int VERSION = 1;

    protected final static String NOM = "Produit.db";

    protected SQLiteDatabase db = null;

    protected ProduitDbHandler mHandler = null;

    public DAOProduitBase(Context pContext) {
        this.mHandler = new ProduitDbHandler(pContext, NOM, null, VERSION);
    }



    public SQLiteDatabase open() {
        db = mHandler.getWritableDatabase();
        return db;
    }

    public SQLiteDatabase openRead(){
        db = mHandler.getReadableDatabase();
        return db;
    }



    public void close() {
        db.close();
    }



    public SQLiteDatabase getDb() {
        return db;
    }

}
