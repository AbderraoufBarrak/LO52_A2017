package com.example.shuttlesmgmt.adapter.Version2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.entity.Product;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Michel on 2017/11/2.
 */

public class ProductAdapter extends ArrayAdapter<Product> {
    String id;
    int idDrawable;
    public ProductAdapter(Context context, List<Product> objects) {
        super(context,0, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int i, View v, ViewGroup group){
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_view_product, group, false);
        }
        ProductViewHolder vh = (ProductViewHolder) v.getTag();
        if(vh == null){
            vh = new ProductViewHolder();
            vh.name = (TextView) v.findViewById(R.id.id_nom);
            vh.ref = (TextView) v.findViewById(R.id.id_reference);
            vh.price = (TextView) v.findViewById(R.id.id_price);
            vh.quantity = (TextView) v.findViewById(R.id.id_quantite);
            vh.image = (ImageView) v.findViewById(R.id.image);
            v.setTag(vh);
        }

        Product pos = getItem(i);

        vh.name.setText(pos.getName());
        vh.ref.setText(pos.getRef());
        vh.price.setText(Double.toString(pos.getPrice()) + " € ");
        vh.quantity.setText(Integer.toString(pos.getQuantity()));
        id = pos.getImage();
        idDrawable = getContext().getResources().getIdentifier(id,"drawable", getContext().getPackageName());
        vh.image.setImageResource(idDrawable);

        return v;
    }

    class ProductViewHolder{
        public ImageView image;
        public TextView name, ref, price, quantity;
    }

    public List<Product> sortList(List<Product> listProduct, int i){
        switch (i){
            case 0:
                //tri par quantite
                Collections.sort(listProduct, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getQuantity() - o2.getQuantity();
                    }
                });
                break;
            case 1:
                //tri par prix
                Collections.sort(listProduct, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return Double.compare(o1.getPrice(), o2.getPrice());
                    }
                });
                break;
            case 2:
                //tri par reference
                Collections.sort(listProduct, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getRef().compareToIgnoreCase(o2.getRef());
                    }
                });
                break;
            case 3:
                //Par défault tri par nom du produit
                Collections.sort(listProduct, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    }
                });
                break;
        }
        return listProduct;
    }
}
