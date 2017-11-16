package io.hervenrv.shuttlesmgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Achats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achats);

        List<AchatListEntry> achats = genererAchat();

        ListView listView = (ListView) findViewById(R.id.listview);

        AchatAdapter adapter = new AchatAdapter(this, achats);
        if(adapter!=null) {
            listView.setAdapter(adapter);
        }
    }

    private List<AchatListEntry> genererAchat(){
        List<AchatListEntry> stocks = new ArrayList<AchatListEntry>();
        stocks.add(new AchatListEntry("Pierre PAUL", "AS30", 4, true));
        stocks.add(new AchatListEntry("Jacques MARTIN", "RSL3", 2, false));
        return stocks;
    }

    public void openFormulaireAchat(View view) {
        Intent intent = new Intent(this, Formulaire.class);
        startActivity(intent);
    }
}
