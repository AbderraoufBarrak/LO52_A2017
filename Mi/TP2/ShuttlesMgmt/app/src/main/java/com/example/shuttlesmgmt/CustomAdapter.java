package com.example.shuttlesmgmt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shuttlesmgmt.R;

/**
 * Created by Michel on 2017/9/29.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    String Marque[];
    String Reference[];
    int Stock[];
    int image[];
    LayoutInflater inflater;

    public CustomAdapter(Context context, Context app, String[] marque, String[] reference,int[] image, int[] stock ){
        this.context = app;
        this.Marque = marque;
        this.Reference = reference;
        this.Stock = stock;
        this.image = image;
        inflater = (LayoutInflater.from(app));
    }

    @Override
    public int getCount() {
        return Reference.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewgroup){
        view = inflater.inflate(R.layout.activity_stock, null);
        TextView marque = (TextView) view.findViewById(R.id.text_marque);
        TextView reference = (TextView) view.findViewById(R.id.text_ref);
        TextView stock = (TextView) view.findViewById(R.id.text_stock);
        ImageView icon = (ImageView) view.findViewById(R.id.image);
        marque.setText(Marque[i]);
        reference.setText(Reference[i]);
        stock.setText(Stock[i]);
        icon.setImageResource(image[i]);
        return view;
    }
}
