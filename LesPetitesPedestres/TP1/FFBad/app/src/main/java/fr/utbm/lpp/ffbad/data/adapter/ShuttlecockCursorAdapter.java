package fr.utbm.lpp.ffbad.data.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import fr.utbm.lpp.ffbad.R;
import fr.utbm.lpp.ffbad.data.Shuttlecock;
import fr.utbm.lpp.ffbad.data.db.FFBadDbContract;

/**
 * Created by Zilliqman on 06/10/2017.
 */

public class ShuttlecockCursorAdapter extends CursorAdapter{
    public ShuttlecockCursorAdapter(Context context, Cursor cursor) { super(context, cursor, 0); }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_shuttlecock, viewGroup, false);
}

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtBrand = view.findViewById(R.id.brand);
        TextView txtReference = view.findViewById(R.id.reference);
        TextView txtStock = view.findViewById(R.id.stock);
        ImageView imgIcon = view.findViewById(R.id.icon);

        Shuttlecock shuttlecock = FFBadDbContract.Shuttlecock.getFromCursor(cursor);

        txtBrand.setText(shuttlecock.getBrand());
        txtReference.setText(shuttlecock.getReference());
        txtStock.setText(String.valueOf(shuttlecock.getStock()));

        String iconName = shuttlecock.getIcon() + ".jpg";

        /*iconName += ".jpg";*/

        //int image = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());

        //imgIcon.setImageResource(image);

        imgIcon.setImageResource(R.drawable.rsl_grade3);

        Log.d("LOL","YOUPI");

    }
}
