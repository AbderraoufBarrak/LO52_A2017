package com.example.android.contentprovidersample;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.contentprovidersample.data.Historique;
import com.example.android.contentprovidersample.data.SampleDatabase;


public class AllHistorique extends AppCompatActivity {
    private static final int LOADER_CHEESES = 1;
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_historique);

        final RecyclerView list_historic = findViewById(R.id.list_historic);
        list_historic.setLayoutManager(new LinearLayoutManager(list_historic.getContext()));
        mListAdapter = new ListAdapter();
        list_historic.setAdapter(mListAdapter);

        new HistoriqueCursorTask().execute();
    }

    private class HistoriqueCursorTask extends AsyncTask<Void, Void, Cursor> {
        protected Cursor doInBackground(Void... params) {
            return SampleDatabase.getInstance(getApplicationContext()).historique().selectAll();
        }

        protected void onPostExecute(Cursor allHistoric) {
            mListAdapter.setCheeses(allHistoric);
        }
    }

    private static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

        private Cursor mCursor;

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ListAdapter.ViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
            if (mCursor.moveToPosition(position)) {
                holder.item_title.setText(mCursor.getString(
                        mCursor.getColumnIndexOrThrow(Historique.COLUMN_ID)));
                holder.item_who.setText(mCursor.getString(
                        mCursor.getColumnIndexOrThrow(Historique.COLUMN_ID)));
                holder.item_quantity.setText(mCursor.getString(
                        mCursor.getColumnIndexOrThrow(Historique.COLUMN_ID)));
                holder.item_price.setText(mCursor.getString(
                        mCursor.getColumnIndexOrThrow(Historique.COLUMN_ID)));
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

            TextView item_title;
            TextView item_who;
            TextView item_quantity;
            TextView item_price;

            private final Context context;

            ViewHolder(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_historic, parent, false));
                item_title = itemView.findViewById(R.id.item_title);
                item_who = itemView.findViewById(R.id.item_who);
                item_quantity = itemView.findViewById(R.id.item_quantity);
                item_price = itemView.findViewById(R.id.item_price);
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

