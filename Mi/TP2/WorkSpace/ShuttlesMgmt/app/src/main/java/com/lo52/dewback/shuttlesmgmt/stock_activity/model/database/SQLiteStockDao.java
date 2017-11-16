package com.lo52.dewback.shuttlesmgmt.stock_activity.model.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lo52.dewback.shuttlesmgmt.stock_activity.model.IStockDao;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.beans.StockDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Notmoo on 16/11/2017.
 */

public class SQLiteStockDao implements IStockDao {
    private DataBaseHelper dbHelper;

    public SQLiteStockDao(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    private List<StockDataBean> queryWithFilter(String filter){
        SQLiteDatabase bdd = dbHelper.getReadableDatabase();
        Cursor c = bdd.query(
                DatabaseStringRes.TABLE_SHUTTLE,
                new String[] {DatabaseStringRes.COL_SHUTTLE_ID,
                        DatabaseStringRes.COL_SHUTTLE_BRAND,
                        DatabaseStringRes.COL_SHUTTLE_NAME,
                        DatabaseStringRes.COL_SHUTTLE_PRICE,
                        DatabaseStringRes.COL_SHUTTLE_STOCK},
                filter,
                null,
                null,
                null,
                null);
        bdd.close();
        return cursorToStock(c);
    }

    private List<StockDataBean> cursorToStock(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return new ArrayList<>();

        List<StockDataBean> stock = new ArrayList<>();
        //Sinon on se place sur le premier élément
        c.moveToFirst();
        boolean firstLoop = true;
        do{
            if(firstLoop)
                firstLoop = false;
            else
                c.moveToNext();

            StockDataBean stockItem = new StockDataBean();
            stockItem.setId(c.getInt(0));
            stockItem.setBrand(c.getString(1));
            stockItem.setName(c.getString(2));
            stockItem.setPrice(c.getDouble(3));
            stockItem.setStock(c.getInt(4));
            stock.add(stockItem);
        }while(!c.isLast());
        c.close();

        return stock;
    }

    @Override
    public List<StockDataBean> getStock() {
        return queryWithFilter(null);
    }
}
