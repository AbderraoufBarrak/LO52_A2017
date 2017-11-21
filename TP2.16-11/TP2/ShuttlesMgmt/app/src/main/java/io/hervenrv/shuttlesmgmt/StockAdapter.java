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

import io.hervenrv.shuttlesmgmt.BDD.Produit.ProduitDAO;
import io.hervenrv.shuttlesmgmt.BDD.Stock.Stock;

/**
 * Created by basile on 21/10/17.
 *
 */

public class StockAdapter extends ArrayAdapter {

    private ProduitDAO produitDAO;

    public StockAdapter(Context context, List<Stock> entries, ProduitDAO produitDAO) {
        super(context, 0, entries);
        this.produitDAO = produitDAO;
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
            viewHolder.nom = (TextView) convertView.findViewById(R.id.nom);
            viewHolder.stock = (TextView) convertView.findViewById(R.id.stock);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Stock elem = (Stock) getItem(position);
        viewHolder.marque.setText(produitDAO.selectionner(elem.getRef()).getMarque());
        viewHolder.nom.setText(produitDAO.selectionner(elem.getRef()).getNom());
        viewHolder.stock.setText(""+elem.getQuantite());
        viewHolder.image.setImageResource(produitDAO.selectionner(elem.getRef()).getImageID());


        return convertView;
    }

    private class StockViewHolder{
        public TextView nom;
        public TextView marque;
        public TextView stock;
        public ImageView image;
    }
}
