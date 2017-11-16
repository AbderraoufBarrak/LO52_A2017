/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.contentprovidersample;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.contentprovidersample.data.StarLordDatabase;
import com.example.android.contentprovidersample.data.Volant;

public class MainActivity extends AppCompatActivity {

    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(list.getContext()));
        mListAdapter = new ListAdapter();
        list.setAdapter(mListAdapter);

        new VolantCursorTask().execute();
    }

    private class VolantCursorTask extends AsyncTask<Void, Void, Cursor> {
        protected Cursor doInBackground(Void... params) {
            return StarLordDatabase.getInstance(getApplicationContext()).volant().selectAll();
        }

        protected void onPostExecute(Cursor allVolants) {
            mListAdapter.setCheeses(allVolants);
        }
    }


    private static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

        private Cursor mCursor;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
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

                itemView.findViewById(R.id.action_acheter).setClickable(true);
                itemView.findViewById(R.id.action_acheter).setOnClickListener(this);
                itemView.findViewById(R.id.action_vendre).setClickable(true);
                itemView.findViewById(R.id.action_vendre).setOnClickListener(this);
                context = itemView.getContext();
            }

            @Override
            public void onClick(View v) {
                int item_row = getAdapterPosition() + 1;
                if (v.getId() == R.id.action_acheter){
                    Toast.makeText(context,"You pressed 'achat' button",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, AchatVenteActivity.class);
                    intent.putExtra("ITEM_ROW", item_row);
                    intent.putExtra("ACTION", AchatVenteActivity.ACHETER);
                    context.startActivity(intent);
                }else if(v.getId() == R.id.action_vendre){
                    Toast.makeText(context,"You pressed 'vendre' button",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, AchatVenteActivity.class);
                    intent.putExtra("ITEM_ROW", item_row);
                    intent.putExtra("ACTION", AchatVenteActivity.VENDRE);
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context,"The Item Clicked is: "+item_row,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, DisplayDescription.class);
                    intent.putExtra("ITEM_ROW", item_row);
                    context.startActivity(intent);
                }
            }
        }
    }

    public void view_all_history(View view){
        Toast.makeText(this,"You pressed 'history' button",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AllHistorique.class);
        this.startActivity(intent);
    }
}
