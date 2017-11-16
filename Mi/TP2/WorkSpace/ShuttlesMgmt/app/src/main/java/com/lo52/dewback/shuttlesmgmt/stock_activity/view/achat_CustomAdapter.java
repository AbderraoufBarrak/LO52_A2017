package com.lo52.dewback.shuttlesmgmt.stock_activity.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lo52.dewback.shuttlesmgmt.R;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.AchatDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by promet on 10/11/17.
 */

public class achat_CustomAdapter extends ArrayAdapter<AchatDataBean> implements View.OnClickListener{
    private ArrayList<AchatDataBean> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView buyerTxtView;
        TextView referenceTxtView;
        TextView priceTxtView;
        TextView quantityTxtView;
        ImageView overviewImgView;
    }

    public achat_CustomAdapter(List<AchatDataBean> data, Context context) {
        super(context, R.layout.achat_row_item, data);
        this.dataSet = new ArrayList<>(data);
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {

        //TODO faire listener click

		/*
         * int position=(Integer) v.getTag();
         * Object object= getItem(position);
         * StockDataBean StockDataBean=(StockDataBean)object;
		 */

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AchatDataBean StockDataBean = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        achat_CustomAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new achat_CustomAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.stock_row_item, parent, false);
            viewHolder.buyerTxtView = (TextView) convertView.findViewById(R.id.brand);
            viewHolder.referenceTxtView = (TextView) convertView.findViewById(R.id.name);
            viewHolder.priceTxtView = (TextView) convertView.findViewById(R.id.price);
            viewHolder.quantityTxtView = (TextView) convertView.findViewById(R.id.stock);
            viewHolder.overviewImgView = (ImageView) convertView.findViewById(R.id.overview);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (achat_CustomAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.buyerTxtView.setText(String.format("Acheteur : %s",StockDataBean.getBuyer()));
        viewHolder.referenceTxtView.setText(String.format("Référence : %s",StockDataBean.getReference()));
        viewHolder.priceTxtView.setText(String.format("Prix : %s €",Double.toString(StockDataBean.getPrice())));
        viewHolder.quantityTxtView.setText(String.format("Quantité : %s",Integer.toString(StockDataBean.getQuantity())));
        viewHolder.overviewImgView.setImageResource(StockDataBean.getOverviewImgRes());
        viewHolder.overviewImgView.setAdjustViewBounds(true);

        //TODO ajouter un update de l'image dans l'ImageView overview

        // Return the completed view to render on screen
        return convertView;
    }
}
