package com.example.shuttlesmgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    int image[] = {R.drawable.A1013, R.drawable.A1015, R.drawable.AS30, R.drawable.RSLA9};
    ListView list;
    String marque[];
    String reference[];
    double stock[];
    String line;
    int i = 0;
    String[] splits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.setAdapter(CustomAdapter);
        File data = new File("C:\\Users\\Michel\\Desktop\\LO52_A2017\\Mi\\TP2\\ShuttlesMgmt\\app\\src\\main\\res\\data\\Data.txt");
        try{
            FileInputStream input = openFileInput("Data.txt");
            InputStreamReader inputreader = new InputStreamReader(input);
            BufferedReader buffreader = new BufferedReader(inputreader);
            while((line = buffreader.readLine()) != null){
                splits = line.split("-");
                marque[i] = splits[0];
                reference[i] = splits[1];
                stock[i] = double() splits[2];
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Button achatBtn = (Button) findViewById(R.id.btn_achat);
        final Button stockBtn = (Button) findViewById(R.id.btn_stock);

        achatBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Achats.class);
                startActivity(intent);
            }
        });

        stockBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Stock.class);
                startActivity(intent);
            }
        });
    }

}
