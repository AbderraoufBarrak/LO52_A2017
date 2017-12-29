package io.hervenrv.shuttlesmgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.View;
=======
import android.widget.ArrayAdapter;
>>>>>>> 04216c7b2355aeb6fc5fbb9f0ea9fe0f065f6999
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Achats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achats);

<<<<<<< HEAD
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
=======
        List<ListEntryAchats> achats = genererAchats();

        ListView listView = (ListView) findViewById(R.id.listview);

        AchatsAdapter adapter = new AchatsAdapter(this, achats);
        listView.setAdapter(adapter);
    }

    private List<ListEntryAchats> genererAchats(){
        List<ListEntryAchats> achats = new ArrayList<ListEntryAchats>();
        achats.add(new ListEntryAchats("Yonex", "AS30", 15, R.drawable.yonex_as30));
        achats.add(new ListEntryAchats("RSL", "Grade 3", 5, R.drawable.rsl_3));
        achats.add(new ListEntryAchats("RSL", "Grade A9", 10, R.drawable.rsl_a9));
        achats.add(new ListEntryAchats("RSL", "Grade A1", 6, R.drawable.rsl_a1));
        return achats;
>>>>>>> 04216c7b2355aeb6fc5fbb9f0ea9fe0f065f6999
    }
}
