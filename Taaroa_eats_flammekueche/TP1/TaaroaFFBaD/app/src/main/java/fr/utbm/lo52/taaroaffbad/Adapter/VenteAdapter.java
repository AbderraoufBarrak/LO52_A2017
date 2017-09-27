package fr.utbm.lo52.taaroaffbad.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import fr.utbm.lo52.taaroaffbad.Beans.Vente;
import fr.utbm.lo52.taaroaffbad.R;

/**
 * Created by Moi on 27/09/2017.
 */

public class VenteAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    private List<Vente> venteList;

    public VenteAdapter(Activity activity, List<Vente> venteList) {
        this.activity = activity;
        this.venteList = venteList;
    }

    @Override
    public int getCount() {
        return venteList.size();
    }

    @Override
    public Object getItem(int position) {
        return venteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.adapter_vente, null);

        // getting volant data for the row
        Vente v = venteList.get(position);

        // VenteID
        TextView idVente = (TextView) convertView.findViewById(R.id.tvVenteID);
        idVente.setText("[#"+String.valueOf(v.getVenteId())+"]");

        // Vente prix
        TextView prixVente = (TextView) convertView.findViewById(R.id.tvVentePrix);
        prixVente.setText(String.valueOf(v.getPrix()));

        // Vente quantité
        TextView qteVente = (TextView) convertView.findViewById(R.id.tvVenteQte);
        qteVente.setText(String.valueOf(v.getQuantite()));

        // Vente bool payé
        ImageView img = (ImageView) convertView.findViewById(R.id.ivPaye);
        if(v.getPaye()) img.setImageResource(R.drawable.yes);
        else            img.setImageResource(R.drawable.no);

        return convertView;
    }



}
