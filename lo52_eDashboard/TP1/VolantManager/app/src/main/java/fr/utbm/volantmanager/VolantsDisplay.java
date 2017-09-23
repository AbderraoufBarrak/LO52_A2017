package fr.utbm.volantmanager;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import fr.utbm.DAO.VolantsDAO;
import fr.utbm.database.DatabaseHandler;
import fr.utbm.entity.Volants;

/**
 * Activité affichant la liste des volants
 */
public class VolantsDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volants_display);

        // Instanciation directe du DAO (penser à passer par les couches controller/service plus tard)
        VolantsDAO volantsDAO = new VolantsDAO(VolantsDisplay.this);
        volantsDAO.open();

        // Ajout d'un volant en BDD
        Volants volantToAdd = new Volants("eDBTEAM", "B3ST V0LANT", "1");
        volantsDAO.addVolant(volantToAdd);

        // Récupération du volant en BDD
        Volants myVolant = volantsDAO.getVolant("eDBTEAM", "B3ST V0LANT");
        Log.d("eDBTEAM/VolantsDisplay", "returned volant : " + myVolant.toString());
    }
}
