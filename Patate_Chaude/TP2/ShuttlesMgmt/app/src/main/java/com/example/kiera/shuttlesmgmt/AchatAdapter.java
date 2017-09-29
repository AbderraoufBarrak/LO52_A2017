package com.example.kiera.shuttlesmgmt;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class AchatAdapter extends ArrayAdapter<Achat> {

    public AchatAdapter(Context context, List<Achat> a) {
        super(context, 0, a);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stock_card,parent, false);
        }

        AchatAdapter.AchatViewHolder viewHolder = (AchatAdapter.AchatViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new AchatAdapter.AchatViewHolder();
            viewHolder.acheteur = (TextView) convertView.findViewById(R.id.marque);
            viewHolder.ref = (TextView) convertView.findViewById(R.id.ref);
            viewHolder.qtt = (TextView) convertView.findViewById(R.id.qtt);
            viewHolder.prix = (TextView) convertView.findViewById(R.id.prix);
            viewHolder.paye = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Achat s = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.acheteur.setText(s.getAcheteur());
        viewHolder.ref.setText(s.getRef());
        viewHolder.qtt.setText(s.getQtt()+"");
        viewHolder.prix.setText(s.getPrix()+"€");
        viewHolder.paye.setImageDrawable(new ColorDrawable(s.isPaye()? Color.GREEN : Color.RED));

        return convertView;
    }

    class AchatViewHolder {
        public ImageView paye;
        public TextView acheteur, ref, prix, qtt;
    }

}

