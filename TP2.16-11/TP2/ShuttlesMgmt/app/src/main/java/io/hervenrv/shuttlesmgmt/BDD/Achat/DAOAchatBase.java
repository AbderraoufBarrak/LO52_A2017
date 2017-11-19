package io.hervenrv.shuttlesmgmt.BDD.Achat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Utilisateur on 17/11/2017.
 */

public abstract class DAOAchatBase {

    protected final static int VERSION = 1;

    protected final static String NOM = "Achat.db";

    protected SQLiteDatabase mDb = null;

    protected AchatDBHandler mHandler = null;

    public DAOAchatBase(Context pContext) {

        this.mHandler = new AchatDBHandler(pContext, NOM, null, VERSION);

    }



    public SQLiteDatabase open() {

        mDb = mHandler.getWritableDatabase();

        return mDb;

    }

    public SQLiteDatabase readOpen(){

        mDb = mHandler.getReadableDatabase();

        return mDb;
    }



    public void close() {

        mDb.close();

    }



    public SQLiteDatabase getDb() {

        return mDb;

    }

}
