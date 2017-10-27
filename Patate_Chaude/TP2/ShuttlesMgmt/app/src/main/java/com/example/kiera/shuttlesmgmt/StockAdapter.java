package com.example.kiera.shuttlesmgmt;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kiera on 29/09/2017.
 */

public class StockAdapter extends ArrayAdapter<Stock>{

    public StockAdapter(Context context, List<Stock> a) {
        super(context, 0, a);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stock_card,parent, false);
        }

        StockViewHolder viewHolder = (StockViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new StockViewHolder();
            viewHolder.marque = (TextView) convertView.findViewById(R.id.marque);
            viewHolder.ref = (TextView) convertView.findViewById(R.id.ref);
            viewHolder.qtt = (TextView) convertView.findViewById(R.id.qtt);
            viewHolder.prix = (TextView) convertView.findViewById(R.id.prix);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Stock s = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.marque.setText(s.getMarque());
        viewHolder.ref.setText(s.getRef());
        viewHolder.qtt.setText(s.getQtt()+"");
        viewHolder.prix.setText(String.format("%.2f",s.getPrix())+" €");
        viewHolder.image.setImageResource(s.getImage());

        return convertView;
    }

    class StockViewHolder {
        public ImageView image;
        public TextView marque, ref, prix, qtt;
    }

}
