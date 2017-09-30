package fr.utbm.lpp.ffbad.data.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import fr.utbm.lpp.ffbad.R;

public class SaleCursorAdapter extends CursorAdapter {

    public SaleCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_sale, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int[] to = {
                R.id.price,
                R.id.quantity,
                R.id.brand,
                R.id.reference,
                R.id.name,
                R.id.is_paid,
        };

        TextView txtPrice = view.findViewById(R.id.price);
        TextView txtQuantity = view.findViewById(R.id.quantity);
        TextView txtShuttlecock = view.findViewById(R.id.shuttlecock);
        TextView txtCustomerName = view.findViewById(R.id.name);

        List<TextView> txtList = Arrays.asList(txtPrice, txtQuantity, txtShuttlecock, txtCustomerName);

        float price = cursor.getFloat(cursor.getColumnIndex("price"));
        int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
        boolean isPaid = cursor.getInt(cursor.getColumnIndex("is_paid")) == 1;
        String brand = cursor.getString(cursor.getColumnIndex("brand"));
        String ref = cursor.getString(cursor.getColumnIndex("reference"));
        String customerName = cursor.getString(cursor.getColumnIndex("name"));

        txtPrice.setText(String.valueOf(price));
        txtQuantity.setText(String.valueOf(quantity));
        txtShuttlecock.setText(brand + " - " + ref);
        txtCustomerName.setText(customerName);

        int txtColor = (isPaid ? Color.GREEN : Color.RED);
        for (TextView txt : txtList) {
            txt.setTextColor(txtColor);
        }
    }
}
