package com.lo52.flo.lo52_tp5;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP;

/**
 * Activité principale du programme (elle gère l'affichage et les appels vers les fonctions c++)
 */
public class LO52MainActivity extends AppCompatActivity {

    // Attributs pour utiliser l'affichage et la saisie de texte facilement
    private TextView displayTextview;
    private EditText edittext;

    // Chargement de la librairie c++
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * Fonction appelée lors du lancement de l'activité
     * Elle lance l'affichage et initialise les attributs
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo52_main);

        // Règle l'affichage de la barre d'action
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(new GradientDrawable(BOTTOM_TOP, new int[]{0xFFE192C0, 0xFF991D65}));
        ab.setTitle("TP5");

        displayTextview = (TextView) findViewById(R.id.text_display);
        edittext = (EditText) findViewById(R.id.edittext);
    }

    /**
     * Fonction appelée lors du clique sur le bouton Read
     * Appelle la fonction native read
     * @param view
     */
    public void onRead(View view){
        displayTextview.setText(read(edittext.getText().toString()));
    }

    /**
     * Fonction appelée lors du clique sur le bouton Write
     * Appelle la fonction native write
     * @param view
     */
    public void onWrite(View view){
        displayTextview.setText(write(edittext.getText().toString()));
    }

    /**
     * Fonction appelée lors du clique sur le bouton Start
     * Appelle la fonction native start
     * @param view
     */
    public void onStart(View view){
        int randValue = (int)(11 + Math.random()*(16-11));
        displayTextview.setText(start(randValue));
    }

    /**
     * Fonction appelée lors du clique sur le bouton Stop
     * Appelle la fonction native stop
     * @param view
     */
    public void onStop(View view){
        int randValue = (int)(1 + Math.random()*(10-1));
        displayTextview.setText(stop(randValue));
    }

    /**
     * Fonction appelée lors du clique sur le bouton Reset
     * Appelle la fonction native reset
     * @param view
     */
    public void onReset(View view){
        edittext.setText("");
        displayTextview.setText(reset());
    }

    /**
     * Fonction onBackPressed
     * Permet de gérer la fermeture de l'application lors de l'appuie sur la touche retour
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(LO52MainActivity.this)
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

    /**
     * Déclaration des fonctions natives
     */
    public native String read(String value);

    public native String write(String value);

    public native String stop(int value);

    public native String start (int value);

    public native String reset();
}
