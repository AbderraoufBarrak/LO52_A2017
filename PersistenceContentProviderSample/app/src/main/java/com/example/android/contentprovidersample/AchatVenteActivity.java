package com.example.android.contentprovidersample;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.contentprovidersample.data.SampleDatabase;
import com.example.android.contentprovidersample.data.Volant;

public class AchatVenteActivity extends AppCompatActivity {

    public static final int ACHETER = 1;
    public static final int VENDRE = 2;

    private int item_row;
    private int action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat_vente);

        Intent intent = getIntent();
        item_row = intent.getIntExtra("ITEM_ROW", 0);
        action = intent.getIntExtra("ACTION", 0);

        new VolantCursorTask().execute();

    }

    private class VolantCursorTask extends AsyncTask<Void, Void, Cursor> {
        protected Cursor doInBackground(Void... params) {
            return SampleDatabase.getInstance(getApplicationContext()).volant().selectAll();
        }

        protected void onPostExecute(Cursor allVolants) {
            allVolants.move(item_row);
            TextView mText =   findViewById(R.id.nomVolantMarche);
            String prefixe = "";
            switch (action){
                case ACHETER:
                    prefixe = "Achat ";
                    break;
                case VENDRE:
                    prefixe = "Vente ";
                    break;
            }
            mText.setText(prefixe + allVolants.getString(allVolants.getColumnIndexOrThrow(Volant.COLUMN_NAME)));
            TextView mPrix = findViewById(R.id.prixVolantMarche);
            mPrix.setText(String.format("%.2f", allVolants.getDouble(allVolants.getColumnIndexOrThrow(Volant.COLUMN_PRIX))) + " €");
        }

    }

}
