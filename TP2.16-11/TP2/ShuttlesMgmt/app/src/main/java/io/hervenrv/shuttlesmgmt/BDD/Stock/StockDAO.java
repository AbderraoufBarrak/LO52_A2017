package io.hervenrv.shuttlesmgmt.BDD.Stock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 17/11/2017.
 */

public class StockDAO extends DAOStockBase {

    public StockDAO(Context context){
        super(context);
    }

    public void ajouter(Stock a){

        ContentValues value = new ContentValues();

        value.put(DBConst.STOCK_REF, a.getRef());
        value.put(DBConst.STOCK_QUANTITE, a.getQuantite());

        db.insert(DBConst.STOCK_TABLE_NAME, null, value);

    }

    public void supprimer(String ref){

        db.delete(DBConst.STOCK_TABLE_NAME, DBConst.STOCK_REF + " = ?", new String[]{ref});

    }

    public void modifier(Stock a){

        ContentValues value = new ContentValues();
        value.put(DBConst.STOCK_REF, a.getRef());
        value.put(DBConst.STOCK_QUANTITE, a.getQuantite());
        db.update(DBConst.STOCK_TABLE_NAME, value, DBConst.STOCK_REF + " = ?", new String[]{a.getRef()});

    }

    public Stock selectionner(String ref){

        Cursor c = db.rawQuery("select * from " + DBConst.STOCK_TABLE_NAME + " where ref = ?", new String[]{ref});

        Stock resultat = null;

        if(c.moveToFirst()) {
            int quantite = c.getInt(1);
            resultat = new Stock(ref, quantite);
        }

        c.close();

        return resultat;
    }

    public ArrayList<Stock> getList(){

        ArrayList<Stock> result = new ArrayList<>();

        Cursor c = db.rawQuery("select * from " + DBConst.STOCK_TABLE_NAME, null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {

                String ref = c.getString(0);
                int quantite = c.getInt(1);
                Stock elem = new Stock(ref, quantite);

                result.add(elem);
                c.moveToNext();
            }
        }

        c.close();

        return result;
    }


}
