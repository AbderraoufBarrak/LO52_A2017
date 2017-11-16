package com.lo52.dewback.shuttlesmgmt.stock_activity.model.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lo52.dewback.shuttlesmgmt.stock_activity.model.IOrderDao;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.beans.OrderDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Notmoo on 16/11/2017.
 */

public class SQLiteOrderDao implements IOrderDao {

    private DataBaseHelper dbHelper;

    public SQLiteOrderDao(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    @Override
    public List<OrderDataBean> getOrders() {
        return queryWithFilter(null);
    }

    @Override
    public OrderDataBean getOrder(Integer orderId) {
        return queryWithFilter(DatabaseStringRes.COL_ORDER_ID + "="+orderId).get(0);
    }

    private List<OrderDataBean> queryWithFilter(String filter){
        SQLiteDatabase bdd = dbHelper.getReadableDatabase();
        Cursor c = bdd.query(
                DatabaseStringRes.TABLE_ORDER,
                new String[] {DatabaseStringRes.COL_ORDER_ID,
                        DatabaseStringRes.COL_ORDER_PRODUCT_ID,
                        DatabaseStringRes.COL_ORDER_PRODUCT_AMOUNT,
                        DatabaseStringRes.COL_ORDER_BUYER_NAME,
                        DatabaseStringRes.COL_ORDER_PAID_STATUS},
                filter,
                null,
                null,
                null,
                null);
        bdd.close();
        return cursorToOrders(c);
    }

    private List<OrderDataBean> cursorToOrders(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return new ArrayList<>();

        List<OrderDataBean> orders = new ArrayList<>();
        //Sinon on se place sur le premier élément
        c.moveToFirst();
        boolean firstLoop = true;
        do{
            if(firstLoop)
                firstLoop = false;
            else
                c.moveToNext();

            OrderDataBean order = new OrderDataBean();
            order.setOrderId(c.getInt(0));
            order.setProductId(c.getInt(1));
            order.setProductAmount(c.getInt(2));
            order.setBuyerName(c.getString(3));
            order.setOrderPaid(c.getInt(4)!=0);
            orders.add(order);
        }while(!c.isLast());
        c.close();

        return orders;
    }
}
