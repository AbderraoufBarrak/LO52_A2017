package com.example.shuttlesmgmt.adapter.Version2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.entity.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
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
}
