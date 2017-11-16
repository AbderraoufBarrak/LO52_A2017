package com.example.android.contentprovidersample;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.contentprovidersample.data.Converters;
import com.example.android.contentprovidersample.data.Historique;
import com.example.android.contentprovidersample.data.SampleDatabase;
import com.example.android.contentprovidersample.data.Volant;

import java.util.Date;

public class DisplayDescription extends AppCompatActivity {

    private int volant_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_description);

        Intent intent = getIntent();
        volant_id = intent.getIntExtra("ITEM_ROW", 0);

        new DescriptionTask().execute();
    }

    private class DescriptionTask extends AsyncTask<Void, Void, Void> {
        private Cursor mVolants;
        private int totalVolants;

        protected Void doInBackground(Void... params) {
            mVolants =  SampleDatabase.getInstance(getApplicationContext()).volant().selectAll();
            totalVolants = SampleDatabase.getInstance(getApplicationContext()).volantHistorique().quantiteVolant(volant_id);
            return null;
        }

        protected void onPostExecute(Void result) {
            mVolants.move(volant_id);
            TextView mText = findViewById(R.id.nomVolant);
            mText.setText(mVolants.getString(mVolants.getColumnIndexOrThrow(Volant.COLUMN_NAME)));
            TextView mPrix = findViewById(R.id.priceVolant);
            mPrix.setText(String.format("%.2f", mVolants.getDouble(mVolants.getColumnIndexOrThrow(Volant.COLUMN_PRIX))) + " €");

            TextView mQuantiteVolants = findViewById(R.id.quantiteVolants);
            mQuantiteVolants.setText("Stock : " + totalVolants);
        }

    }
}
