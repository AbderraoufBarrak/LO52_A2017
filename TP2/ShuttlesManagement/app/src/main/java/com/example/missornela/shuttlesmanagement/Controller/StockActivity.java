package com.example.missornela.shuttlesmanagement.Controller;

/**
 * Created by Miss ornela on 08/11/2017.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.missornela.shuttlesmanagement.MainActivity;
import com.example.missornela.shuttlesmanagement.Model.Tube_Volant;

import com.example.missornela.shuttlesmanagement.R;

import java.util.ArrayList;
import java.util.HashMap;


public class StockActivity extends AppCompatActivity{

    private MySQLiteHelper db;


    ListView list ;
    Context context;
    ArrayList<HashMap<String, String>> aList;

    int[] imgid=new int[]{
            R.drawable.a1013_1_1,
            R.drawable.as30_1_1,
            R.drawable.as30_1_1,
            R.drawable.a1013_1_1,

    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        context = this;
        db = new MySQLiteHelper(this);
        list = (ListView)findViewById(R.id.tab_data_listview);

      // fillDatabase();
        viewAll();


    }

    //permet de remeplir la base de données au demarrage de l'appli
     /* public void fillDatabase(){
        db.insertTube_VolantData(new Tube_Volant("AS30",27,"automne 2015","Standard",2,500,"Yonex"));
        db.insertTube_VolantData(new Tube_Volant("Grade 3",16.70,"Printemps 2016","Elite",1,5000,"RSL"));
        db.insertTube_VolantData(new Tube_Volant("Grade A9",13.70,"automne 2016","Elite",1,10000,"RSL"));
        db.insertTube_VolantData(new Tube_Volant("Grade A1",21,"Printemps 2017 2016","Standard",1,6000,"RSL"));
    }*/

    public void onImageHomeClicked(View view) {
        Intent intent = new Intent(StockActivity.this, MainActivity.class);
        intent.addFlags(41);
        startActivity(intent);
    }



    private void viewAll()
    {

        Cursor data = db.getAllStock();

            if(data.getCount()==0)
        {        showMessage("Error","NO shuttle Available2" + String.valueOf(data.getCount()));

            return;
        }

        ListView lv = (ListView) findViewById (R.id.tab_data_listview);
        String[] from = new String[] {"Marque","Reference","Stock"};
        int[] to = new int[] {R.id.marqueCol_textview, R.id.referenceCol_textview,R.id.stockCol_textview};
        SimpleCursorAdapter adapter =new SimpleCursorAdapter(context,
                R.layout.activity_stock_item, data, from, to, 0);

        lv.setAdapter(adapter);
    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //Méthode qui se déclenchera au clic sur un item
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_purchase:

                Intent myIntent = new Intent(this, PurchaseActivity.class);
                startActivityForResult(myIntent,0);
                return true;

            case R.id.menu_stocks:

                Intent myIntent1 = new Intent(this, StockActivity.class);
                startActivityForResult(myIntent1,0);
                return true;

            case R.id.menu_shuttlesreg:

                Intent myIntent2 = new Intent(this, ShuttlesRegActivity.class);
                startActivityForResult(myIntent2,0);
                return true;

        }
        return false;
    }



}

