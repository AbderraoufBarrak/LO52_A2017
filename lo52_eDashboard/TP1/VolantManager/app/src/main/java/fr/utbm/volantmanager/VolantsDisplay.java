package fr.utbm.volantmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.utbm.DAO.VolantsDAO;
import fr.utbm.entity.Volant;
import fr.utbm.util.CustomAdapter;

/**
 * Activité affichant la liste des volants
 */
public class VolantsDisplay extends AppCompatActivity {

    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volants_display);

        ListView volantsLV = (ListView) findViewById(R.id.volants_lv);


        // --- DATABASE ---

        // Instanciation directe du DAO (penser à passer par les couches controller/service plus tard)
        VolantsDAO volantsDAO = new VolantsDAO(VolantsDisplay.this);

        volantsDAO.open();

        // Efface le contenu de la table
        volantsDAO.eraseContent();

        // Ajout d'un volant en BDD
        volantsDAO.addVolant(new Volant("eDBTEAM", "B3ST V0LANT", "1"));
        volantsDAO.addVolant(new Volant("Vieu Volant", "NUL", "4"));
        volantsDAO.addVolant(new Volant("Volant OK", "TRKL", "2"));
        volantsDAO.addVolant(new Volant("Futur Volant", "FV", "3"));

        // Récupération d'un volant en BDD
        Volant myVolant = volantsDAO.getVolant("eDBTEAM", "B3ST V0LANT");
        Log.d("eDBTEAM/VolantsDisplay", "returned volant : " + myVolant.toString());

        // Récupération de tous les volants en BDD
        List<Volant> myVolantList = new ArrayList<>();
        myVolantList = volantsDAO.getVolants();
        Log.d("eDBTEAM/VolantsDisplay", "All volants : " + myVolantList.toString());

        // --- LISTVIEW ---

        adapter = new CustomAdapter(this, myVolantList);
        volantsLV.setAdapter(adapter);

        adapter.updateRecords(myVolantList);
    }
}
