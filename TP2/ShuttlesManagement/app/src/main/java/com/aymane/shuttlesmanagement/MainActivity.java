package com.aymane.shuttlesmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] ACHETEURS = {"Antoine Marc", "ANNA D'AMICIS", "RAQUEL GAMBA", "DAHOUNE ZAKARIA"};
    String[] REFERENCES = {"AS30" , "Grade 3", "Grade A9", "Grade A1"};
    String[] QUANTITES = {"10", "100", "54", "95"};
    String[] PRIX = {"270 euro", "1670 euro", "739.8 euro", "1995"};
    String[] COULEURS = {"#00ff00", "#ff0000", "#ff0000", "#00ff00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.commandsList);

        MainActivity.CustomAdapter customAdapter=new MainActivity.CustomAdapter();
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
                return true;
            case R.id.item_stock:
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                return true;
            case R.id.item_form:
                startActivity(new Intent(MainActivity.this, Main3Activity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    class CustomAdapter extends BaseAdapter {

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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view= getLayoutInflater().inflate(R.layout.achats_layout,null);



            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.elmtAchat);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), Main3Activity.class);
                    intent.putExtra("acheteur",ACHETEURS[i]);
                    intent.putExtra("couleur",COULEURS[i]);
                    intent.putExtra("ref",REFERENCES[i] );
                    intent.putExtra("qte",QUANTITES[i]);
                    startActivityForResult(intent,0);
                }
            });

            TextView textViewAch=(TextView) view.findViewById(R.id.textAcheteur);
            TextView textViewRef=(TextView) view.findViewById(R.id.textReference);
            TextView textViewQte=(TextView) view.findViewById(R.id.textQte);
            TextView textViewPrix=(TextView) view.findViewById(R.id.textPrix);

            textViewAch.setText(ACHETEURS[i]);
            textViewAch.setBackgroundColor(Color.parseColor(COULEURS[i]));
            textViewRef.setText(REFERENCES[i]);
            textViewRef.setBackgroundColor(Color.parseColor(COULEURS[i]));
            textViewQte.setText(QUANTITES[i]);
            textViewQte.setBackgroundColor(Color.parseColor(COULEURS[i]));
            textViewPrix.setText(PRIX[i]);
            textViewPrix.setBackgroundColor(Color.parseColor(COULEURS[i]));
            return view;
        }
    }
}


