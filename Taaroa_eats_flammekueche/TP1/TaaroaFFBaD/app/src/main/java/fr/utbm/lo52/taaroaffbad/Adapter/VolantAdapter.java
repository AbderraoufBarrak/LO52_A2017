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

        Log.i("Yvan",v.getClassement()+"");

        // Marque
        TextView marque = (TextView) convertView.findViewById(R.id.tvVolantName);
        marque.setText(v.getMarque());

        // Rating
        RatingBar classement = (RatingBar) convertView.findViewById(R.id.ratingBarAdapter);
        classement.setRating(v.getClassement());


        return convertView;
    }
}
