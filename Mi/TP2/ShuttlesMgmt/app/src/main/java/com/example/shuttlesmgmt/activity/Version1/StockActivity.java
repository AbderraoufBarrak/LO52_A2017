package com.example.shuttlesmgmt.activity.Version1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.entity.Version1.Stock;
import com.example.shuttlesmgmt.adapter.Version1.StockAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StockActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        list = (ListView) findViewById(R.id.list_stock);

        List<Stock> stockList = new ArrayList<Stock>();

        Log.i("AppInfo", "Je suis dans activy_stock");

        InputStream inputstream = getResources().openRawResource(R.raw.data_stock);

        String[] splits;
        String lines;

        if(inputstream != null){
            Log.i("AppInfo", "J'ai trouve le fichier");
            InputStreamReader inputreader = new InputStreamReader(inputstream);
            BufferedReader buffreader = new BufferedReader(inputreader);

            try{
                while((lines = buffreader.readLine()) != null){
                    Log.i("AppInfo", "Line :" + lines);
                    splits = lines.split(" - ");
                    stockList.add(new Stock(splits[1], splits[2], splits[3], splits[0], splits[4]));
                }
                buffreader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            Log.i("AppInfo", "Jai pas trouve le fichier");
        }

        stockList.add(new Stock("wtf", "wtf" ,"200" , "a1013", "20"));
        StockAdapter stockAdapter = new StockAdapter(this, stockList);
        list.setAdapter(stockAdapter);
    }

}
