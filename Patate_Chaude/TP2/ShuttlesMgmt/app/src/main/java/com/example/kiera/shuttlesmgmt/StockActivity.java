package com.example.kiera.shuttlesmgmt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StockActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        mListView = (ListView) findViewById(R.id.listView);

        List<Stock> list = genererStock();

        StockAdapter adapter = new StockAdapter(this, list);
        mListView.setAdapter(adapter);
    }

    private List<Stock> genererStock(){
        List<Stock> s = new ArrayList<Stock>();
        s.add(new Stock(R.drawable.t1, "Yonex", "AS30", 500, 27));
        s.add(new Stock(R.drawable.t2, "RSL", "Grade 3", 5000, 16.7));
        s.add(new Stock(R.drawable.t3, "RSL", "Grade A9", 10000, 13.7));
        s.add(new Stock(R.drawable.t4, "RSL", "Grade A1", 6000, 21));
        return s;
    }

}
