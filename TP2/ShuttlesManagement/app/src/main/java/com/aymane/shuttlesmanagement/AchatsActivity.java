package com.aymane.shuttlesmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AchatsActivity extends AppCompatActivity {

    List<String> a = new ArrayList<String>();
    List<String> r = new ArrayList<String>();
    List<String> q = new ArrayList<String>();
    List<String> p = new ArrayList<String>();
    List<String> cl = new ArrayList<String>();

    /*String[] ACHETEURS = {"Antoine Marc", "ANNA D'AMICIS", "RAQUEL GAMBA", "DAHOUNE ZAKARIA"};
    String[] REFERENCES = {"AS30" , "Grade 3", "Grade A9", "Grade A1"};
    String[] QUANTITES = {"10", "100", "54", "95"};
    String[] PRIX = {"270 euro", "1670 euro", "739.8 euro", "1995"};
    String[] COULEURS = {"#00ff00", "#ff0000", "#ff0000", "#00ff00"};*/

    DatabaseHelper dba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.commandsList);

        AchatsActivity.CustomAdapter customAdapter=new AchatsActivity.CustomAdapter();
        listView.setAdapter(customAdapter);

        dba = new DatabaseHelper(this);

        if(dba.count_shuttles() == 0)
        {
            dba.insertData("Yonex","AS30",500,10000,R.drawable.b);
            dba.insertData("RSL","Grade 3",99,5000,R.drawable.a);
            dba.insertData("RSL","Grade A9",500,500,R.drawable.c);
            dba.insertData("RSL","Grade A1",400,6000,R.drawable.d);
        }

        Cursor c=dba.select_achats();
         while(c.moveToNext())
         {
             a.add(c.getString(1));
             r.add(c.getString(3));
             q.add(c.getInt(4)+"");
             p.add(c.getInt(5)+"");
             if(c.getInt(2) == 0)
                    cl.add("#ff0000");
             else
                 cl.add("#00ff00");
         }

        //Toast.makeText(AchatsActivity.this,dba.count()+"" , Toast.LENGTH_SHORT).show();
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
                return true;
            case R.id.item_stock:
                startActivity(new Intent(AchatsActivity.this, StockActivity.class));
                return true;
            case R.id.item_form:
                startActivity(new Intent(AchatsActivity.this, AcheterActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return a.size();
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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view= getLayoutInflater().inflate(R.layout.achats_layout,null);



            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.elmtAchat);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), AcheterActivity.class);
                    intent.putExtra("acheteur",a.get(i));
                    intent.putExtra("couleur",cl.get(i));
                    intent.putExtra("ref",r.get(i) );
                    intent.putExtra("qte",q.get(i));
                    startActivityForResult(intent,0);
                }
            });

            TextView textViewAch=(TextView) view.findViewById(R.id.textAcheteur);
            TextView textViewRef=(TextView) view.findViewById(R.id.textReference);
            TextView textViewQte=(TextView) view.findViewById(R.id.textQte);
            TextView textViewPrix=(TextView) view.findViewById(R.id.textPrix);

            textViewAch.setText(a.get(i));
            textViewAch.setBackgroundColor(Color.parseColor(cl.get(i)));
            textViewRef.setText(r.get(i));
            textViewRef.setBackgroundColor(Color.parseColor(cl.get(i)));
            textViewQte.setText(q.get(i));
            textViewQte.setBackgroundColor(Color.parseColor(cl.get(i)));
            textViewPrix.setText(p.get(i));
            textViewPrix.setBackgroundColor(Color.parseColor(cl.get(i)));
            return view;
        }
    }
}


