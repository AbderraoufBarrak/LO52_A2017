package io.hervenrv.shuttlesmgmt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Achats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achats);

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
    }
}
