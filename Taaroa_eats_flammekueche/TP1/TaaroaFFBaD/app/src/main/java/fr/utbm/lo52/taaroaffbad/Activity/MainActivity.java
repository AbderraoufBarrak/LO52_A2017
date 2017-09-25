package fr.utbm.lo52.taaroaffbad.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import fr.utbm.lo52.taaroaffbad.Beans.Volant;
import fr.utbm.lo52.taaroaffbad.Database.VolantDAO;
import fr.utbm.lo52.taaroaffbad.R;

public class MainActivity extends AppCompatActivity {

    public Button btnTest;
    public ArrayList<Volant> test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VolantDAO volantsDAO = new VolantDAO(MainActivity.this);
        volantsDAO.open();

        Volant volantToAdd = new Volant("2017-2018","2018-2019", "ARTENGO", "BSC 950", 3);
        Volant volantToAdd_2 = new Volant("2017-2017","2018-2018", "aaa", "Blbala0", 1);
        volantsDAO.addVolant(volantToAdd);
        volantsDAO.addVolant(volantToAdd_2);


        test = volantsDAO.getVolant();

        for (Volant v : test){
            Log.d("YVAN",v.toString());
        }


        btnTest = (Button)findViewById(R.id.btnTest);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VolantActivity.class);

                intent.putExtra("volantList", test);
                MainActivity.this.startActivity(intent);
            }
        });

    }
}
