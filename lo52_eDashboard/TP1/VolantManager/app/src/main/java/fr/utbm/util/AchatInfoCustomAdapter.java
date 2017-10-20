package fr.utbm.util;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.utbm.beans.AchatInfo;
import fr.utbm.beans.LotInfo;
import fr.utbm.volantmanager.R;

public class AchatInfoCustomAdapter extends BaseAdapter {

    private Activity activity;
    private List<AchatInfo> achats;
    private LayoutInflater inflater;

    public AchatInfoCustomAdapter(Activity activity) {
        this.activity = activity;
    }

    public AchatInfoCustomAdapter(Activity activity, List<AchatInfo> achats) {
        this.activity = activity;
        this.achats = achats;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return achats.size();
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

        AchatInfoCustomAdapter.ViewHolder holder = null;

        if(view == null) {
            view = inflater.inflate(R.layout.achat_lv_item, viewGroup, false);

            holder = new AchatInfoCustomAdapter.ViewHolder();

            holder.achatNameTV = (TextView)view.findViewById(R.id.achat_lv_name_tv);
            holder.achatFamilyTV = (TextView)view.findViewById(R.id.achat_lv_family_tv);
            holder.achatSocietyTV = (TextView)view.findViewById(R.id.achat_lv_society_tv);
            holder.achatPriceTV = (TextView)view.findViewById(R.id.achat_lv_price_tv);
            holder.achatQuantityTV = (TextView)view.findViewById(R.id.achat_lv_quantite_tv);
            holder.achatRefMarqueTV = (TextView)view.findViewById(R.id.achat_lv_refmarque_tv);
            holder.achatIdTV = (TextView)view.findViewById(R.id.achat_lv_id_tv);
            holder.achatDateTV = (TextView)view.findViewById(R.id.achat_lv_date_tv);
            holder.achatTailleTV = (TextView)view.findViewById(R.id.achat_lv_taille_tv);

            view.setTag(holder);

        } else {
            holder = (AchatInfoCustomAdapter.ViewHolder) view.getTag();
        }

        AchatInfo achatInfo = achats.get(i);

        holder.achatNameTV.setText("Nom: " + achatInfo.getAcheteurNom());
        holder.achatFamilyTV.setText("Prénom: " + achatInfo.getAcheteurPrénom());
        holder.achatSocietyTV.setText("Société: " + achatInfo.getAcheteurSociété());
        holder.achatPriceTV.setText("Prix: " + String.format("%.2f", achatInfo.getLotPrix()*achatInfo.getQuantité()) + "€");
        holder.achatQuantityTV.setText("" + achatInfo.getQuantité());
        holder.achatRefMarqueTV.setText(achatInfo.getMarque() + " / " + achatInfo.getRéférence());
        if(achatInfo.isPayed()) {
            holder.achatIdTV.setText("#" + achatInfo.getId() + " (Payé)");
            holder.achatIdTV.setTextColor(ContextCompat.getColor(activity, R.color.payed));
        } else {
            holder.achatIdTV.setText("#" + achatInfo.getId() + " (Non payé)");
            holder.achatIdTV.setTextColor(ContextCompat.getColor(activity, R.color.unpayed));
        }
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.achatDateTV.setText("" + simpleFormat.format(achatInfo.getDateDate()));
        holder.achatTailleTV.setText("x" + achatInfo.getLotTaille());
        holder.achatTailleTV.setTextColor(ContextCompat.getColor(activity, ColorQuantityMatcher.colorAdapter(achatInfo.getLotTaille())));

        return view;
    }

    public void updateRecords(List<AchatInfo> achatInfos) {
        this.achats = achatInfos;

        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView achatNameTV;
        TextView achatFamilyTV;
        TextView achatSocietyTV;
        TextView achatPriceTV;
        TextView achatQuantityTV;
        TextView achatRefMarqueTV;
        TextView achatIdTV;
        TextView achatDateTV;
        TextView achatTailleTV;
    }
}
