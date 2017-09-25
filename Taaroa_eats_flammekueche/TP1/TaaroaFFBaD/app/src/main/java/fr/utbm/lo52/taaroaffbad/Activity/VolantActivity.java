package fr.utbm.lo52.taaroaffbad.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.ArrayList;

import fr.utbm.lo52.taaroaffbad.Adapter.VolantAdapter;
import fr.utbm.lo52.taaroaffbad.Beans.Volant;
import fr.utbm.lo52.taaroaffbad.R;

public class VolantActivity extends AppCompatActivity {

    public ListView listView;
    ArrayList<Volant> volantList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volant);

        Intent intent = getIntent();
        volantList = (ArrayList<Volant>) intent.getSerializableExtra("volantList");

        listView = (ListView) findViewById(R.id.listVolant);
        listView.setAdapter(new VolantAdapter(VolantActivity.this, volantList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Volant listItem = volantList.get(position);
                Log.d("JOJO",listItem.getReference());

                Intent intent = new Intent(VolantActivity.this,VolantPageActivity.class);
                intent.putExtra("validite1_volant",listItem.getValidite_1());
                intent.putExtra("validite2_volant",listItem.getValidite_2());
                intent.putExtra("marque_volant",listItem.getMarque());
                intent.putExtra("reference",listItem.getReference());
                intent.putExtra("classement",listItem.getClassement());

                startActivity(intent);
            }
        });

    }
}
