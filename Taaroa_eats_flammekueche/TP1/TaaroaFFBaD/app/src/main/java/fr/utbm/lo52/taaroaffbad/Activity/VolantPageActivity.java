package fr.utbm.lo52.taaroaffbad.Activity;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import fr.utbm.lo52.taaroaffbad.Beans.Acheteur;
import fr.utbm.lo52.taaroaffbad.Beans.Vente;
import fr.utbm.lo52.taaroaffbad.Beans.Volant;
import fr.utbm.lo52.taaroaffbad.Database.AcheteurDAO;
import fr.utbm.lo52.taaroaffbad.Database.VenteDAO;
import fr.utbm.lo52.taaroaffbad.Database.VolantDAO;
import fr.utbm.lo52.taaroaffbad.R;

public class VolantPageActivity extends AppCompatActivity {

    public AlertDialog.Builder builder;
    public EditText cmdNom, cmdPrenom, cmdTelephone, cmdAdresse, cmdQuantite;
    public TextView textPrix;
    public Switch checkPaye, checkStatut;
    public Volant volant;
    public int quantite=0;
    public double nouveauPrix=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volant_page);

        Intent intent = getIntent();
        volant = (Volant) intent.getSerializableExtra("Volant");

        // marque & référence
        TextView tvVolantMarqueRef = (TextView) findViewById(R.id.tvVolantMarqueRef);
        tvVolantMarqueRef.setText(volant.getMarque()+"\n["+volant.getReference()+"]");

        // classement
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setNumStars(volant.getClassement());
        Drawable drawable = ratingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#dfe61d"), PorterDuff.Mode.SRC_ATOP);

        // validite 1 & 2 + stock
        TextView tvValidite = (TextView) findViewById(R.id.tvValidite);
        tvValidite.setText(volant.getValidite_1()+" / "+volant.getValidite_2()+
                            "\nStock disponible : "+volant.getStock());

        // image
        ImageView img = (ImageView) findViewById(R.id.ivVolantIconGd);
        if(volant.getMarque().equals("RSL")) {
            if(volant.getReference().equals("RSL GRADE 1"))       img.setImageResource(R.drawable.tube_1013_1_1_gd);
            else if(volant.getReference().equals("RSL GRADE 3"))  img.setImageResource(R.drawable.tube_1015_blanc_1_2_gd);
            else if(volant.getReference().equals("RSL GRADE 9"))  img.setImageResource(R.drawable.tube_rsla9_1_1_gd);
        }
        else img.setImageResource(R.drawable.tube_as30_1_1_gd); // Marque & Réf défaut : Yonex [AS30]

        // bouton cmmander
        Button commander = (Button) findViewById(R.id.btnCommander);
        commander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(new ContextThemeWrapper(VolantPageActivity.this, R.style.AppTheme));
                LayoutInflater inflater = VolantPageActivity.this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.form, null));

                /*AlertDialog dialogObj = builder.show();
                cmdNom = (EditText) dialogObj.findViewById(R.id.CmdNom);
                cmdPrenom = (EditText) dialogObj.findViewById(R.id.CmdPrenom);
                cmdAdresse = (EditText) dialogObj.findViewById(R.id.CmdAdresse);
                cmdTelephone = (EditText) dialogObj.findViewById(R.id.CmdTelephone);
                cmdQuantite = (EditText) dialogObj.findViewById(R.id.CmdQuantite);
                checkPaye = (Switch) dialogObj.findViewById(R.id.CheckPaye);
                checkStatut = (Switch) dialogObj.findViewById(R.id.CheckStatut);

                // MAJ de l'affichage du prix
                textPrix = (TextView) dialogObj.findViewById(R.id.tvVentePrix);

                // si club ou non
                checkStatut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Log.e("JOJO-SWITCH CLUB", "CLUB !");
                        majPrix();
                    }
                });

                // selon quantité
                cmdQuantite.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                        Log.e("JOJO-SWITCH CLUB", "11 !");
                        quantite = Integer.parseInt(cmdQuantite.toString());
                        majPrix();
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
                        Log.e("JOJO-SWITCH CLUB", "22 !");}

                    @Override
                    public void afterTextChanged(Editable arg0) {
                        Log.e("JOJO-SWITCH CLUB", "33 !");
                        //new SearchTask().execute(seq.toString().trim());
                    }

                });*/

                builder.setPositiveButton("Acheter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Dialog dialogObj = Dialog.class.cast(dialog);

                        try {

                            String sNom = cmdNom.getText().toString().toUpperCase();
                            String sPrenom = cmdPrenom.getText().toString().toLowerCase();
                            String sTel = cmdTelephone.getText().toString();
                            String sAdr = cmdAdresse.getText().toString().toUpperCase();
                            final String qteTmp = cmdQuantite.getText().toString();

                            if(sNom.isEmpty() || sTel.isEmpty() || sAdr.isEmpty() || qteTmp.isEmpty())
                                throw new Exception("Champ(s) vide(s) !");

                            if(volant.getStock() < Integer.parseInt(cmdQuantite.getText().toString()))
                                throw new Exception("Pas assez de stock !");

                            // Ajoute l'acheteur en BDD
                            Acheteur acheteur = new Acheteur(0,         // acheteurID : incrémental en BDD
                                    sNom,
                                    sPrenom,
                                    sAdr,
                                    sTel,
                                    checkStatut.isChecked()?"club":"particulier"
                            );

                            AcheteurDAO acheteurDAO = new AcheteurDAO(VolantPageActivity.this);
                            acheteurDAO.open();
                            long idNewAcheteur = acheteurDAO.addAcheteur(acheteur);

                            Vente vente = new Vente(0,          // venteID : incrémental en BDD
                                    volant.getMarque(),         // marque
                                    volant.getReference(),      // référence
                                    0,                          // fabID
                                    idNewAcheteur,                          // achID
                                    nouveauPrix,  // prix
                                    checkPaye.isChecked(),                        // boolPaye
                                    Integer.parseInt(qteTmp),                     // qte
                                    new Date(),                                   // dateAchat
                                    (checkPaye.isChecked() ? new Date() : null)); // datePaye

                            // Ajoute la vente en BDD
                            VenteDAO venteDAO = new VenteDAO(VolantPageActivity.this);
                            venteDAO.open();
                            venteDAO.addVente(vente);

                            // Puis décrémente le stock du volant
                            VolantDAO volantDAO = new VolantDAO(VolantPageActivity.this);
                            volantDAO.open();
                            volantDAO.decrementStock(volant,vente.getQuantite());

                            // Ferme la pop-up
                            dialog.cancel();

                            // message de réussite
                            Toast.makeText(VolantPageActivity.this, "Commande enregistrée", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(VolantPageActivity.this,MainActivity.class));

                        }catch (Exception e){
                            Log.e("VolantPageActivity", e+"");
                            Toast.makeText(VolantPageActivity.this, "Erreur : "+e.getMessage(), Toast.LENGTH_LONG).show();
                        }



                    }
                }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                //builder.show();

                AlertDialog dialogObj = builder.show();
                cmdNom = (EditText) dialogObj.findViewById(R.id.CmdNom);
                cmdPrenom = (EditText) dialogObj.findViewById(R.id.CmdPrenom);
                cmdAdresse = (EditText) dialogObj.findViewById(R.id.CmdAdresse);
                cmdTelephone = (EditText) dialogObj.findViewById(R.id.CmdTelephone);
                cmdQuantite = (EditText) dialogObj.findViewById(R.id.CmdQuantite);
                checkPaye = (Switch) dialogObj.findViewById(R.id.CheckPaye);
                checkStatut = (Switch) dialogObj.findViewById(R.id.CheckStatut);

                // MAJ de l'affichage du prix
                textPrix = (TextView) dialogObj.findViewById(R.id.tvVentePrix);

                // si club ou non
                checkStatut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Log.e("JOJO-SWITCH CLUB", "CLUB !");
                        majPrix();
                    }
                });

                // selon quantité
                cmdQuantite.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                        if(cmdQuantite.getText().toString().isEmpty())
                            quantite = 0;
                        else
                            quantite = Integer.parseInt(cmdQuantite.getText().toString());

                        majPrix();
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {}

                    @Override
                    public void afterTextChanged(Editable arg0) {}

                });

            }
        });


    }

    public void majPrix(){
        try {
            boolean isClub = checkStatut.isChecked();
            Log.e("JOJO-majPrix()", "boolean isClub =" + isClub);

            DecimalFormat df = new DecimalFormat("#.##");
            nouveauPrix = Double.valueOf(df.format(volant.getPrix() * quantite * (isClub ? 0.9 : 1.0)));

            textPrix.setText("" + nouveauPrix+" €");
            Log.e("JOJO-majPrix()", "MAJ PRIX =" + nouveauPrix);
        }catch (Exception e){
            Log.e("JOJO-majPrix()", "ERREUR ="+e.getMessage());
        }
    }

    // HOME Button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_home) {
            startActivity(new Intent(this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
