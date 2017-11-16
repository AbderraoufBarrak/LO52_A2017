package com.lo52.dewback.shuttlesmgmt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.lo52.dewback.shuttlesmgmt.stock_activity.model.AchatDataBean;
import com.lo52.dewback.shuttlesmgmt.stock_activity.view.achat_CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class Achats extends AppCompatActivity {

    List<AchatDataBean> dataModels;
    ListView listView;
    private static achat_CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        dataModels = new ArrayList<>();
        //TODO Utiliser la BDD pour gather les données (passer par une classe de DAO)
        dataModels.add(new AchatDataBean("Yonex", "YONEX_AS30", 27.0, 500, R.drawable.yonex_as30));
        dataModels.add(new AchatDataBean("RSL", "Grade 3", 16.7, 5000, R.drawable.rsl_grade_a3));
        dataModels.add(new AchatDataBean("RSL", "Grade A9", 13.7, 10000, R.drawable.rsl_grade_a9));
        dataModels.add(new AchatDataBean("RSL", "Grade A1", 21.0, 6000, R.drawable.rsl_grade_a1));

        adapter = new achat_CustomAdapter(dataModels, getApplicationContext());

        listView=(ListView)findViewById(R.id.stockListView);
        listView.setAdapter(adapter);
    }
}
