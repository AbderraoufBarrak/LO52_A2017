package com.example.kiera.shuttlesmgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AchatsActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achats);

        mListView = (ListView) findViewById(R.id.listView2);

        List<Achat> list = recupAchats();

        AchatAdapter adapter = new AchatAdapter(this, list);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long l){
                Achat item = (Achat) adapter.getItemAtPosition(position);

                Intent intent = new Intent(AchatsActivity.this,NouvelAchatActivity.class);
                intent.putExtra("nomAcheteur",item.getAcheteur());
                intent.putExtra("refTube",item.getRef());
                intent.putExtra("prix",item.getPrix());
                intent.putExtra("qtt",item.getQtt());
                intent.putExtra("paye",item.isPaye());
                //based on item add info to intent
                startActivity(intent);
            }
        });
    }

    private List<Achat> recupAchats(){
        List<Achat> s = new ArrayList<>();
        //s.add(new Achat("Madame Michu", "AS30", 1, 27, true));
        //s.add(new Achat("Madame Michu", "Grade 3", 1, 16.7,false));
        //s.add(new Achat("Societe Racket","AS30", 20, 540, true));
        //s.add(new Achat("Societe Racket","Grade A9", 10, 137, false));
        Achat_BDD aBDD = new Achat_BDD(this);
        s.addAll(aBDD.selectAll());
        aBDD.close();
        return s;
    }
}
