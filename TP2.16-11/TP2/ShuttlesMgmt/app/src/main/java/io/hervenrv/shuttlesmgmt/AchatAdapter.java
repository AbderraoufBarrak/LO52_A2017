package io.hervenrv.shuttlesmgmt;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import io.hervenrv.shuttlesmgmt.BDD.Achat.Achat;
import io.hervenrv.shuttlesmgmt.BDD.Produit.ProduitDAO;
import io.hervenrv.shuttlesmgmt.BDD.Produit.ProduitDbHandler;

/**
 * Created by basile on 21/10/17.
 *
 */

public class AchatAdapter extends ArrayAdapter {

    private  ProduitDAO produit_dao;

    public AchatAdapter(Context context, List<Achat> entries, ProduitDAO produit_dao) {
        super(context, 0, entries);
        this.produit_dao = produit_dao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.achat_list_entry,parent, false);
        }

        AchatViewHolder viewHolder = (AchatViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new AchatViewHolder();
            viewHolder.acheteur = convertView.findViewById(R.id.acheteur);
            viewHolder.ref = convertView.findViewById(R.id.ref);
            viewHolder.quantite = convertView.findViewById(R.id.quantite);
            viewHolder.prix = convertView.findViewById(R.id.prix);
            viewHolder.fond = convertView.findViewById(R.id.main);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Achat elem = (Achat) getItem(position);
        viewHolder.acheteur.setText(elem.getAcheteur());
        viewHolder.ref.setText(elem.getRef());
        viewHolder.quantite.setText("" + elem.getQuantite());
        if(elem.getPaye()){
            viewHolder.fond.setBackgroundColor(Color.GREEN);
        }else{
            viewHolder.fond.setBackgroundColor(Color.RED);
        }
        viewHolder.prix.setText( produit_dao.selectionner(elem.getRef()).getPrix()*elem.getQuantite() + " €");



        return convertView;
    }

    private class AchatViewHolder{
        public RelativeLayout fond;
        public TextView acheteur;
        public TextView ref;
        public TextView quantite;
        public TextView prix;
    }
}
