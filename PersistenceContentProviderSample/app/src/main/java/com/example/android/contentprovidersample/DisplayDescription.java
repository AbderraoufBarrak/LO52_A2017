package com.example.android.contentprovidersample;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.contentprovidersample.data.Volant;
import com.example.android.contentprovidersample.provider.SampleContentProvider;

public class DisplayDescription extends AppCompatActivity {

    private static final int LOADER_CHEESES = 1;
    private Cursor mCursor;
    private int item_row;

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {

                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    switch (id) {
                        case LOADER_CHEESES:
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
                        case LOADER_CHEESES:
                            mCursor = data;
                            mCursor.move(item_row);
                            TextView mText = findViewById(R.id.nomVolant);
                            mText.setText(mCursor.getString(mCursor.getColumnIndexOrThrow(Volant.COLUMN_NAME)));
                            break;
                    }
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    switch (loader.getId()) {
                        case LOADER_CHEESES:
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

        getSupportLoaderManager().initLoader(LOADER_CHEESES, null, mLoaderCallbacks);
    }
}
