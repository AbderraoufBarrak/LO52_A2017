package com.example.android.contentprovidersample;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.contentprovidersample.data.Converters;
import com.example.android.contentprovidersample.data.Historique;
import com.example.android.contentprovidersample.data.Volant;
import com.example.android.contentprovidersample.provider.SampleContentProvider;

import java.util.Date;

public class AchatVenteActivity extends AppCompatActivity {

    public static final int ACHETER = 1;
    public static final int VENDRE = 2;
    private static final int LOADER_VOLANTS = 1;

    private int item_row;
    private int action;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat_vente);

        Intent intent = getIntent();
        item_row = intent.getIntExtra("ITEM_ROW", 0);
        action = intent.getIntExtra("ACTION", 0);

        getSupportLoaderManager().initLoader(LOADER_VOLANTS, null, mLoaderCallbacks);
    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {

                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    switch (id) {
                        case LOADER_VOLANTS:
                            return new CursorLoader(getApplicationContext(),
                                    SampleContentProvider.URI_VOLANT,
                                    new String[]{Volant.COLUMN_NAME},
                                    null, null, null);
                        default:
                            throw new IllegalArgumentException();
                    }
                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                    switch (loader.getId()) {
                        case LOADER_VOLANTS:
                            mCursor = data;
                            mCursor.move(item_row);
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
                            mText.setText(prefixe + mCursor.getString(mCursor.getColumnIndexOrThrow(Volant.COLUMN_NAME)));
                            TextView mPrix = findViewById(R.id.prixVolantMarche);
                            mPrix.setText(String.format("%.2f", mCursor.getDouble(mCursor.getColumnIndexOrThrow(Volant.COLUMN_PRIX))) + " €");
                            break;
                    }
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    switch (loader.getId()) {
                        case LOADER_VOLANTS:
                            mCursor = null;
                            break;
                    }
                }

            };
}
