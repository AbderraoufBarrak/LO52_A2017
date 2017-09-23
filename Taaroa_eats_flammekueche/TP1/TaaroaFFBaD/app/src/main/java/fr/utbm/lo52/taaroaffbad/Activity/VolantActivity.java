package fr.utbm.lo52.taaroaffbad.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import fr.utbm.lo52.taaroaffbad.Adapter.VolantAdapter;
import fr.utbm.lo52.taaroaffbad.Beans.Volant;
import fr.utbm.lo52.taaroaffbad.R;

public class VolantActivity extends AppCompatActivity {

    public ListView listView;
    ArrayList<Volant> volantList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volant);

        volantList.add(new Volant("2015-2016","206-2017","Toshibo","Test",2));
        volantList.add(new Volant("2015-2016","206-2017","Blabla","aaaaa",1));
        volantList.add(new Volant("2015-2016","206-2017","Micka","Test",3));
        volantList.add(new Volant("2015-2016","206-2017","Yvan","aaaaa",1));

        listView = (ListView) findViewById(R.id.listVolant);
        listView.setAdapter(new VolantAdapter(VolantActivity.this, volantList));

    }
}
