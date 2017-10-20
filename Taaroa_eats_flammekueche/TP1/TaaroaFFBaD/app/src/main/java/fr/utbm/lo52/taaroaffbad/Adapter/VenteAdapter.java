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

import fr.utbm.lo52.taaroaffbad.Beans.Acheteur;
import fr.utbm.lo52.taaroaffbad.Beans.Vente;
import fr.utbm.lo52.taaroaffbad.Database.AcheteurDAO;
import fr.utbm.lo52.taaroaffbad.R;

/**
 * Created by Moi on 27/09/2017.
 */

public class VenteAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    private List<Vente> venteList;
    private AcheteurDAO acheteurDAO;
    private Vente v;
    private Acheteur a;

    public VenteAdapter(Activity activity, List<Vente> venteList) {
        this.activity = activity;
        this.venteList = venteList;
        acheteurDAO = new AcheteurDAO(activity);
        acheteurDAO.open();
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

        // get volant
        v = venteList.get(position);

        // VenteID
        TextView idVente = (TextView) convertView.findViewById(R.id.tvVenteID);
        idVente.setText("[#"+String.valueOf(v.getVenteId())+"]");

        // Vente prix
        TextView prixVente = (TextView) convertView.findViewById(R.id.tvVentePrix);
        prixVente.setText(String.valueOf(v.getPrix()));
        Log.i("JOJO_v.getPRIX",v.getPrix()+"");

        // Vente quantité
        TextView qteVente = (TextView) convertView.findViewById(R.id.tvVenteQte);
        qteVente.setText(String.valueOf(v.getQuantite()));

        // Vente bool payé
        ImageView img = (ImageView) convertView.findViewById(R.id.ivPaye);
        if(v.getPaye()) img.setImageResource(R.drawable.yes);
        else            img.setImageResource(R.drawable.no);

        // get acheteur correspondant
        a = acheteurDAO.getAcheteur(v.getAcheteurId());

        // Acheteur nom
        TextView nom = (TextView) convertView.findViewById(R.id.tvVenteNom);
        nom.setText(a.getNom());

        // Acheteur prenom
        TextView prenom = (TextView) convertView.findViewById(R.id.tvVentePre);
        prenom.setText(a.getPrenom());

        // Acheteur adresse
        TextView adr = (TextView) convertView.findViewById(R.id.tvVenteAdr);
        adr.setText(a.getAdresse());

        // Acheteur tel
        TextView tel = (TextView) convertView.findViewById(R.id.tvVenteTel);
        tel.setText(a.getTel());

        // Acheteur type
        TextView type = (TextView) convertView.findViewById(R.id.tvVenteTypeAch);
        type.setText(a.getType());

        return convertView;
    }



}
