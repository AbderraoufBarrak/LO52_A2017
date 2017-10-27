package com.example.kiera.shuttlesmgmt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by kiera on 27/10/2017.
 */

public class Stock_BDD extends DAO_Base{
    public static final String REF = "reference";
    public static final String MARQUE = "marque_id";
    public static final String IMG = "img_tube";
    public static final String PRIX = "prix_unit";
    public static final String QTT = "quantite";

    public static final String TABLE = "LO52_STOCK";
    private static final String CREATE =
            "CREATE TABLE " + TABLE + " (" +
                    REF + " TEXT PRIMARY KEY, " +
                    IMG + " INTEGER, " +
                    MARQUE + " TEXT, " +
                    PRIX + " REAL, " +
                    QTT + " INTEGER);";

    public static final String STOCK_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE + ";";

    public Stock_BDD(Context pContext) {
        super(pContext);
        open();
    }

    public void ajouter(Stock s) {
        ContentValues value = new ContentValues();
        value.put(REF, s.getRef());
        value.put(MARQUE, s.getMarque());
        value.put(IMG, s.getImage());
        value.put(PRIX,s.getPrix());
        value.put(QTT,s.getQtt());
        mDb.insert(TABLE, null, value);

    }

    public void supprimer(String ref) {
        mDb.delete(TABLE, REF + " = ?", new String[] {ref});
    }

    public void modifier(Stock s) {
        ContentValues value = new ContentValues();
        value.put(QTT, s.getQtt());
        mDb.update(TABLE, value, REF  + " = ?", new String[] {s.getRef()});

    }

    public ArrayList<Stock> selectAll() {
        ArrayList<Stock> list = new ArrayList<>();
        Cursor c = mDb.rawQuery("select "+IMG+", "+REF+", "+MARQUE+", "+QTT+", "+PRIX+" from " + TABLE , null);

        while (c.moveToNext()){
            list.add(new Stock(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3),c.getDouble(4)));
        }

        return list;
    }

    public ArrayList<String> selectRefs() {
        ArrayList<String> list = new ArrayList<>();
        Cursor c = mDb.rawQuery("select "+REF+" from " + TABLE , null);

        while (c.moveToNext()){

            list.add(c.getString(0));
        }

        return list;
    }

    public double getPrix(String ref){
        Cursor c = mDb.rawQuery("select "+PRIX+" from " + TABLE + " where " + REF + " = ?", new String[]{ref});
        if (c.moveToFirst()){
            return c.getDouble(0);
        }
        return 0;
    }
}

