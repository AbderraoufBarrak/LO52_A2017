package io.hervenrv.shuttlesmgmt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Stock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        ArrayList<String> text = new ArrayList<>();
        text.add("Yonex - AS30 - Stock de 500 - 27 euros le tube");
        text.add("RSL - Grade 3 - Stock de 5000 - 16,70 euros le tube");
        text.add("RSL - Grade A9 - Stock de 10000 - 13.70 euros le tube");
        text.add("RSL - Grade A1  - Stock de 6000 - 21 euros le tube");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1, text);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}
