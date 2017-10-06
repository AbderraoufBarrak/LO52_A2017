package fr.utbm.lo52.taaroaffbad.Activity;

import android.content.Intent;
import java.text.DateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import fr.utbm.lo52.taaroaffbad.Beans.Vente;
import fr.utbm.lo52.taaroaffbad.Beans.Volant;
import fr.utbm.lo52.taaroaffbad.Database.VenteDAO;
import fr.utbm.lo52.taaroaffbad.R;

public class VentePageActivity extends AppCompatActivity {

    Vente vente;
    TextView titre, sousTitre, desc;

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
        String sDesc = "Prix : "+vente.getPrix()+" \n A payé : "+(vente.getPaye()?" le "+shortDateFormat.format(vente.getDatePaye()):"NON");

        titre.setText(sTitre);
        sousTitre.setText(sSousTitre);
        desc.setText(sDesc);

        // switch pour payer ou non ---------------------------------------------------------------
        Switch switchPaye = (Switch) findViewById(R.id.switchPaye);
        boolean flagPaye = vente.getPaye();
        switchPaye.setSelected(flagPaye);

        if(flagPaye) // déjà payé
            switchPaye.setVisibility(View.GONE);

        // action de paie
        switchPaye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.i("JOJO-SwitchPaye","isChecked:"+isChecked);
                    VenteDAO venteDAO = new VenteDAO(VentePageActivity.this);
                    venteDAO.open();
                    venteDAO.setPaye(vente);
                }
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
