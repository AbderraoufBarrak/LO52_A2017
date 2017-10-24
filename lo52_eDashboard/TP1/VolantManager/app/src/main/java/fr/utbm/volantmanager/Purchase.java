package fr.utbm.volantmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.utbm.DAO.AcheterDAO;
import fr.utbm.DAO.AcheteurDAO;
import fr.utbm.DAO.DateDAO;
import fr.utbm.DAO.LotVolantDAO;
import fr.utbm.DAO.VolantsDAO;
import fr.utbm.beans.AchatInfo;
import fr.utbm.entity.Acheter;
import fr.utbm.util.AchatInfoCustomAdapter;


// TODO - Tri sur la ListView ? par date ? par acheteur ? par prix ?
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // avec le bouton retour, on retombe automatiquement sur la page d'accueil
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
