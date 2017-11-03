package fr.utbm.lpp.ffbad.data.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import fr.utbm.lpp.ffbad.R;
import fr.utbm.lpp.ffbad.data.Customer;
import fr.utbm.lpp.ffbad.data.db.FFBadDbContract;

public class CustomerCursorAdapter extends CursorAdapter {

    public CustomerCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_customer, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textName = view.findViewById(R.id.txt_name);

        Customer customer = FFBadDbContract.Customer.getFromCursor(cursor);

        textName.setText(customer.getName());
    }
}
