package com.aymane.shuttlesmanagement;

import android.content.Intent;
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

public class Main2Activity extends AppCompatActivity {

    int[] IMAGES={R.drawable.b, R.drawable.c, R.drawable.a, R.drawable.d};
    String[] REFERENCES={ "AS30", "Grade 3" , "Grade A9" , "Grade A1"};
    String[] BRANDS={"Yonex" ,"RSL" ,"RSL", "RSL" };
    String[] STOCKS={"500", "5000", "10000", "6000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
                return true;
            case R.id.item_stock:
                return true;
            case R.id.item_form:
                startActivity(new Intent(Main2Activity.this, Main3Activity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 4;
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            view= getLayoutInflater().inflate(R.layout.stock_layout,null);

            ImageView imageView=(ImageView) view.findViewById(R.id.imageView);
            TextView textViewRef=(TextView) view.findViewById(R.id.refText);
            TextView textViewBrand=(TextView) view.findViewById(R.id.brandText);
            TextView textViewStock=(TextView) view.findViewById(R.id.stockText);

            imageView.setImageResource(IMAGES[i]);
            textViewRef.setText("Réference: "+REFERENCES[i]);
            textViewBrand.setText("Marque: "+BRANDS[i]);
            textViewStock.setText("Stock: "+STOCKS[i]);
            return view;
        }
    }
}
