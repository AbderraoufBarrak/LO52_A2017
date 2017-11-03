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
import java.util.StringTokenizer;

import fr.utbm.lpp.ffbad.R;
import fr.utbm.lpp.ffbad.data.Sale;
import fr.utbm.lpp.ffbad.data.db.FFBadDbContract;

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
        TextView txtPrice = view.findViewById(R.id.price);
        TextView txtQuantity = view.findViewById(R.id.quantity);
        TextView txtShuttlecock = view.findViewById(R.id.shuttlecock);
        TextView txtCustomerName = view.findViewById(R.id.customer);

        List<TextView> txtList = Arrays.asList(txtPrice, txtQuantity, txtShuttlecock, txtCustomerName);

        Sale sale = FFBadDbContract.Sale.getFromCursor(cursor);

        float unit_price = Float.parseFloat(String.valueOf(sale.getPrice()));
        int quantity = Integer.parseInt(String.valueOf(sale.getQuantity()));
        float price = quantity * unit_price;



        txtPrice.setText(String.format("%.2f", price));
        txtQuantity.setText(String.valueOf(sale.getQuantity()));
        txtShuttlecock.setText(sale.getShuttlecock().getBrand() + " - " + sale.getShuttlecock().getReference());
        txtCustomerName.setText(sale.getCustomer().getName());

        int txtColor = (sale.isPaid() ? Color.GREEN : Color.RED);
        for (TextView txt : txtList) {
            txt.setTextColor(txtColor);
        }
    }
}
