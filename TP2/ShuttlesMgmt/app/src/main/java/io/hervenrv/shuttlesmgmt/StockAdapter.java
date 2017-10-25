package io.hervenrv.shuttlesmgmt;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by basile on 21/10/17.
 *
 */

public class StockAdapter extends ArrayAdapter {

    public StockAdapter(Context context, List<ListEntry> entries) {
        super(context, 0, entries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stock_list_entry,parent, false);
        }

        StockViewHolder viewHolder = (StockViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new StockViewHolder();
            viewHolder.marque = (TextView) convertView.findViewById(R.id.marque);
            viewHolder.ref = (TextView) convertView.findViewById(R.id.ref);
            viewHolder.stock = (TextView) convertView.findViewById(R.id.stock);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        ListEntry elem = (ListEntry) getItem(position);
        viewHolder.marque.setText(elem.getMarque());
        viewHolder.ref.setText(elem.getRef());
        viewHolder.stock.setText("" + elem.getStock());
        viewHolder.image.setImageResource(elem.getImage());


        return convertView;
    }

    private class StockViewHolder{
        public TextView marque;
        public TextView ref;
        public TextView stock;
        public ImageView image;
    }
}
