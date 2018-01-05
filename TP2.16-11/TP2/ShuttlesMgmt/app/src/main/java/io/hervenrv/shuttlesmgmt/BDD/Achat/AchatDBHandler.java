package io.hervenrv.shuttlesmgmt.BDD.Achat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by basile on 15/11/17.
 */

public class AchatDBHandler extends SQLiteOpenHelper {


    public AchatDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(DBConst.ACHAT_TABLE_CREATE);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DBConst.ACHAT_TABLE_DROP);
        onCreate(db);
    }
}
