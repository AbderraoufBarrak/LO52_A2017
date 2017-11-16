package com.lo52.dewback.shuttlesmgmt.stock_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.lo52.dewback.shuttlesmgmt.R;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.StockDataBean;
import com.lo52.dewback.shuttlesmgmt.stock_activity.view.stock_CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class Stock extends AppCompatActivity {

    List<StockDataBean> dataModels;
    ListView listView;
    private static stock_CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        dataModels = new ArrayList<>();
        //TODO Utiliser la BDD pour gather les données (passer par une classe de DAO)
        dataModels.add(new StockDataBean(0, "Yonex", "YONEX_AS30", 27.0, 500, R.drawable.yonex_as30));
        dataModels.add(new StockDataBean(0, "RSL", "Grade 3", 16.7, 5000, R.drawable.rsl_grade_a3));
        dataModels.add(new StockDataBean(0, "RSL", "Grade A9", 13.7, 10000, R.drawable.rsl_grade_a9));
        dataModels.add(new StockDataBean(0, "RSL", "Grade A1", 21.0, 6000, R.drawable.rsl_grade_a1));

        adapter = new stock_CustomAdapter(dataModels, getApplicationContext());

        listView=(ListView)findViewById(R.id.stockListView);
        listView.setAdapter(adapter);
    }
}
