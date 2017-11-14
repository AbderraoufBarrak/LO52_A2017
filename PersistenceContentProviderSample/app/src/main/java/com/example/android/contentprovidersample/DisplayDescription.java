package com.example.android.contentprovidersample;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.contentprovidersample.data.Converters;
import com.example.android.contentprovidersample.data.Historique;
import com.example.android.contentprovidersample.data.Volant;
import com.example.android.contentprovidersample.provider.SampleContentProvider;

import java.util.Date;

public class DisplayDescription extends AppCompatActivity {

    private static final int LOADER_VOLANTS = 1;
    private static final int LOADER_HISTORIQUE = 2;
    private Cursor mCursor;
    private int item_row;

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
                        case LOADER_HISTORIQUE:
                            return new CursorLoader(getApplicationContext(),
                                    SampleContentProvider.URI_HISTORIQUE,
                                    new String[]{Historique.COLUMN_ID},
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
                            TextView mText = findViewById(R.id.nomVolant);
                            mText.setText(mCursor.getString(mCursor.getColumnIndexOrThrow(Volant.COLUMN_NAME)));
                            TextView mPrix = findViewById(R.id.priceVolant);
                            mPrix.setText(String.format("%.2f", mCursor.getDouble(mCursor.getColumnIndexOrThrow(Volant.COLUMN_PRIX))) + " €");
                            break;
                        case LOADER_HISTORIQUE:
                            mCursor = data;
                            mCursor.move(item_row);
                            TextView mDerniereVente = findViewById(R.id.derniereVente);
                            Date mDate = Converters.fromTimestamp(mCursor.getLong(mCursor.getColumnIndexOrThrow(Historique.COLUMN_DATE)));
                            mDerniereVente.setText(mDate.toString());
                            break;
                    }
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    switch (loader.getId()) {
                        case LOADER_VOLANTS:
                            mCursor = null;
                            break;
                        case LOADER_HISTORIQUE:
                            mCursor = null;
                            break;
                    }
                }

            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_description);

        Intent intent = getIntent();
        item_row = intent.getIntExtra("ITEM_ROW", 0);

        getSupportLoaderManager().initLoader(LOADER_VOLANTS, null, mLoaderCallbacks);
        getSupportLoaderManager().initLoader(LOADER_HISTORIQUE, null, mLoaderCallbacks);
    }
}
