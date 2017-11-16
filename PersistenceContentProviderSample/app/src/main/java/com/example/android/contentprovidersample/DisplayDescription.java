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

    private static final int LOADER_VOLANTS = 1;
    private static final int LOADER_HISTORIQUE = 2;
    private int item_row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_description);

        Intent intent = getIntent();
        item_row = intent.getIntExtra("ITEM_ROW", 0);

        new DescriptionCursorTask().execute(LOADER_VOLANTS);
        new DescriptionCursorTask().execute(LOADER_HISTORIQUE);
    }

    private class DescriptionCursorTask extends AsyncTask<Integer, Void, Cursor> {
        private int loader;

        protected Cursor doInBackground(Integer... params) {
            this.loader = params[0];

            switch (loader) {
                case LOADER_VOLANTS:
                    return SampleDatabase.getInstance(getApplicationContext()).volant().selectAll();
                case LOADER_HISTORIQUE:
                    return SampleDatabase.getInstance(getApplicationContext()).volantHistorique().selectByVolantId(item_row);
                default:
                    return null;
            }
        }

        protected void onPostExecute(Cursor mCursor) {
            switch (loader) {
                case LOADER_VOLANTS:
                    mCursor.move(item_row);
                    TextView mText = findViewById(R.id.nomVolant);
                    mText.setText(mCursor.getString(mCursor.getColumnIndexOrThrow(Volant.COLUMN_NAME)));
                    TextView mPrix = findViewById(R.id.priceVolant);
                    mPrix.setText(String.format("%.2f", mCursor.getDouble(mCursor.getColumnIndexOrThrow(Volant.COLUMN_PRIX))) + " €");
                    break;
                case LOADER_HISTORIQUE:
                    mCursor.moveToFirst();
                    TextView mDerniereVente = findViewById(R.id.derniereVente);
                    Date mDate = Converters.fromTimestamp(mCursor.getLong(mCursor.getColumnIndexOrThrow(Historique.COLUMN_DATE)));
                    mDerniereVente.setText(mDate.toString());
                    break;
            }
        }

    }
}
