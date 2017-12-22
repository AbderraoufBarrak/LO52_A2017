package com.example.gl52.lo52_application_mages.BD;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button achats, formulaire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        achats = findViewById(R.id.button2);

        achats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(MenuActivity.this, AchatsActivity.class);
                    startActivity(intent);

            }
        });
         formulaire= findViewById(R.id.button3);

        formulaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, FormulaireActivity.class);
                startActivity(intent);

            }
        });
    }
}
