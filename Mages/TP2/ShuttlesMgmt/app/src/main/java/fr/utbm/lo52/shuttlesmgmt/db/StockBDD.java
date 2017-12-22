package fr.utbm.lo52.shuttlesmgmt.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by sbouaala on 30/09/2017
 */

public class Stock extends DAO_Base {
        public void ajouter(Stock stock) {
        ContentValues value = new ContentValues();
        value.put(STOCK_REFERENCE, stock.getReference());
        value.put(STOCK_MARQUE, stock.getMarque());
        value.put(IMAGE, stock.getImage());
        value.put(PRIX,stock.getPrix());
        value.put(QUANTITE, stock.getQuantite());
        db.insert(STOCK_TABLE, null, value);

    }

    public void supprimer(String ref) {
       db.delete(STOCK_TABLE, STOCK_REFERENCE + " = ?", new String[] {ref});
    }

    public void modifier(Stock stock) {
        ContentValues value = new ContentValues();
        value.put(QUANTIE, stock.getQuantite());
        db.update(TABLE_STOCK, value, STOCK_REFERENCE  + " = ?", new String[] {stock.getReference()});

    }

    public ArrayList<Stock> selectAll() {
        ArrayList<Stock> list = new ArrayList<>();
        Cursor c = db.rawQuery("select "+IMAGE+", "+STOCK_REFERENCE+", "+STOCK_MARQUE+", "+QUANTITE+", "+PRIX+" from " + TABLE_STOCK , null);

        while (c.moveToNext()){
            list.add(new Stock(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3),c.getDouble(4)));
        }

        return list;
    }

    public ArrayList<String> selectReferencess() {
        ArrayList<String> list = new ArrayList<>();
        Cursor c = db.rawQuery("select "+STOCK_REFERENCE+" from " + TABLE_STOCK , null);

        while (c.moveToNext()){

            list.add(c.getString(0));
        }

        return list;
    }

    public double getPrix(String ref){
        Cursor c = db.rawQuery("select "+PRIX+" from " + TABLE_STOCK + " where " + STOCK_REFERENCE + " = ?", new String[]{reference});
        if (c.moveToFirst()){
            return c.getDouble(0);
        }
        return 0;
    }

    public int getQuantite(String ref){
        Cursor c = db.rawQuery("select "+QUANTITE+" from " + STOCK_TABLE + " where " + STOCK_REFERENCE + " = ?", new String[]{reference});
        if (c.moveToFirst()){
            return c.getInt(0);
        }
        return 0;
    }
}