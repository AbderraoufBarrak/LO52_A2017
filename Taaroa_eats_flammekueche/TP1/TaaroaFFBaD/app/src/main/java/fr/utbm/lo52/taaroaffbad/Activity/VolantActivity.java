package fr.utbm.lo52.taaroaffbad.Activity;

import android.content.Intent;
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

        Intent intent = getIntent();
        ArrayList<Volant> test = (ArrayList<Volant>) intent.getSerializableExtra("volantList");

        listView = (ListView) findViewById(R.id.listVolant);
        listView.setAdapter(new VolantAdapter(VolantActivity.this, test));
    }
}
