package com.example.shuttlesmgmt.adapter.Version1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shuttlesmgmt.entity.Version1.Achat;
import com.example.shuttlesmgmt.R;

import java.util.List;

/**
 * Created by Michel on 2017/10/7.
 */

public class AchatAdapter extends ArrayAdapter<Achat> {
    private String id;
    private int idDrawable;

    public AchatAdapter(Context app, List<Achat> object){
        super(app, 0, object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewgroup){

        if(view == null){
            view =  LayoutInflater.from(getContext()).inflate(R.layout.activity_listview_achat, viewgroup, false);
        }
        AchatAdapter.AchatViewHolder viewHolder = (AchatAdapter.AchatViewHolder) view.getTag();
        if(viewHolder == null){
            viewHolder = new AchatAdapter.AchatViewHolder();
            viewHolder.marque = (TextView) view.findViewById(R.id.text_marque);
            viewHolder.ref = (TextView) view.findViewById(R.id.text_reference);
            viewHolder.qtt = (TextView) view.findViewById(R.id.text_quantite);
            viewHolder.image = (ImageView) view.findViewById(R.id.image);
            viewHolder.prix = (TextView) view.findViewById(R.id.text_prix);
            viewHolder.acheteur = (TextView) view.findViewById(R.id.text_acheteur);
            viewHolder.idPayer = (ImageView) view.findViewById(R.id.idColor);
            view.setTag(viewHolder);
        }

        //recupere la position
        Achat a = getItem(i);

        viewHolder.marque.setText(a.getMarque());
        viewHolder.ref.setText(a.getRef());
        viewHolder.qtt.setText(a.getQuantite() + " unité");
        viewHolder.prix.setText(a.getPrix() + "€");
        viewHolder.acheteur.setText(a.getAcheteur());
        id = a.getImage();

        //converti de string en int
        idDrawable = getContext().getResources().getIdentifier(id,"drawable", getContext().getPackageName());
        viewHolder.image.setImageResource(idDrawable);

        if(a.getPayer()=="y" || a.getPayer().contentEquals("y")){
            viewHolder.idPayer.setColorFilter(Color.GREEN);
        }else{
            viewHolder.idPayer.setColorFilter(Color.RED);
        }
        return view;
    }

    class AchatViewHolder {
        public ImageView image, idPayer;
        public TextView marque, ref, qtt, prix, acheteur;
    }
}
