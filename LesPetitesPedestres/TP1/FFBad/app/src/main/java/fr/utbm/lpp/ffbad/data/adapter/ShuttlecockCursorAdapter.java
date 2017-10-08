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

        //Creation des alias des champs à remplir dans la vue
        TextView txtBrand = view.findViewById(R.id.brand);
        TextView txtReference = view.findViewById(R.id.reference);
        TextView txtStock = view.findViewById(R.id.stock);
        ImageView imgIcon = view.findViewById(R.id.icon);

        Shuttlecock shuttlecock = FFBadDbContract.Shuttlecock.getFromCursor(cursor);

        //contenus des textView
        txtBrand.setText(shuttlecock.getBrand());
        txtReference.setText(shuttlecock.getReference());
        txtStock.setText(String.valueOf(shuttlecock.getStock()));

        //recuperation de la concatenation de la marque et de la référence du volant
        String iconNameWithSpaces = shuttlecock.getIcon();

        //les noms contiennent peut-être des espaces
        Log.d("image",iconNameWithSpaces);

        String iconName = "";

        for(int i=0;i<iconNameWithSpaces.length();i++){
            if (iconNameWithSpaces.charAt(i) == ' ') {
                for (int j = i; j < iconNameWithSpaces.length() - 1; j++) {
                    iconName += iconNameWithSpaces.charAt(j + 1);
                }
                break;
            } else {
                iconName += iconNameWithSpaces.charAt(i);
            }
        }

        //les noms ne contiennent plus d'espaces
        Log.d("imageSUITE", iconName);

        //creation d'un ID de la ressource
        int resIm = context.getResources().getIdentifier(iconName, "mipmap", context.getPackageName());

        //assigner la ressource à l'image
        imgIcon.setImageResource(resIm);
    }
}
