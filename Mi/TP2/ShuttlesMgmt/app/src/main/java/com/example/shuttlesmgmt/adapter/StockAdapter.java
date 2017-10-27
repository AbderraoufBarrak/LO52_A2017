package com.example.shuttlesmgmt.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.entity.Stock;

import java.util.List;

/**
 * Created by Michel on 2017/9/29.
 */

public class StockAdapter extends ArrayAdapter<Stock> {

    String id;
    int idDrawable;

    public StockAdapter(Context app, List<Stock> object){
       super(app, 0, object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewgroup){

        if(view == null){
            view =  LayoutInflater.from(getContext()).inflate(R.layout.activity_listview_stock, viewgroup, false);
        }
        StockViewHolder viewHolder = (StockViewHolder) view.getTag();
        if(viewHolder == null){
            viewHolder = new StockViewHolder();
            viewHolder.marque = (TextView) view.findViewById(R.id.text_marque);
            viewHolder.ref = (TextView) view.findViewById(R.id.text_ref);
            viewHolder.qtt = (TextView) view.findViewById(R.id.text_stock);
            viewHolder.image = (ImageView) view.findViewById(R.id.image);
            viewHolder.prix = (TextView) view.findViewById(R.id.text_prix);
            view.setTag(viewHolder);
        }

        Stock s = getItem(i);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.marque.setText(s.getMarque());
        viewHolder.ref.setText(s.getRef());
        viewHolder.qtt.setText(s.getQuantite());
        viewHolder.prix.setText(s.getPrix() + "€");
        id = s.getImage();
        Log.i("AppInfo", "test");
        //converti de string en int
        idDrawable = getContext().getResources().getIdentifier(id,"drawable", getContext().getPackageName());
        viewHolder.image.setImageResource(idDrawable);

        return view;
    }

    class StockViewHolder {
        public ImageView image;
        public TextView marque, ref, qtt, prix;
    }
}
