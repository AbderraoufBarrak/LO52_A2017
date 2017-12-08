package com.lo52.flo.lardesports;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP;

/**
 * Activité gérant l'affichage de tous les achats
 */
public class AchatActivity extends AppCompatActivity {

    /**
     * Fonction onCreate appelée lors de la création de l'activité
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(new GradientDrawable(BOTTOM_TOP, new int[]{0xFFA796FF, 0xFF3529FF}));
        ab.setTitle("Achats");

        LinkedList<Achat> achats = new LinkedList<Achat>();

        for(Achat achat : MainActivity.bdd.getAllAchats()){
            achats.add(achat);
        }

        AchatAdapter adapter = new AchatAdapter(getApplicationContext(), R.layout.achat_layout, achats);
        ListView list_achats = (ListView) findViewById(R.id.list_achats);
        list_achats.setAdapter(adapter);
        list_achats.setOnItemClickListener(listview_listener);
    }

    /**
     * Fonction appelée lors de l'appuie sur un achat de la liste
     * Elle lance l'activité VolantsGestionActivity en mode "lecture seule"
     * @param achatIndice : indice de l'achat sur lequel l'utilisateur a cliqué
     */
    private void onClickOnAchat(int achatIndice){
        Intent intent = new Intent(this, VolantsGestionActivity.class);
        intent.putExtra("LECUTRE_SEULE", true);
        intent.putExtra("ACHAT_ID", achatIndice);
        startActivityForResult(intent, 0);
    }

    /**
     * Fonction appelée lorsque l'activité VolantsGestionActivity est terminée
     * Elle actualise la liste des achats avec les nouvelles valeurs
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0)
            this.recreate();
    }

    /**
     * Défini l'action à réaliser lors de l'appuie sur un achat par l'utilisateur
     */
    AdapterView.OnItemClickListener listview_listener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
            onClickOnAchat(position+1);
        }
    };
}
