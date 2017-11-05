package com.example.shuttlesmgmt.activity.Version1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.shuttlesmgmt.entity.Achat;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.adapter.Version1.AchatAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AchatsActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achats);
        Log.i("AppInfo", "je suis dans activity achat");

        list = (ListView) findViewById(R.id.id_listAchat);
        List<Achat> achatList = new ArrayList<Achat>();

        InputStream inputstream = getResources().openRawResource(R.raw.data_achat);

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
                    achatList.add(new Achat(splits[1], splits[2], splits[3], splits[4], splits[0], splits[5], splits[6]));
                }
                buffreader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            Log.i("AppInfo", "Jai pas trouve le fichier");
        }
        achatList.add(new Achat("wtf", "wtf" ,"200" ,"100" ,"a1013", "BEBE", "y"));
        achatList.add(new Achat("wf", "wf" ,"200" ,"100" ,"a1013", "HALLO", "n"));

        AchatAdapter achatAdapter = new AchatAdapter(this, achatList);
        list.setAdapter(achatAdapter);
    }

}
