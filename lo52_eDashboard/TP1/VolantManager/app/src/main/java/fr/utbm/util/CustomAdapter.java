package fr.utbm.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.utbm.entity.Volant;
import fr.utbm.volantmanager.R;

/**
 * Created by Exige on 30/09/2017.
 */

public class CustomAdapter extends BaseAdapter {

    private Activity activity;
    private List<Volant> volants;
    private LayoutInflater inflater;

    public CustomAdapter(Activity activity) {
        this.activity = activity;
    }

    public CustomAdapter(Activity activity, List<Volant> volants) {
        this.activity = activity;
        this.volants = volants;

        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return volants.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if(view == null) {
            view = inflater.inflate(R.layout.volants_lv_item, viewGroup, false);

            holder = new ViewHolder();

            holder.volantIV = (ImageView)view.findViewById(R.id.volant_iv);
            holder.volantMarqueTV = (TextView)view.findViewById(R.id.volant_marque_tv);
            holder.volantRefTV = (TextView)view.findViewById(R.id.volant_ref_tv);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        Volant volant = volants.get(i);

        holder.volantMarqueTV.setText(this.activity.getString(R.string.marque_volants) + " " + volant.getMarque());
        holder.volantRefTV.setText(this.activity.getString(R.string.ref_volants) + " " + volant.getRef());

        if(volant.getRef().equals("B3ST V0LANT")) {
            holder.volantIV.setImageResource(R.mipmap.ic_bebelama);
        }


        return view;
    }

    public void updateRecords(List<Volant> volants) {
        this.volants = volants;

        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView volantMarqueTV;
        TextView volantRefTV;
        ImageView volantIV;
    }
}
