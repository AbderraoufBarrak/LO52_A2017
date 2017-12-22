package fr.utbm.lo52.shuttlesmgmt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FormulaireActivity extends AppCompatActivity {

    Button save_btn;
    EditText acheteur, quantite;
    ToggleButton payer;
    Spinner reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
//create a list of items for the spinner.
        String[] items = new String[]{"001", "002", "003", "004"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        save_btn = findViewById(R.id.enregistrer_btn);
        acheteur = findViewById(R.id.acheteur_tf);
        quantite = findViewById(R.id.quantite_tf);
        payer = findViewById(R.id.toggleButton);
        reference = findViewById(R.id.spinner);

        if(getIntent().getExtras() != null){
            acheteur.setText(getIntent().getStringExtra("acheteur"));
            quantite.setText(getIntent().getStringExtra("quantite"));
            if (!getIntent().getStringExtra("reference").equals(null)) {
                int spinnerPosition = adapter.getPosition(getIntent().getStringExtra("reference"));
                reference.setSelection(spinnerPosition);
            }
            payer.setChecked(getIntent().getBooleanExtra("payer", true));
            Toast.makeText(getApplicationContext(), Boolean.toString(getIntent().getBooleanExtra("payer", true)), Toast.LENGTH_SHORT).show();
        }

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), acheteur.getText().toString() +"; "+quantite.getText().toString() +"; "+payer.getText().toString() +"; "+ reference.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}