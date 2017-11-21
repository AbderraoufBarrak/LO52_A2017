package io.hervenrv.shuttlesmgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import io.hervenrv.shuttlesmgmt.BDD.Achat.Achat;
import io.hervenrv.shuttlesmgmt.BDD.Achat.AchatDAO;
import io.hervenrv.shuttlesmgmt.BDD.Produit.ProduitDAO;

public class AchatsAct extends AppCompatActivity {

    private ProduitDAO produitDAO;
    private AchatDAO achatDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achats);

        produitDAO = MainActivity.produitDAO;
        achatDAO = MainActivity.achatDAO;

        ArrayList<Achat> achats = achatDAO.getList();

        ListView listView = (ListView) findViewById(R.id.listview);

        AchatAdapter adapter = new AchatAdapter(this, achats, produitDAO);
        if(adapter!=null) {
            listView.setAdapter(adapter);
        }



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AchatsAct.this, FormulaireAct.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });
    }



}
