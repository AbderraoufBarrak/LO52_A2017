package com.example.kiera.shuttlesmgmt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by kiera on 27/10/2017.
 */

public class Achat_BDD extends DAO_Base{

    public static final String ID = "achat_id";
    public static final String ACHETEUR = "acheteur";
    public static final String REF = "reference";
    public static final String QTT = "qtt_achetee";
    public static final String PRIX = "prix";
    public static final String PAYE = "paye";

    public static final String TABLE = "LO52_ACHAT";
    private static final String CREATE =
            "CREATE TABLE " + TABLE + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ACHETEUR + " TEXT, " +
                    REF + " TEXT, " +
                    PRIX + " REAL, " +
                    PAYE + " INTEGER, " +
                    QTT + " INTEGER);";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE + ";";

    public Achat_BDD(Context pContext) {
        super(pContext);
        open();
    }

    public void ajouter(Achat a) {
        ContentValues value = new ContentValues();
        value.put(REF, a.getRef());
        value.put(ACHETEUR, a.getAcheteur());
        value.put(PRIX,a.getPrix());
        value.put(QTT,a.getQtt());
        value.put(PAYE,a.isPaye());
        mDb.insert(TABLE, null, value);

    }

    public void supprimer(int id) {
        mDb.delete(TABLE, REF + " = ?", new String[] {String.valueOf(id)});
    }

    public void modifier(Achat a) {
        ContentValues value = new ContentValues();
        value.put(PAYE, a.isPaye());
        mDb.update(TABLE, value, REF  + " = ?", new String[] {a.getRef()});

    }

    public ArrayList<Achat> selectAll() {
        ArrayList<Achat> list = new ArrayList<>();
        Cursor c = mDb.rawQuery("select "+ACHETEUR+", "+REF+", "+QTT+", "+PRIX+", "+PAYE+" from " + TABLE , null);

        while (c.moveToNext()){

            list.add(new Achat(c.getString(0),c.getString(1),c.getInt(2),c.getDouble(3),c.getInt(4)==1));
        }

        return list;
    }
}
