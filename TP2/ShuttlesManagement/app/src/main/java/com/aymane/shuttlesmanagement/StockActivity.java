package com.aymane.shuttlesmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class StockActivity extends AppCompatActivity {

    List<Integer> i = new ArrayList<Integer>();
    List<String> r = new ArrayList<String>();
    List<String> b = new ArrayList<String>();
    List<String> s = new ArrayList<String>();

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new DatabaseHelper(this);

        Cursor c = db.select_volants();
        if(c.getCount()==0) return;




       while(c.moveToNext()){
            b.add(c.getString(1));
            r.add(c.getString(2));
            i.add(parseInt(c.getString(5)));
            s.add(c.getString(4));
        }

        ListView listView = (ListView)findViewById(R.id.shuttlesList);
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_achats:
                startActivity(new Intent(StockActivity.this, AchatsActivity.class));
                return true;
            case R.id.item_stock:
                return true;
            case R.id.item_form:
                startActivity(new Intent(StockActivity.this, AcheterActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return r.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int j, View view, ViewGroup viewGroup) {
            view= getLayoutInflater().inflate(R.layout.stock_layout,null);

            ImageView imageView=(ImageView) view.findViewById(R.id.imageView);
            TextView textViewRef=(TextView) view.findViewById(R.id.refText);
            TextView textViewBrand=(TextView) view.findViewById(R.id.brandText);
            TextView textViewStock=(TextView) view.findViewById(R.id.stockText);

            imageView.setImageResource(i.get(j));
            textViewRef.setText("Réference: "+r.get(j));
            textViewBrand.setText("Marque: "+b.get(j));
            textViewStock.setText("Stock: "+s.get(j));
            return view;
        }
    }
}
