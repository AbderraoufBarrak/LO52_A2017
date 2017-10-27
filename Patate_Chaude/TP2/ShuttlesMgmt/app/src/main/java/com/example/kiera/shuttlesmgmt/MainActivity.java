package com.example.kiera.shuttlesmgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clicStock(View view){
        Intent i = new Intent(this, StockActivity.class);
        startActivity(i);
    }

    public void clicAchats(View view){
        Intent i = new Intent(this, AchatsActivity.class);
        startActivity(i);
    }

    public void clicNouvelAchat(View view){
        Intent i = new Intent(this, NouvelAchatActivity.class);
        startActivity(i);
    }
}
