package fr.utbm.lo52.taaroaffbad.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import fr.utbm.lo52.taaroaffbad.Beans.Volant;
import fr.utbm.lo52.taaroaffbad.R;

/**
 * Created by Moi on 22/09/2017.
 */

public class VolantAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Volant> volantList;

    public VolantAdapter(Activity activity, List<Volant> volantList) {
        this.activity = activity;
        this.volantList = volantList;
    }

    @Override
    public int getCount() {
        return volantList.size();
    }

    @Override
    public Object getItem(int position) {
        return volantList.get(position);
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
            convertView = inflater.inflate(R.layout.adapter_volant, null);

        // getting volant data for the row
        Volant v = volantList.get(position);

        Log.i("JOJO_volantAdapter=",v.toString());

        // Marque
        TextView marque = (TextView) convertView.findViewById(R.id.tvVolantMarque);
        marque.setText(v.getMarque());

        // Référence
        TextView ref = (TextView) convertView.findViewById(R.id.tvVolantRef);
        ref.setText("["+v.getReference()+"]");

        // Stock
        TextView stock = (TextView) convertView.findViewById(R.id.tvVolantStock);
        stock.setText(v.getStock()+"x");

        // Image
        ImageView img = (ImageView) convertView.findViewById(R.id.ivVolantIcon);
        if(v.getMarque().equals("RSL")) {
            if(v.getReference().equals("RSL GRADE 1"))       img.setImageResource(R.drawable.tube_1013_1_1);
            else if(v.getReference().equals("RSL GRADE 3"))  img.setImageResource(R.drawable.tube_1015_blanc_1_2);
            else if(v.getReference().equals("RSL GRADE 9"))  img.setImageResource(R.drawable.tube_rsla9_1_1);
        }
        else img.setImageResource(R.drawable.tube_as30_1_1); // Marque & Réf défaut : Yonex [AS30]

        return convertView;
    }
}
