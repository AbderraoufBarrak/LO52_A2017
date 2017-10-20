package fr.utbm.util;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.utbm.beans.LotInfo;
import fr.utbm.volantmanager.R;
import fr.utbm.volantmanager.VolantsDisplay;

public class CustomAdapter extends BaseAdapter {

    private Activity activity;
    private List<LotInfo> lots;
    private LayoutInflater inflater;

    public CustomAdapter(Activity activity) {
        this.activity = activity;
    }

    public CustomAdapter(Activity activity, List<LotInfo> lots) {
        this.activity = activity;
        this.lots = lots;

        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return lots.size();
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
            view = inflater.inflate(R.layout.lots_lv_item, viewGroup, false);

            holder = new ViewHolder();

            holder.lotIV = (ImageView)view.findViewById(R.id.lot_iv);
            holder.lotMarqueTV = (TextView)view.findViewById(R.id.lot_marque_tv);
            holder.lotRefTV = (TextView)view.findViewById(R.id.lot_ref_tv);
            holder.lotTailleTV = (TextView)view.findViewById(R.id.lot_taille_tv);
            holder.lotPrixTV = (TextView)view.findViewById(R.id.lot_prix_tv);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        LotInfo lotInfo = lots.get(i);

        holder.lotMarqueTV.setText(lotInfo.getMarque());
        holder.lotRefTV.setText(lotInfo.getRef());
        holder.lotTailleTV.setText("" + lotInfo.getTaille());
        holder.lotTailleTV.setTextColor(ContextCompat.getColor(activity, ColorQuantityMatcher.colorAdapter(lotInfo.getTaille())));
        holder.lotPrixTV.setText("" + String.format("%.2f", lotInfo.getPrix()) + "€");
        holder.lotIV.setImageResource(imagePicker(lotInfo));


        return view;
    }

    public void updateRecords(List<LotInfo> lots) {
        this.lots = lots;

        notifyDataSetChanged();
    }

    public List<LotInfo> sortList(List<LotInfo> lots, int mode) {
        Log.d("eDBTEAM/CustomAdapter", "sortList, mode " + mode);
        switch(mode) {
            case 1 :
                Collections.sort(lots, new Comparator<LotInfo>() {
                    @Override
                    public int compare(LotInfo lot1, LotInfo lot2) {
                        if(lot1.getTaille() == lot2.getTaille()) {
                            return 0;
                        }
                        return lot1.getTaille() < lot2.getTaille() ? -1 : 1;
                    }
                });
                break;
            case 2 :
                Collections.sort(lots, new Comparator<LotInfo>() {
                    @Override
                    public int compare(LotInfo lot1, LotInfo lot2) {
                        if(lot1.getPrix() == lot2.getPrix()) {
                            return 0;
                        }
                        return lot1.getPrix() < lot2.getPrix() ? -1 : 1;
                    }
                });
                break;
            case 3 :
                Collections.sort(lots,
                        new Comparator<LotInfo>()
                        {
                            public int compare(LotInfo lot1, LotInfo lot2)
                            {
                                return lot1.getMarque().compareToIgnoreCase(lot2.getMarque());
                            }
                        });
                break;
            case 4:
                Collections.sort(lots,
                        new Comparator<LotInfo>()
                        {
                            public int compare(LotInfo lot1, LotInfo lot2)
                            {
                                return lot1.getRef().compareToIgnoreCase(lot2.getRef());
                            }
                        });
                break;
        }
        return lots;
    }

    public int imagePicker(LotInfo lot) {
        if(lot.getRef().equals("B3ST V0LANT")) {
            return R.mipmap.ic_volants_manager_round;
        } else if (lot.getRef().equals("AS30")) {
            return R.mipmap.ic_lot_1;
        } else if(lot.getRef().equals("Grade 3")) {
            return R.mipmap.ic_lot_2;
        } else if(lot.getRef().equals("Grade A9")) {
            return R.mipmap.ic_lot_3;
        } else if(lot.getRef().equals("Grade A1")) {
            return R.mipmap.ic_lot_4;
        } else {
            return R.mipmap.ic_bebelama;
        }
    }

    class ViewHolder {
        TextView lotMarqueTV;
        TextView lotRefTV;
        TextView lotTailleTV;
        TextView lotPrixTV;
        ImageView lotIV;
    }
}
