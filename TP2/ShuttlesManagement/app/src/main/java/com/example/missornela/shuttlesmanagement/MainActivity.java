package com.example.missornela.shuttlesmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.missornela.shuttlesmanagement.Controller.MySQLiteHelper;
import com.example.missornela.shuttlesmanagement.Controller.ShuttlesRegActivity;
import com.example.missornela.shuttlesmanagement.Controller.StockActivity;
import com.example.missornela.shuttlesmanagement.Controller.PurchaseActivity;
import com.example.missornela.shuttlesmanagement.Model.Client;
import com.example.missornela.shuttlesmanagement.Model.Facture;
import com.example.missornela.shuttlesmanagement.Model.Tube_Volant;


public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private MySQLiteHelper db;
    final int val = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new MySQLiteHelper(this);
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        if (intent != null && intent.getFlags()==41) {
        }
        else {

            fillDatabase();
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }




    public void fillDatabase(){
        Client d = new Client("chris","BROWN","ZZZ","3 RUE  MARCEL PAUL ",000000);
        db.insertClientData(d);
        db.insertClientData(new Client("Miss","DJANWA","DDD","RUE DES VINS",00112));

        db.insertFactureData(new Facture(1,12,true,2));
        db.insertFactureData(new Facture(2,26,false,1));
        db.insertFactureData(new Facture(1,8,true,4));
        db.insertTube_VolantData(new Tube_Volant("AS30",27,"automne 2015","Standard",2,500,"Yonex"));
        db.insertTube_VolantData(new Tube_Volant("Grade 3",16.70,"Printemps 2016","Elite",1,5000,"RSL"));
        db.insertTube_VolantData(new Tube_Volant("Grade A9",13.70,"automne 2016","Elite",1,10000,"RSL"));
        db.insertTube_VolantData(new Tube_Volant("Grade A1",21,"Printemps 2017 2016","Standard",1,6000,"RSL"));

    }

    //clic sur un item
    public boolean onOptionsItemSelected(MenuItem item) {

        //choix d'un item
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

