package fr.utbm.lo52.taaroaffbad.Activity;

import android.content.Intent;
import java.text.DateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import fr.utbm.lo52.taaroaffbad.Beans.Acheteur;
import fr.utbm.lo52.taaroaffbad.Beans.Vente;
import fr.utbm.lo52.taaroaffbad.Beans.Volant;
import fr.utbm.lo52.taaroaffbad.Database.AcheteurDAO;
import fr.utbm.lo52.taaroaffbad.Database.VenteDAO;
import fr.utbm.lo52.taaroaffbad.R;

public class VentePageActivity extends AppCompatActivity {

    Vente vente;
    TextView titre, sousTitre, desc;
    Boolean flagVeutPayer, flagPaye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vente_page);

        Intent intent = getIntent();
        vente = (Vente) intent.getSerializableExtra("Vente");

        // affichage test
        TextView titre = (TextView) findViewById(R.id.tvVenteTITRE);
        TextView sousTitre = (TextView) findViewById(R.id.tvVenteSousTITRE);
        TextView desc = (TextView) findViewById(R.id.tvVenteDESC);

        // params : (YEAR-1900, MONTH-1, DAY, HOUR, MINUTE)
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT);

        String sTitre = "#"+vente.getVenteId()+" "+vente.getMarque()+" ["+vente.getReference()+"]";
        String sSousTitre =  shortDateFormat.format(vente.getDateAchat());
        String sDesc = "<b>Prix :</b> "+vente.getPrix()+" \n<br><b>A payé :</b> "+(vente.getPaye()?" le "+
                shortDateFormat.format(vente.getDatePaye()):"NON")+"\n";

        titre.setText(sTitre);
        sousTitre.setText(sSousTitre);

        // récupération des infos de l'acheteur
        long idAcheteur = vente.getAcheteurId();
        AcheteurDAO acheteurDAO = new AcheteurDAO(VentePageActivity.this);
        acheteurDAO.open();
        Acheteur acheteur = acheteurDAO.getAcheteur(idAcheteur);

        sDesc += "<br><b>Nom :</b> "+acheteur.getNom()+ "<br><b>Prénom :</b> "+acheteur.getPrenom()+
                "<br><b>Adresse :</b> "+acheteur.getAdresse()+ "<br><b>Tel :</b> "+acheteur.getTel()+
                "<br><b>Statut :</b> "+acheteur.getType();
        desc.setText(Html.fromHtml(sDesc));

        // switch pour payer ou non ---------------------------------------------------------------
        Button confirm = (Button) findViewById(R.id.bConfirm);
        Switch switchPaye = (Switch) findViewById(R.id.switchPaye);
        flagVeutPayer = false;      // pour le bouton de confirmation
        flagPaye = vente.getPaye(); // pour le statut de la vente
        switchPaye.setSelected(flagPaye);

        if(flagPaye){ // déjà payé
            switchPaye.setVisibility(View.GONE);
            confirm.setVisibility(View.GONE);
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flagVeutPayer){
                    Log.i("JOJO-ConfirmPAIEMENT","CONFIRM PAIEMENT");
                    VenteDAO venteDAO = new VenteDAO(VentePageActivity.this);
                    venteDAO.open();
                    venteDAO.setPaye(vente);
                    startActivity(new Intent(VentePageActivity.this,MainActivity.class));
                }
            }
        });

        // action de paiement
        switchPaye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i("JOJO-SwitchPaye","isChecked:"+isChecked+" / flagVeutPayer = "+isChecked);
            flagVeutPayer = isChecked;
            }
        });
        //-----------------------------------------------------------------------------------------
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
