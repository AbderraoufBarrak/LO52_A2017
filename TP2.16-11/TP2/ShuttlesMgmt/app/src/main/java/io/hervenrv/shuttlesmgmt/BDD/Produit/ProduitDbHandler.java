package io.hervenrv.shuttlesmgmt.BDD.Produit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by basile on 15/11/17.
 */

public class ProduitDbHandler extends SQLiteOpenHelper {


    public ProduitDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db){

        //db.execSQL("CREATE TABLE STOCK()");
        db.execSQL(DBConst.PRODUIT_TABLE_CREATE);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DBConst.PRODUIT_TABLE_DROP);
        onCreate(db);
    }
}
