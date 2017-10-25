package io.hervenrv.shuttlesmgmt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Stock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        List<ListEntry> stock = genererStock();

        ListView listView = (ListView) findViewById(R.id.listview);

        StockAdapter adapter = new StockAdapter(this, stock);
        listView.setAdapter(adapter);
    }

    private List<ListEntry> genererStock(){
        List<ListEntry> stocks = new ArrayList<ListEntry>();
        stocks.add(new ListEntry("Yonex", "AS30", 500, R.drawable.yonex_as30));
        stocks.add(new ListEntry("RSL", "Grade 3", 5000, R.drawable.rsl_3));
        stocks.add(new ListEntry("RSL", "Grade A9", 10000, R.drawable.rsl_a9));
        stocks.add(new ListEntry("RSL", "Grade A1", 6000, R.drawable.rsl_a1));
        return stocks;
    }
}
