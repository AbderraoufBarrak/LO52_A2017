package fr.utbm.lo52.shuttlesmgmt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import android.support.v7.widget.*;

import fr.utbm.lo52.shuttlesmgmt.adapters.AchatAdapter;
import fr.utbm.lo52.shuttlesmgmt.adapters.RecyclerTouchListener;
import fr.utbm.lo52.shuttlesmgmt.models.AchatModel;


public class AchatsActivity extends AppCompatActivity {

    protected RecyclerView patientsRecyclerView;
    protected AchatAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected ArrayList<AchatModel> mDataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achats);
        patientsRecyclerView = (RecyclerView) findViewById(R.id.achats_recyclerview);
        initDataSet();

        mAdapter= new AchatAdapter(mDataset);
        mLayoutManager= new LinearLayoutManager(this);
        patientsRecyclerView.setLayoutManager(mLayoutManager);
        patientsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        patientsRecyclerView.setAdapter(mAdapter);
        patientsRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, patientsRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                AchatModel achat = mDataset.get(position);
                Toast.makeText(getApplicationContext(), achat.getAcheteur() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AchatsActivity.this, FormulaireActivity.class);
                intent.putExtra("acheteur", achat.getAcheteur());
                intent.putExtra("quantite", achat.getQuantite());
                intent.putExtra("reference", achat.getReference());
                intent.putExtra("payer", achat.getPayer());
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
    protected void initDataSet(){
        mDataset = new ArrayList<AchatModel>();
        mDataset.add(new AchatModel("Vincent Berthe","26", "3","003", true));
        mDataset.add(new AchatModel("Franck Guepin","37", "5","001", false));
        mDataset.add(new AchatModel("Gharib Boumeziane","15", "2","002", true));

    }
}
