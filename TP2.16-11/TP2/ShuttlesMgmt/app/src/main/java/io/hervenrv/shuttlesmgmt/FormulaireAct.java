package io.hervenrv.shuttlesmgmt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.hervenrv.shuttlesmgmt.BDD.Achat.Achat;
import io.hervenrv.shuttlesmgmt.BDD.Achat.AchatDAO;
import io.hervenrv.shuttlesmgmt.BDD.Produit.ProduitDAO;
import io.hervenrv.shuttlesmgmt.BDD.Stock.Stock;
import io.hervenrv.shuttlesmgmt.BDD.Stock.StockDAO;

public class FormulaireAct extends AppCompatActivity {

    private boolean setId;
    private int id;
    private String ref;
    private AchatDAO achatDAO;
    private ProduitDAO produitDAO;
    private StockDAO stockDAO;
    private Achat currentAchat;
    private FormulaireAct.FomulaireBiewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        setId = false;
        ref = null;
        achatDAO = MainActivity.achatDAO;
        produitDAO = MainActivity.produitDAO;
        stockDAO = MainActivity.stockDAO;

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null) {
            String s = extras.getString("ID", null);
            if(s != null) {
                id = Integer.parseInt(s);
                setId = true;
            }
            else{
                ref = extras.getString("REF", null);
            }
        }

        currentAchat = null;

        if(viewHolder == null) {
            viewHolder = new FormulaireAct.FomulaireBiewHolder();
            viewHolder.ref = (TextView) findViewById(R.id.ref);
            viewHolder.acheteur = (EditText) findViewById(R.id.acheteur);
            viewHolder.quantite = (EditText) findViewById(R.id.quantite);
            viewHolder.toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
            viewHolder.save = (Button) findViewById(R.id.saveButton);
            viewHolder.refList = (Spinner) findViewById(R.id.liste);
            viewHolder.save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveAchat();
                }
            });
        }

        if(setId)
        {
            currentAchat = achatDAO.selectionner(id);
            viewHolder.ref.setText(currentAchat.getRef());
            viewHolder.acheteur.setText(currentAchat.getAcheteur());
            enableEditText(viewHolder.acheteur, false);
            viewHolder.quantite.setText(""+ currentAchat.getQuantite());
            enableEditText(viewHolder.quantite, false);
            viewHolder.toggleButton.setChecked(currentAchat.getPaye());
            enableToggle(viewHolder.toggleButton, !currentAchat.getPaye());
            if(currentAchat.getPaye()) {
                viewHolder.save.setVisibility(Button.GONE);
                enableButton(viewHolder.save, false);
            }

        }else if (ref != null){
            viewHolder.ref.setText(ref);
        } else {
            List<Stock> stockList = stockDAO.getList();

            ArrayList<String> refList = new ArrayList<>();

            for(Stock el : stockList){
                refList.add(el.getRef());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.activity_formulaire, R.id.liste ,refList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            viewHolder.refList.setAdapter(adapter);
        }



    }


    public void saveAchat() {

        Achat test;
        try {
            boolean checked = viewHolder.toggleButton.isChecked();
            String acheteur = viewHolder.acheteur.getText().toString();
            if (acheteur.length() < 2)
                return;

            String ref = viewHolder.ref.getText().toString();
            int quantite = Integer.parseInt(viewHolder.quantite.getText().toString());
            if (quantite < 1)
                return;
            String date = new Date().toString();
            test = new Achat(0, acheteur, ref, quantite, checked, date);
        }catch(Exception e){
            return;
        }

        if(!setId){
            currentAchat = test;
            Stock selected = stockDAO.selectionner(currentAchat.getRef());
            int quantite = selected.getQuantite();
            selected.setQuantite(selected.getQuantite()-currentAchat.getQuantite());

            if(selected.getQuantite() < 0){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage("Stock insufisant, Restant : " + quantite)
                        .setTitle("Action impossible");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                    }
                });

                AlertDialog dialog = builder.create();

                dialog.show();

                return;
            }else{
                if(selected.getQuantite() == 0)
                    stockDAO.supprimer(selected.getRef());
                else
                    stockDAO.modifier(selected);
            }


            achatDAO.ajouter(currentAchat);

        } else {
            currentAchat.setPaye(test.getPaye());
            achatDAO.modifier(currentAchat);
        }

        onBackPressed();
    }

    public class FomulaireBiewHolder{
        public TextView ref;
        public EditText acheteur;
        public EditText quantite;
        public ToggleButton toggleButton;
        public Button save;
        public Spinner refList;
    }

    private void enableEditText(EditText editText, boolean enable) {
        editText.setFocusable(enable);
        editText.setEnabled(enable);
        editText.setCursorVisible(enable);
    }

    private void enableToggle(ToggleButton toggleButton, boolean enable){
        toggleButton.setFocusable(enable);
        toggleButton.setEnabled(enable);
    }

    private void enableButton(Button toggleButton, boolean enable){
        toggleButton.setFocusable(enable);
        toggleButton.setEnabled(enable);
    }
}
