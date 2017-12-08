package com.lo52.flo.lardesports;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP;

/**
 * Activité MainActivité
 * Activité principale lancée au démarrage de l'application
 */
public class MainActivity extends AppCompatActivity {

    public static BDD bdd;

    /**
     * FonctiononCreate
     * Appelée lors de la création de l'application
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(new GradientDrawable(BOTTOM_TOP, new int[]{0xFFA796FF, 0xFF3529FF}));
        ab.setTitle("LardeSport");

        bdd = new BDD(this);
        //bdd.initBDD();
    }

    /**
     * Fonction stockButton
     * Appelée lors du clique sur le bouton Stock
     * Elle lance l'activité StockActivity
     * @param view
     */
    public void stockButton(View view){
        Intent intent = new Intent(this, StockActivity.class);
        startActivity(intent);
    }

    /**
     * Fonction achatButton
     * Appelée lors du clique sur le bouton Achat
     * Elle lance l'activité AchatActivity
     * @param view
     */
    public void achatButton(View view){
        Intent intent = new Intent(this, AchatActivity.class);
        startActivity(intent);
    }

    public void gestionButton(View view){
        Intent intent = new Intent(this, VolantsGestionActivity.class);
        intent.putExtra("LECTURE_SEULE", false);
        startActivity(intent);
    }

    /**
     * Fonction onBackPressed
     * Permet de gérer la fermeture de l'application lors de l'appuie sur la touche retour
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Fermer l'application")
                .setMessage("Etes-vous sur de vouloir fermer cette application ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent();
                        setResult(2,intent);
                        finish();
                    }
                })
                .setNegativeButton("Non", null)
                .create()
                .show();
    }
}
