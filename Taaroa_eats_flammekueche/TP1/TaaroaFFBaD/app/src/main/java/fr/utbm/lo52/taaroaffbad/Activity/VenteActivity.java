package fr.utbm.lo52.taaroaffbad.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Vente listItem = venteList.get(position);
                Log.d("JOJO",listItem.getReference());

                Intent intent = new Intent(VenteActivity.this,VentePageActivity.class);
                intent.putExtra("Vente",listItem);

                startActivity(intent);
            }
        });

    }

    // HOME Button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_home) {
            startActivity(new Intent(this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
