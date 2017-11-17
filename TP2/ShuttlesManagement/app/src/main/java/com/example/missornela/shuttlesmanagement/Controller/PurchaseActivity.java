package com.example.missornela.shuttlesmanagement.Controller;

/**
 * Created by Miss ornela on 08/11/2017.
 */


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.EditText;


import com.example.missornela.shuttlesmanagement.MainActivity;
import com.example.missornela.shuttlesmanagement.Model.Client;
import com.example.missornela.shuttlesmanagement.Model.Facture;
import com.example.missornela.shuttlesmanagement.Model.Tube_Volant;
import com.example.missornela.shuttlesmanagement.Model.Distributeur;
import com.example.missornela.shuttlesmanagement.R;


public class PurchaseActivity extends AppCompatActivity {

    private MySQLiteHelper db;

    ListView list ;
    Context context;

    EditText PrenomInput,RefrenceInput,quantiteInput,prixInput;
    final String EXTRA_NOM_CLIENT = "Nom";
    final String EXTRA_PRENOM_CLIENT = "Prenom";
    final String EXTRA_REFERENCE_TUBE = "Reference";
    final String EXTRA_QUANTITE_FACTURE = "Quantite";
    final String EXTRA_PAYE_FACTURE = "paye";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        context = this;
        db = new MySQLiteHelper(this);

        list = (ListView)findViewById(R.id.tab_purchase_listview);
        //fillDatabase();
        viewAll();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                Cursor cursor= (Cursor)parent.getItemAtPosition(position);
                Intent intent = new Intent(PurchaseActivity.this, ShuttlesRegActivity.class);
                intent.addFlags(40);
                intent.putExtra(EXTRA_NOM_CLIENT, cursor.getString(2));
                intent.putExtra(EXTRA_PRENOM_CLIENT, cursor.getString(1));
                intent.putExtra(EXTRA_REFERENCE_TUBE, cursor.getString(4));
                intent.putExtra(EXTRA_QUANTITE_FACTURE, cursor.getString(3));
                intent.putExtra(EXTRA_PAYE_FACTURE, cursor.getString(6));
                startActivity(intent);
            }

        });

    }

    /*public void fillDatabase(){
        Client d = new Client("chris","BROWN","ZZZ","3 RUE  MARCEL PAUL ",000000);
        db.insertClientData(d);
        db.insertClientData(new Client("Miss","DJANWA","DDD","RUE DES VINS",00112));

        db.insertFactureData(new Facture(1,12,true,2));
        db.insertFactureData(new Facture(2,26,false,1));
        db.insertFactureData(new Facture(1,8,true,4));

    }*/


    public void onImageHomeClicked(View view) {
        Intent intent = new Intent(PurchaseActivity.this, MainActivity.class);
        intent.addFlags(41);
        startActivity(intent);
    }




    private void viewAll()
    {
        Cursor data = db.getAllPurchase();

        if(data.getCount()==0)
        {
            showMessage("Error","No purchase available");
            return;

        }

        ListView lv = (ListView) findViewById(R.id.tab_purchase_listview);
        PurchaseCursorAdapter adapter = new PurchaseCursorAdapter(this,data);
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
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
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
