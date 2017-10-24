package fr.utbm.volantmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.utbm.DAO.LotVolantDAO;
import fr.utbm.DAO.VolantsDAO;
import fr.utbm.beans.LotInfo;
import fr.utbm.entity.LotVolant;
import fr.utbm.entity.Volant;
import fr.utbm.util.CustomAdapter;

/**
 * Activité affichant la liste des volants
 */
public class VolantsDisplay extends AppCompatActivity {

    private CustomAdapter adapter;
    private ArrayAdapter<String> spinnerAdapter;
    private List<LotInfo> myLotInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volants_display);

        ListView volantsLV = (ListView) findViewById(R.id.volants_lv);
        Spinner sortSP = (Spinner) findViewById(R.id.sort_sp);

        // --- DATABASE ---

        // Instanciation directe du DAO (penser à passer par les couches controller/service plus tard)
        VolantsDAO volantsDAO = new VolantsDAO(VolantsDisplay.this);
        LotVolantDAO lotVolantDAO = new LotVolantDAO(VolantsDisplay.this);

        volantsDAO.open();
        lotVolantDAO.open();

        // Récupération d'un volant en BDD
        Volant myVolant = volantsDAO.getVolant("eDBTEAM", "B3ST V0LANT");
        Log.d("eDBTEAM/VolantsDisplay", "returned volant : " + myVolant.toString());

        // Récupération de tous les types de volants en BDD
        List<Volant> myVolantList = new ArrayList<>();
        myVolantList = volantsDAO.getVolants();
        Log.d("eDBTEAM/VolantsDisplay", "All volants : " + myVolantList.toString());
        
        // Récupération des informations des lots
        myLotInfos = lotVolantDAO.getTestVolants(volantsDAO);
        Log.d("eDBTEAM/VolantsDisplay", "All lots infos : " + myLotInfos.toString());


        // --- LISTVIEW ---

        adapter = new CustomAdapter(this, myLotInfos);
        volantsLV.setAdapter(adapter);

        adapter.updateRecords(myLotInfos);


        sortSP.setAdapter(spinnerAdapter);
        spinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.sort_types));
        sortSP.setAdapter(spinnerAdapter);

        sortSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "Tri par " + adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                if(adapterView.getItemAtPosition(position).equals(getResources().getString(R.string.sort_stock))) {
                    myLotInfos = adapter.sortList(myLotInfos, 1);
                } else if(adapterView.getItemAtPosition(position).equals(getResources().getString(R.string.sort_price))) {
                    myLotInfos = adapter.sortList(myLotInfos, 2);
                } else if(adapterView.getItemAtPosition(position).equals(getResources().getString(R.string.sort_brand))) {
                    myLotInfos = adapter.sortList(myLotInfos, 3);
                } else if(adapterView.getItemAtPosition(position).equals(getResources().getString(R.string.sort_ref))) {
                    myLotInfos = adapter.sortList(myLotInfos, 4);
                } else {
                    myLotInfos = adapter.sortList(myLotInfos, 0);
                }
                adapter.updateRecords(myLotInfos);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }
}
