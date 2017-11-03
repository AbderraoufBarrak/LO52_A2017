package com.example.kiera.shuttlesmgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.round;

public class NouvelAchatActivity extends AppCompatActivity {

    private EditText editNom, editQtt;
    private CheckBox cbPaye;
    private TextView prix, stock;
    private Spinner spinRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvel_achat);

        editNom = (EditText)findViewById(R.id.editNom);
        editQtt = (EditText)findViewById(R.id.editQtt);
        cbPaye = (CheckBox) findViewById(R.id.cbPaye);
        prix = (TextView) findViewById(R.id.textPrix);
        stock = (TextView) findViewById(R.id.stockActuelView);

        Stock_BDD sb = new Stock_BDD(this);
        spinRef = (Spinner)findViewById(R.id.spinnerTube);
        ArrayList<String> spinnerArray = sb.selectRefs();
        sb.close();
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item,
                        spinnerArray);
        spinRef.setAdapter(spinnerArrayAdapter);

        if (getIntent().hasExtra("nomAcheteur")){ // on va supposer ici que un suffit


            editNom.setText(getIntent().getStringExtra("nomAcheteur"));
            editNom.setEnabled(false);

            spinRef.setSelection(spinnerArrayAdapter.getPosition(getIntent().getStringExtra("refTube")));
            spinRef.setEnabled(false);

            editQtt.setText(getIntent().getIntExtra("qtt",1)+"", TextView.BufferType.EDITABLE);
            editQtt.setEnabled(false);

            cbPaye.setChecked(getIntent().getBooleanExtra("paye",false));
            cbPaye.setText("Payé : "+getIntent().getDoubleExtra("prix",0)+" €");
            cbPaye.setEnabled(false);

            Button b = (Button) findViewById(R.id.bValiderAchat);
            b.setEnabled(false);
        }

        editQtt.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0) {
                    Stock_BDD sb = new Stock_BDD(NouvelAchatActivity.this);
                    double p = sb.getPrix((String) spinRef.getSelectedItem());
                    sb.close();
                    prix.setText(String.format("%.2f", p*Integer.parseInt(s.toString()))+" €");
                }else{
                    prix.setText("0.00 €");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        spinRef.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String qtt = editQtt.getText().toString();
                if(!Objects.equals(qtt, "")) {
                    Stock_BDD sb = new Stock_BDD(NouvelAchatActivity.this);
                    double p = sb.getPrix((String) spinRef.getSelectedItem());
                    int q = sb.getQtt((String) spinRef.getSelectedItem());
                    sb.close();
                    stock.setText("Stock : "+q);
                    editQtt.setFilters(new InputFilter[]{ new InputFilterMinMax(1, q)});
                    prix.setText(String.format("%.2f", p*Integer.parseInt(qtt))+" €");
                }else{
                    prix.setText("0.00 €");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                prix.setText("0.00 €");
            }
        });
    }

    public void clicConfirm(View view){
        if (editNom.getText().toString().equals("") || editQtt.getText().toString().equals("")){
            Toast t = Toast.makeText(this, "Entrez des valeurs correctes", Toast.LENGTH_LONG);
            t.show();
            return;
        }
        Achat a = new Achat(editNom.getText().toString(), (String) spinRef.getSelectedItem(), Integer.parseInt(editQtt.getText().toString()),
                Double.parseDouble(prix.getText().toString().split(" ")[0]), cbPaye.isChecked());
                // oui, c'est laid.
        Achat_BDD aBDD = new Achat_BDD(this);
        aBDD.ajouter(a);
        aBDD.close();
        Intent i = new Intent(this, AchatsActivity.class);
        startActivity(i);
    }
}
