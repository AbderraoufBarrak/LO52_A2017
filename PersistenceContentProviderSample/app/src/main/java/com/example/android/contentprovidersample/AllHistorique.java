package com.example.android.contentprovidersample;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.contentprovidersample.data.Volant;
import com.example.android.contentprovidersample.provider.SampleContentProvider;

public class AllHistorique extends AppCompatActivity {
    private static final int LOADER_CHEESES = 1;
    private AllHistorique.CheeseAdapter mCheeseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_historique);

        final RecyclerView list_historic = findViewById(R.id.list_historic);
        list_historic.setLayoutManager(new LinearLayoutManager(list_historic.getContext()));
        mCheeseAdapter = new AllHistorique.CheeseAdapter();
        list_historic.setAdapter(mCheeseAdapter);

        getSupportLoaderManager().initLoader(LOADER_CHEESES, null, mLoaderCallbacks);
    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {

                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    switch (id) {
                        case LOADER_CHEESES:
                            return new CursorLoader(getApplicationContext(),
                                    SampleContentProvider.URI_CHEESE,
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
                            mCheeseAdapter.setCheeses(data);
                            break;
                    }
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    switch (loader.getId()) {
                        case LOADER_CHEESES:
                            mCheeseAdapter.setCheeses(null);
                            break;
                    }
                }

            };


    private static class CheeseAdapter extends RecyclerView.Adapter<CheeseAdapter.ViewHolder> {

        private Cursor mCursor;

        @Override
        public CheeseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CheeseAdapter.ViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(CheeseAdapter.ViewHolder holder, int position) {
            if (mCursor.moveToPosition(position)) {
                holder.mText.setText(mCursor.getString(
                        mCursor.getColumnIndexOrThrow(Volant.COLUMN_NAME)));
                holder.mImage.setImageResource(mCursor.getInt(
                        mCursor.getColumnIndexOrThrow(Volant.COLUMN_IMAGE)));
            }
        }

        @Override
        public int getItemCount() {
            return mCursor == null ? 0 : mCursor.getCount();
        }

        void setCheeses(Cursor cursor) {
            mCursor = cursor;
            notifyDataSetChanged();
        }

        static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            TextView mText;
            ImageView mImage;
            private final Context context;

            ViewHolder(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_card, parent, false));
                mText = itemView.findViewById(R.id.card_title);
                mImage = itemView.findViewById(R.id.card_image);
                itemView.setClickable(true);
                itemView.setOnClickListener(this);

                context = itemView.getContext();
            }

            @Override
            public void onClick(View v) {
                int item_row = getAdapterPosition() + 1;
                Toast.makeText(context,"You pressed " + item_row + "case",Toast.LENGTH_SHORT).show();
            }
        }
    }
}

