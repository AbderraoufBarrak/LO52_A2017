package io.hervenrv.shuttlesmgmt;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import io.hervenrv.shuttlesmgmt.BDD.Achat.Achat;
import io.hervenrv.shuttlesmgmt.BDD.Achat.AchatDAO;
import io.hervenrv.shuttlesmgmt.BDD.Produit.ProduitDAO;

public class AchatsAct extends AppCompatActivity{

    private ProduitDAO produitDAO;
    private AchatDAO achatDAO;
    private ArrayList<Achat> achats;
    private AchatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achats);

        produitDAO = MainActivity.produitDAO;
        achatDAO = MainActivity.achatDAO;

        achats = achatDAO.getList();

        ListView listView = (ListView) findViewById(R.id.listview);

        adapter = new AchatAdapter(this, achats, produitDAO);
        if(adapter!=null) {
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AchatsAct.this, FormulaireAct.class);
                Achat selected = achats.get(position);
                intent.putExtra("ID", "" + selected.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.clear();
        adapter.addAll(achatDAO.getList());
        adapter.notifyDataSetChanged();
    }
}
