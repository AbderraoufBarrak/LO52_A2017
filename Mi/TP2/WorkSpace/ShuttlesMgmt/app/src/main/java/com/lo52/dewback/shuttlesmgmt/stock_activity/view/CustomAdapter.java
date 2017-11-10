package com.lo52.dewback.shuttlesmgmt.stock_activity.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lo52.dewback.shuttlesmgmt.R;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.StockDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 20/10/2017.
 */

public class CustomAdapter extends ArrayAdapter<StockDataBean> implements View.OnClickListener{

    private ArrayList<StockDataBean> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView brandTxtView;
        TextView nameTxtView;
        TextView priceTxtView;
        TextView stockTxtView;
        ImageView overviewImgView;
    }

    public CustomAdapter(List<StockDataBean> data, Context context) {
        super(context, R.layout.row_item, data);
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
        StockDataBean StockDataBean = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.brandTxtView = (TextView) convertView.findViewById(R.id.brand);
            viewHolder.nameTxtView = (TextView) convertView.findViewById(R.id.name);
            viewHolder.priceTxtView = (TextView) convertView.findViewById(R.id.price);
            viewHolder.stockTxtView = (TextView) convertView.findViewById(R.id.stock);
            viewHolder.overviewImgView = (ImageView) convertView.findViewById(R.id.overview);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.brandTxtView.setText(String.format("Marque : %s",StockDataBean.getBrand()));
        viewHolder.nameTxtView.setText(String.format("Nom : %s",StockDataBean.getName()));
        viewHolder.priceTxtView.setText(String.format("Prix : %s €",Double.toString(StockDataBean.getPrice())));
        viewHolder.stockTxtView.setText(String.format("Stock : %s",Integer.toString(StockDataBean.getStock())));
        viewHolder.overviewImgView.setImageResource(StockDataBean.getOverviewImgRes());
        viewHolder.overviewImgView.setAdjustViewBounds(true);

        //TODO ajouter un update de l'image dans l'ImageView overview

        // Return the completed view to render on screen
        return convertView;
    }
}