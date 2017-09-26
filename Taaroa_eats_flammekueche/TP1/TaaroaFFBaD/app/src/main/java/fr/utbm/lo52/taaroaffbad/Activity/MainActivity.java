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
import fr.utbm.lo52.taaroaffbad.Database.VenteDAO;
import fr.utbm.lo52.taaroaffbad.Database.VolantDAO;
import fr.utbm.lo52.taaroaffbad.R;

public class MainActivity extends AppCompatActivity {

    public Button btnVolant, btnCommande;
    public ArrayList<Volant> test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VolantDAO volantsDAO = new VolantDAO(MainActivity.this);
        volantsDAO.open();

        // TODO : Mettre dans un fichier xml
        //Volant volantToAdd = new Volant("2017-2018","2018-2019", "ARTENGO", "BSC 950", 3);
        //Volant volantToAdd_2 = new Volant("2017-2017","2018-2018", "aaa", "Blbala0", 1);
        //volantsDAO.addVolant(volantToAdd);
        //volantsDAO.addVolant(volantToAdd_2);

        Vente vente = new Vente(1,0,"AA","BB",0,15,true,10,new Date(), new Date());
        VenteDAO aaaa = new VenteDAO(MainActivity.this);
        aaaa.open();
        aaaa.addVente(vente);


        test = volantsDAO.getVolant();

        Typeface pacifico = Typeface.createFromAsset(getAssets(),"font/Pacifico.ttf");
        btnVolant = (Button)findViewById(R.id.btnVolant);
        btnCommande = (Button)findViewById(R.id.btnCommande);
        btnVolant.setTypeface(pacifico);
        btnCommande.setTypeface(pacifico);

        btnVolant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VolantActivity.class);

                intent.putExtra("volantList", test);
                MainActivity.this.startActivity(intent);
            }
        });

    }
}
