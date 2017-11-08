package com.example.shuttlesmgmt.adapter.Version2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.entity.Version2.Order;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Michel on 2017/11/2.
 */

public class OrderAdapter extends ArrayAdapter<Order>{
    public OrderAdapter(Context context, List<Order> objects) {
        super(context, 0, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int i, View v, ViewGroup group){
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_view_order, group, false);
        }
        OrderViewHolder vh = (OrderViewHolder) v.getTag();
        if(vh == null){
            vh = new OrderViewHolder();
            vh.buyer = (TextView) v.findViewById(R.id.id_buyer);
            vh.product = (TextView) v.findViewById(R.id.id_product);
            vh.date = (TextView) v.findViewById(R.id.id_date);
            vh.quantity = (TextView) v.findViewById(R.id.id_quantity);
            vh.price = (TextView) v.findViewById(R.id.id_price);

            v.setTag(vh);
        }

        Order pos = getItem(i);

        vh.buyer.setText(pos.getCustomerName());

        vh.date.setText(new SimpleDateFormat("dd/MM/yyyy").format(pos.getDate()));
        vh.product.setText(pos.getProductName());
        vh.quantity.setText(Integer.toString(pos.getQuantity()));
        vh.price.setText(Double.toString(pos.getPrice()) + " €");
        return v;
    }

    public class OrderViewHolder{
        public TextView buyer, product, date, quantity, price;
    }

    //partie tri
    public List<Order> sortList(List<Order> listOrder, int i){
        switch(i){
            case 0:
                //tri par prix
                Collections.sort(listOrder, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return Double.compare(o1.getPrice(), o2.getPrice());
                    }
                });
                break;
            case 1:
                //tri par quantite
                Collections.sort(listOrder, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return o1.getQuantity()-o2.getQuantity();
                    }
                });
                break;
            case 2:
                //tri par date
                Collections.sort(listOrder, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                });
                break;
            case 3:
                //tri par nom du client
                Collections.sort(listOrder, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return o1.getCustomerName().compareToIgnoreCase(o2.getCustomerName());
                    }
                });
                break;
            case 4:
                //tri par nom du produit
                Collections.sort(listOrder, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return o1.getProductName().compareToIgnoreCase(o2.getProductName());
                    }
                });
                break;
            case 5:
                //tri par si paye ou pas
                Collections.sort(listOrder, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return Boolean.compare(o1.getIsPaid(), o2.getIsPaid());
                    }
                });
                break;
        }
        return listOrder;
    }
}
