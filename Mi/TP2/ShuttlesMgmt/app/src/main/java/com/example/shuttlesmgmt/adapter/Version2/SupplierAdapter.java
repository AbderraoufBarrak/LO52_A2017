package com.example.shuttlesmgmt.adapter.Version2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.entity.Version2.Supplier;

import java.util.List;

/**
 * Created by Michel on 2017/11/2.
 */

public class SupplierAdapter extends ArrayAdapter<Supplier> {
    public SupplierAdapter(Context c, List<Supplier> obj){ super(c, 0, obj);}

    @Override
    public int getCount(){
        return super.getCount();
    }

    @Override
    public View getView(int i, View v, ViewGroup group){
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_view_customer, group, false);
        }
        SupplierViewHolder vh = (SupplierViewHolder) v.getTag();
        if(vh == null){
            vh = new SupplierViewHolder();
            vh.name = (TextView) v.findViewById(R.id.id_name);
            vh.add = (TextView) v.findViewById(R.id.id_add);
            vh.phone = (TextView) v.findViewById(R.id.id_phone);
            v.setTag(vh);
        }


        Supplier pos = getItem(i);

        vh.name.setText(pos.getName());
        vh.add.setText(pos.getAdd());
        vh.phone.setText(pos.getPhone());

        return v;
    }

    class SupplierViewHolder{
        public TextView name, add, phone;
    }

}
