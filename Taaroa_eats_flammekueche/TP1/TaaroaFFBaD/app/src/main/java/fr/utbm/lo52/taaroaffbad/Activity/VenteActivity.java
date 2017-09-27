package fr.utbm.lo52.taaroaffbad.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import fr.utbm.lo52.taaroaffbad.Adapter.VenteAdapter;
import fr.utbm.lo52.taaroaffbad.Adapter.VolantAdapter;
import fr.utbm.lo52.taaroaffbad.Beans.Vente;
import fr.utbm.lo52.taaroaffbad.Beans.Volant;
import fr.utbm.lo52.taaroaffbad.R;

public class VenteActivity extends AppCompatActivity {

    public ListView listView;
    ArrayList<Vente> venteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vente_activity);

        Intent intent = getIntent();
        venteList = (ArrayList<Vente>) intent.getSerializableExtra("venteList");

        listView = (ListView) findViewById(R.id.listVente);
        listView.setAdapter(new VenteAdapter(VenteActivity.this, venteList));



    }
}
