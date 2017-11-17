package com.example.missornela.shuttlesmanagement.Controller;

/**
 * Created by Miss ornela on 12/11/2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.missornela.shuttlesmanagement.R;


public class PurchaseCursorAdapter extends CursorAdapter{

    private Context con;
    private Cursor curs;
    int paye ;
    public PurchaseCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.con = context;
        this.curs = cursor;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_purchase_item, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView  PrenomInput = (TextView) view.findViewById(R.id.acheteurCol_textview);
        TextView RefrenceInput = (TextView) view.findViewById(R.id.referenceCol_textview);
        TextView quantiteInput = (TextView) view.findViewById(R.id.quantiteCol_textview);
        TextView prixInput = (TextView) view.findViewById(R.id.prixCol_textview);
        String acheteur =cursor.getString(cursor.getColumnIndexOrThrow("Nom")) +"  "+ cursor.getString(cursor.getColumnIndexOrThrow("Prenom")) ;
        String reference = cursor.getString(cursor.getColumnIndexOrThrow("Reference"));
        int quantite = cursor.getInt(cursor.getColumnIndexOrThrow("Quantite"));
        long prix = quantite * cursor.getLong(cursor.getColumnIndexOrThrow("Prix"));
        paye = cursor.getInt(cursor.getColumnIndexOrThrow("Paye"));
        PrenomInput.setText(acheteur);
        RefrenceInput.setText(reference);
        quantiteInput.setText(String.valueOf(quantite));
        prixInput.setText(String.valueOf(prix));

        if (paye == 0)
        {
            PrenomInput.setBackgroundColor(Color.RED);
            RefrenceInput.setBackgroundColor(Color.RED);
            quantiteInput.setBackgroundColor(Color.RED);
            prixInput.setBackgroundColor(Color.RED);
        }
        else
        {
            PrenomInput.setBackgroundColor(Color.GREEN);
            RefrenceInput.setBackgroundColor(Color.GREEN);
            quantiteInput.setBackgroundColor(Color.GREEN);
            prixInput.setBackgroundColor(Color.GREEN);
        }

    }


}
