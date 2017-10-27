package com.example.shuttlesmgmt.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shuttlesmgmt.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button achatBtn = (Button) findViewById(R.id.btn_achat);
        final Button stockBtn = (Button) findViewById(R.id.btn_stock);
        final Button dbBtn = (Button) findViewById(R.id.btn_dbAchat);

        achatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AchatsActivity.class);
                startActivity(intent);
            }
        });

        stockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StockActivity.class);
                startActivity(intent);
            }
        });

        dbBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AchatsActivity.class);//Changement de class apres
                startActivity(intent);
            }
        });
    }

}
