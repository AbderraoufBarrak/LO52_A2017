package fr.utbm.volantmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.utbm.DAO.AcheterDAO;
import fr.utbm.DAO.AcheteurDAO;
import fr.utbm.DAO.DateDAO;
import fr.utbm.DAO.LotVolantDAO;
import fr.utbm.DAO.VolantsDAO;
import fr.utbm.beans.AchatInfo;
import fr.utbm.entity.Acheter;
import fr.utbm.entity.Acheteur;
import fr.utbm.entity.Date;
import fr.utbm.util.AchatInfoCustomAdapter;
import fr.utbm.util.CustomAdapter;
import fr.utbm.volantmanager.R;

// TODO - L'affichage de l'activité Purchase est peut-être pas opti
// TODO - Ajouter plus d'entrées en BDD. Garder le remplissage ici BDD ici ? Le basculer sur la première activité ? Faudrait qu'il soit fixe aussi, au lieu de tout supprimer & remettre à chaque fois
// TODO - Tri sur la ListView ? par date ? par acheteur ? par prix ?
// TODO - Y'a des open() sans close() partout
// TODO - Le code est moche là
// TODO - Surbrillance item selectionné

public class Purchase extends AppCompatActivity {

    private AchatInfoCustomAdapter adapter;
    private List<AchatInfo> achatInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        final ListView achatsLV = (ListView) findViewById(R.id.achats_lv);


        AcheterDAO acheterDAO = new AcheterDAO(Purchase.this);
        AcheteurDAO acheteurDAO = new AcheteurDAO(Purchase.this);
        DateDAO dateDAO = new DateDAO(Purchase.this);
        LotVolantDAO lotVolantDAO = new LotVolantDAO(Purchase.this);
        VolantsDAO volantDAO = new VolantsDAO(Purchase.this);

        acheterDAO.open();
        acheteurDAO.open();
        dateDAO.open();
        lotVolantDAO.open();
        volantDAO.open();

        //acheterDAO.eraseContent();
        //acheteurDAO.eraseContent();
        //dateDAO.eraseContent();


        // AJOUTS D'ACHATS
        /*// Ajout d'une Date
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
        acheterDAO.addAcheter(new Acheter(lotVolantDAO.getMaxID(lotVolantDAO.TABLE_NAME, lotVolantDAO.ID), dateDAO.getMaxID(dateDAO.TABLE_NAME, dateDAO.ID), acheteurDAO.getMaxID(acheteurDAO.TABLE_NAME, acheteurDAO.MATRICULE), 10, false));
        acheterDAO.addAcheter(new Acheter(lotVolantDAO.getMaxID(lotVolantDAO.TABLE_NAME, lotVolantDAO.ID) - 1, dateDAO.getMaxID(dateDAO.TABLE_NAME, dateDAO.ID), acheteurDAO.getMaxID(acheteurDAO.TABLE_NAME, acheteurDAO.MATRICULE), 3, true));*/

        // Récupération des achats
        List<Acheter> achats = new ArrayList<>();
        Log.d("eDBTEAM/Purchase","appel de la liste");
        achats = acheterDAO.getAchats();

        Log.d("eDBTEAM/Purchase", "Achats -> " + achats.toString()); // Ici on voit qu'on récupère tous les bons ID

        // Récupération avancée
        achatInfos = acheterDAO.getAchatsInfos(volantDAO, lotVolantDAO, dateDAO, acheteurDAO);

        Log.d("eDBTEAM/Purchase", "AchatInfos -> " + achatInfos.toString());

        // LIST VIEW

        adapter = new AchatInfoCustomAdapter(this, achatInfos);
        achatsLV.setAdapter(adapter);

        adapter.updateRecords(achatInfos);

        achatsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(getBaseContext(), PurchaseItem.class);
                intent.putExtra("achatInfoID", achatInfos.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
