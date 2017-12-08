package com.lo52.flo.lardesports;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP;

public class AchatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(new GradientDrawable(BOTTOM_TOP, new int[]{0xFFA796FF, 0xFF3529FF}));
        ab.setTitle("Achats");

        LinkedList<Achat> achats = new LinkedList<Achat>();

        for(Achat achat : MainActivity.bdd.getAllAchats()){
            achats.add(achat);
        }

        AchatAdapter adapter = new AchatAdapter(getApplicationContext(), R.layout.achat_layout, achats);
        ListView list_achats = (ListView) findViewById(R.id.list_achats);
        list_achats.setAdapter(adapter);
        list_achats.setOnItemClickListener(listview_listener);
    }

    private void onClickOnAchat(int achatIndice){
        Intent intent = new Intent(this, VolantsGestionActivity.class);
        intent.putExtra("LECUTRE_SEULE", true);
        intent.putExtra("ACHAT_ID", achatIndice);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0)
            this.recreate();
    }

    AdapterView.OnItemClickListener listview_listener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
            onClickOnAchat(position+1);
        }
    };
}
