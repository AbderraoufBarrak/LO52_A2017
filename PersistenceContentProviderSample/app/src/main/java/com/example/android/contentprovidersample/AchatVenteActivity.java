package com.example.android.contentprovidersample;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.contentprovidersample.data.Historique;
import com.example.android.contentprovidersample.data.StarLordDatabase;
import com.example.android.contentprovidersample.data.Volant;

import java.util.Calendar;

public class AchatVenteActivity extends AppCompatActivity {

    public static final int ACHETER = 1;
    public static final int VENDRE = 2;

    private int volant_id;
    private int action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat_vente);

        Intent intent = getIntent();
        volant_id = intent.getIntExtra("ITEM_ROW", 0);
        action = intent.getIntExtra("ACTION", 0);

        new VolantCursorTask().execute();

        Button mButton = findViewById(R.id.actionValiderMarche);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText mEdit   = findViewById(R.id.quantiteVolantsMarche);
                Historique historique = new Historique();

                historique.date = Calendar.getInstance().getTime();
                switch (action) {
                    case ACHETER:
                        historique.quantity = Integer.parseInt(mEdit.getText().toString());
                        break;
                    case VENDRE:
                        historique.quantity = -Integer.parseInt(mEdit.getText().toString());
                        break;
                }
                historique.volant_id = volant_id;

                new InsertTask().execute(historique);
            }
        });
    }

    private class VolantCursorTask extends AsyncTask<Void, Void, Cursor> {
        protected Cursor doInBackground(Void... params) {
            return StarLordDatabase.getInstance(getApplicationContext()).volant().selectAll();
        }

        protected void onPostExecute(Cursor allVolants) {
            allVolants.move(volant_id);
            TextView mText = findViewById(R.id.nomVolantMarche);
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

    private class InsertTask extends AsyncTask<Historique, Void, Void> {
        protected Void doInBackground(Historique... params) {
            StarLordDatabase mDatabase = StarLordDatabase.getInstance(getApplicationContext());

            mDatabase.beginTransaction();
            mDatabase.historique().insert(params[0]);
            if (mDatabase.volantHistorique().quantiteVolant(params[0].volant_id) > 0) {
                mDatabase.setTransactionSuccessful();

            }
            mDatabase.endTransaction();
            return null;
        }

        protected void onPostExecute(Void result) {
            finish();
        }

    }

}
