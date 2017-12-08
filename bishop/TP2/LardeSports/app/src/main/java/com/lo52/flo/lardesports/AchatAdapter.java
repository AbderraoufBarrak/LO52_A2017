package com.lo52.flo.lardesports;

/**
 * Created by Flo on 23.10.2017.
 */

import java.util.LinkedList;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AchatAdapter extends ArrayAdapter<Achat> {

    private final Context context;
    private LinkedList<Achat> achats;

    /**
     * Constructeur
     * @param context
     * @param resource
     * @param achats
     */
    public AchatAdapter(Context context, int resource, LinkedList<Achat> achats) {
        super(context, resource, achats);
        this.context = context;
        this.achats = achats;
    }

    /**
     * Fonction surchargé permettant l'affichage des achats grâce à un ArrayAdapter
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.achat_layout, parent, false);
        } else {
            convertView = (LinearLayout) convertView;
        }

        TextView viewAcheteur = (TextView) convertView.findViewById(R.id.acheteur_achat);
        viewAcheteur.setText(achats.get(position).getAcheteur());
        viewAcheteur.setTag(achats.get(position).getAcheteur());

        TextView viewQte = (TextView) convertView.findViewById(R.id.quantite_achat);
        viewQte.setText(String.valueOf(achats.get(position).getQuantite()));
        viewQte.setTag(String.valueOf(achats.get(position).getQuantite()));

        String ref = MainActivity.bdd.getArticleBDDById(achats.get(position).getVolantId()).getRef();

        TextView viewRef = (TextView) convertView.findViewById(R.id.ref_achat);
        viewRef.setText(String.valueOf(ref));
        viewRef.setTag(String.valueOf(ref));

        LinearLayout achatLayout = (LinearLayout) convertView.findViewById(R.id.achat_layout);
        if(achats.get(position).getPaye()){
            achatLayout.setBackgroundColor(Color.parseColor("#38FF36"));
        }
        else{
            achatLayout.setBackgroundColor(Color.parseColor("#FF0808"));
        }

        return convertView;
    }
}
