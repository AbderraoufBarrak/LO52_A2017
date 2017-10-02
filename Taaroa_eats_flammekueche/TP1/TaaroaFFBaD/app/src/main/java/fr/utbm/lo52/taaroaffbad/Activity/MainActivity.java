package fr.utbm.lo52.taaroaffbad.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;

import fr.utbm.lo52.taaroaffbad.Beans.Vente;
import fr.utbm.lo52.taaroaffbad.Beans.Volant;
import fr.utbm.lo52.taaroaffbad.Database.Database;
import fr.utbm.lo52.taaroaffbad.Database.RemplissageBDD;
import fr.utbm.lo52.taaroaffbad.Database.VenteDAO;
import fr.utbm.lo52.taaroaffbad.Database.VolantDAO;
import fr.utbm.lo52.taaroaffbad.R;

public class MainActivity extends AppCompatActivity {

    public Button btnVolant, btnCommande;
    public ArrayList<Volant> test;
    public ArrayList<Vente> test2;
    public VenteDAO venteDAO;
    public VolantDAO volantsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volantsDAO = new VolantDAO(MainActivity.this);
        volantsDAO.open();

        venteDAO = new VenteDAO(MainActivity.this);
        venteDAO.open();

        // remplissage de la BDD (RAZ) -> paramètres : context, volant, fabricant, acheteur, vente
        RemplissageBDD rbdd = new RemplissageBDD(MainActivity.this, false, false, false, false);

        //test2 = venteDAO.getVente();
        test = volantsDAO.getVolant();

        Typeface pacifico = Typeface.createFromAsset(getAssets(),"font/Pacifico.ttf");
        btnVolant = (Button)findViewById(R.id.btnVolant);
        btnCommande = (Button)findViewById(R.id.btnCommande);
        btnVolant.setTypeface(pacifico);
        btnCommande.setTypeface(pacifico);

        btnCommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test2 = venteDAO.getVente();
                Intent intent = new Intent(MainActivity.this, VenteActivity.class);
                intent.putExtra("venteList", test2);
                MainActivity.this.startActivity(intent);
            }
        });

        btnVolant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test = volantsDAO.getVolant();
                Intent intent = new Intent(MainActivity.this, VolantActivity.class);
                intent.putExtra("volantList", test);
                MainActivity.this.startActivity(intent);
            }
        });

    }
}
