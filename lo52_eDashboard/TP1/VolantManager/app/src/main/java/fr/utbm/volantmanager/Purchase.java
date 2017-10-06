package fr.utbm.volantmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.utbm.DAO.AcheterDAO;
import fr.utbm.DAO.AcheteurDAO;
import fr.utbm.DAO.DateDAO;
import fr.utbm.DAO.LotVolantDAO;
import fr.utbm.entity.Acheter;
import fr.utbm.entity.Acheteur;
import fr.utbm.entity.Date;
import fr.utbm.volantmanager.R;

public class Purchase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        Log.d("eDBTEAM/purchase", "Achats -> Ouverture");

        AcheterDAO acheterDAO = new AcheterDAO(Purchase.this);
        AcheteurDAO acheteurDAO = new AcheteurDAO(Purchase.this);
        DateDAO dateDAO = new DateDAO(Purchase.this);
        LotVolantDAO lotVolantDAO = new LotVolantDAO(Purchase.this);

        acheterDAO.open();
        acheteurDAO.open();
        dateDAO.open();
        lotVolantDAO.open();

        acheterDAO.eraseContent();
        acheteurDAO.eraseContent();
        dateDAO.eraseContent();


        // Ajout d'une Date
        String dateString = "06/10/2017";
        java.util.Date date = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            date = df.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dateDAO.addDate(new Date(date));

        // Ajout d'un acheteur
        acheteurDAO.addAcheteur(new Acheteur("Micka", "LeDieu", "UTBM"));

        // Ajout d'un achat
        acheterDAO.addAcheter(new Acheter(lotVolantDAO.getMaxID(lotVolantDAO.TABLE_NAME, lotVolantDAO.ID), dateDAO.getMaxID(dateDAO.TABLE_NAME, dateDAO.ID), acheteurDAO.getMaxID(acheteurDAO.TABLE_NAME, acheteurDAO.MATRICULE), false));

        // Récuépération des achats
        List<Acheter> achats = new ArrayList<>();
        achats = acheterDAO.getAchats();

        Log.d("eDBTEAM/Purchase", "Achats -> " + achats.toString()); // Ici on voit qu'on récupère tous les bons ID
        // TODO - Requête plus complexe dans AcheterDAO, avec création d'un nouveau beans qui rénunit tous les éléments nécessaires
        // TODO - Nouvel Adapter affichant les informations de la nouvelle entité
        // TODO - Ajouter plus d'entrées en BDD. Garder le remplissage ici BDD ici ? Le basculer sur la première activité ?
    }
}
