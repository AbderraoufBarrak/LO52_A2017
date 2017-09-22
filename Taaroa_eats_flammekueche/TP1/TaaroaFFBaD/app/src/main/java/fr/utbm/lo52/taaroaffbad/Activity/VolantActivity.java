package fr.utbm.lo52.taaroaffbad.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import fr.utbm.lo52.taaroaffbad.R;

public class VolantActivity extends AppCompatActivity {

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volant);

        String[] stringsArray = new String[30];
        for (int i = 0; i < 30; ++i)
            stringsArray[i] = Integer.toString(i);
        listView = (ListView)findViewById(R.id.listVolant);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(VolantActivity.this,
                android.R.layout.simple_list_item_1, stringsArray);
        listView.setAdapter(adapter);

    }
}
