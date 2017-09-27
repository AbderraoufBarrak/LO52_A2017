package fr.utbm.lo52.taaroaffbad.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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


        // Acheteur
        TextView idAcheteur = (TextView) convertView.findViewById(R.id.tvIdAcheteur);
        idAcheteur.setText(String.valueOf(v.getAcheteurId()));

        // Vendeur
        TextView idVendeur = (TextView) convertView.findViewById(R.id.tvIdVendeur);
        idVendeur.setText(String.valueOf(v.getFabricantId()));

        // Qte
        TextView qte = (TextView) convertView.findViewById(R.id.tvQte);
        qte.setText(String.valueOf(v.getQuantite()));


        return convertView;
    }



}
