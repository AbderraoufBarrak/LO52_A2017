package fr.utbm.lo52.taaroaffbad.Activity;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


import java.util.Date;

import fr.utbm.lo52.taaroaffbad.Beans.Vente;
import fr.utbm.lo52.taaroaffbad.Database.VenteDAO;
import fr.utbm.lo52.taaroaffbad.R;

public class VolantPageActivity extends AppCompatActivity {

    public AlertDialog.Builder builder;
    public EditText cmdNom, cmdPrenom, cmdTelephone, cmdAdresse, cmdQuantite;
    public Switch checkPaye, checkStatut;
    public String validite1_volant,validite2_volant,marque_volant,reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volant_page);

        Intent intent = getIntent();
        validite1_volant = intent.getStringExtra("validite1_volant");
        validite2_volant = intent.getStringExtra("validite2_volant");
        marque_volant = intent.getStringExtra("marque_volant");
        reference = intent.getStringExtra("reference");
        int classement = intent.getIntExtra("classement",0);

        // marque & référence
        TextView tvVolantMarqueRef = (TextView) findViewById(R.id.tvVolantMarqueRef);
        tvVolantMarqueRef.setText(marque_volant+"\n["+reference+"]");

        // classement
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setNumStars(classement);
        Drawable drawable = ratingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#dfe61d"), PorterDuff.Mode.SRC_ATOP);

        // validite 1 & 2
        TextView tvValidite = (TextView) findViewById(R.id.tvValidite);
        tvValidite.setText(validite1_volant+" / "+validite2_volant);

        // bouton cmmander
        Button commander = (Button) findViewById(R.id.btnCommander);
        commander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(new ContextThemeWrapper(VolantPageActivity.this, R.style.AppTheme));
                LayoutInflater inflater = VolantPageActivity.this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.form, null));
                builder.setPositiveButton("Acheter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Dialog dialogObj = Dialog.class.cast(dialog);

                        cmdNom = (EditText) dialogObj.findViewById(R.id.CmdNom);
                        cmdPrenom = (EditText) dialogObj.findViewById(R.id.CmdPrenom);
                        cmdAdresse = (EditText) dialogObj.findViewById(R.id.CmdAdresse);
                        cmdTelephone = (EditText) dialogObj.findViewById(R.id.CmdTelephone);
                        cmdQuantite = (EditText) dialogObj.findViewById(R.id.CmdQuantite);
                        checkPaye = (Switch) dialogObj.findViewById(R.id.CheckPaye);
                        checkStatut = (Switch) dialogObj.findViewById(R.id.CheckStatut);

                        String refCommande = cmdPrenom.getText().toString().toLowerCase()+" "+
                                cmdNom.getText().toString().toUpperCase()+"\n"+
                                cmdTelephone.getText().toString()+"\n"+
                                cmdAdresse.getText().toString().toUpperCase()+"\n"+
                                cmdQuantite.getText().toString()+"\n"+
                                "payé:"+(checkPaye.isChecked()?"OUI":"NON")+"\n"+
                                "club:"+(checkStatut.isChecked()?"OUI":"NON")+"\n";
                        Log.i("JOJO-commande",refCommande);

                        Vente vente = new Vente(3,0,marque_volant,reference,0,15,checkPaye.isChecked(),Integer.parseInt(cmdQuantite.getText().toString()),new Date(), new Date());
                        VenteDAO venteDAO = new VenteDAO(VolantPageActivity.this);
                        venteDAO.open();
                        venteDAO.addVente(vente);

                        Log.i("JOJO-vente",vente.toString());

                        dialog.cancel();
                    }
                }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
        });


    }

}
