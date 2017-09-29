package com.example.kiera.shuttlesmgmt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        List<Achat> list = genererAchats();

        AchatAdapter adapter = new AchatAdapter(this, list);
        mListView.setAdapter(adapter);
    }

    private List<Achat> genererAchats(){
        List<Achat> s = new ArrayList<>();
        s.add(new Achat("Madame Michu", "AS30", 1, 27, true));
        s.add(new Achat("Madame Michu", "Grade 3", 1, 16.7,false));
        s.add(new Achat("Societe Racket","AS30", 20, 540, true));
        s.add(new Achat("Societe Racket","Grade A9", 10, 137, false));
        return s;
    }
}
