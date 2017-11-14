package com.example.android.contentprovidersample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AchatVenteActivity extends AppCompatActivity {

    public static final int ACHETER = 1;
    public static final int VENDRE = 2;

    private int item_row;
    private int action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat_vente);

        Intent intent = getIntent();
        item_row = intent.getIntExtra("ITEM_ROW", 0);
        action = intent.getIntExtra("ACTION", 0);

        TextView mText = findViewById(R.id.nomVolantMarche);
        switch (action){
            case ACHETER:
                mText.setText("Achat");
                break;
            case VENDRE:
                mText.setText("Vente");
                break;
        }
    }
}
