package com.example.missornela.shuttlesmanagement.Controller;

/**
 * Created by Miss ornela on 12/11/2017.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.missornela.shuttlesmanagement.MainActivity;
import com.example.missornela.shuttlesmanagement.Model.Client;
import com.example.missornela.shuttlesmanagement.Model.Facture;
import com.example.missornela.shuttlesmanagement.R;

import static android.R.attr.data;

public class ShuttlesRegActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText prenomInput,nomInput,quantiteInput;
    TextView prenomView,nomView,quantiteView,tubeView;
    CheckBox paye;
    Spinner tubeReference;
    Button  ajoutFacture;
    private MySQLiteHelper db;
    boolean payeAchat;
    long idTube,quant;
    Client cli = new Client();
    final String EXTRA_NOM_CLIENT = "Nom";
    final String EXTRA_PRENOM_CLIENT = "Prenom";
    final String EXTRA_REFERENCE_TUBE = "Reference";
    final String EXTRA_QUANTITE_FACTURE = "Quantite";
    final String EXTRA_PAYE_FACTURE = "paye";


    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttlesreg);
        Intent intent = getIntent();
        context = this;
        db = new MySQLiteHelper(this);

        paye = (CheckBox)findViewById(R.id.payeFormulaire_checkbox);
        prenomInput = (EditText)findViewById(R.id.prenomFormulaire_edittext);
        quantiteInput = (EditText)findViewById(R.id.quantiteFormulaire_edittext);
        nomInput = (EditText)findViewById(R.id.nomFormulaire_edittext);
        ajoutFacture =(Button) findViewById(R.id.ajouterFormulaire_btn);
        prenomView = (TextView)findViewById(R.id.prenomFormulaire_textview);
        quantiteView = (TextView)findViewById(R.id.quantiteFormulaire_textview);
        nomView = (TextView)findViewById(R.id.nomFormulaire_textview);
        tubeView = (TextView)findViewById(R.id.purchasereg_textview);
        tubeReference= (Spinner) findViewById(R.id.purchasereg_Spinner);
        tubeReference.setOnItemSelectedListener(this);


        // lorquel'on choisit un achat
        if (intent != null && intent.getFlags()==40) {
            nomInput.setText(intent.getStringExtra(EXTRA_NOM_CLIENT));
            prenomInput.setText(intent.getStringExtra(EXTRA_PRENOM_CLIENT));
            quantiteInput.setText(intent.getStringExtra(EXTRA_QUANTITE_FACTURE));
            String ref=intent.getStringExtra(EXTRA_REFERENCE_TUBE);
            viewTubes1(ref);

            if (Integer.parseInt(intent.getStringExtra(EXTRA_PAYE_FACTURE))== 0)
            {
                paye.setChecked(false);
            }
            else
            {
                paye.setChecked(true);
            }



            prenomInput.setEnabled(false);
            nomInput.setEnabled(false);
            paye.setEnabled(false);
            quantiteInput.setEnabled(false);
            tubeReference.setEnabled(false);
            ajoutFacture.setEnabled(false);
            getPrenomInputText();
            getNomInputText();
        }
        else{
            getPrenomInputText();
            getNomInputText();
            viewTubes();
            //addFactureData();

        }


    }


    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.payeFormulaire_checkbox:
                if (checked)
                    payeAchat = true;

                else

                    payeAchat = false;
                break;
        }
    }


    public void onImageHomeClicked(View view) {
        Intent intent = new Intent(ShuttlesRegActivity.this, MainActivity.class);
        intent.addFlags(41);
        startActivity(intent);
    }



    private void addFactureData() {
        ajoutFacture.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cli.setmPrenom(prenomInput.getText().toString());
                        cli.setmNom(nomInput.getText().toString());

                        Cursor curs = db.getClient(cli);

                        if(curs.getCount()==0)
                        {
                            showMessage("Error Customer","Please enter a registered customer");
                            return;
                        }
                        else
                        {if (curs.moveToFirst())
                            {
                                if (quantiteInput.getText().toString().length()== 0)
                                {
                                    showMessage("Quantity Error ","enter a valid shuttle's quantity");
                                    return;
                                }
                                else {
                                    Facture f = new Facture(curs.getLong(0), Integer.parseInt(quantiteInput.getText().toString()), payeAchat, idTube);
                                    // insertion des données du formulaire dans la table facture
                                    boolean b = db.insertFactureData(f);
                                    if (b) {
                                        //purchase well created
                                        showMessage("Well done", "Purchase created");
                                        nomInput.setText("");
                                        prenomInput.setText("");
                                        quantiteInput.setText("");
                                        paye.setChecked(false);
                                    }
                                }
                            }


                        }

                    }

                }
        );
    }

    public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
        //choix d'une reference
        Cursor cursor= (Cursor)parent.getItemAtPosition(pos);
        idTube = cursor.getLong(0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void viewTubes()
    {
        Cursor data = db.getAllStock();

        if(data.getCount()==0)
        {
            return;
        }
        String[] from = new String[] {"Reference"};

        int[] to = new int[] {
                R.id.reference_formulaireCol_textview};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,
                R.layout.activity_shuttlesregline, data, from, to, 0);
        tubeReference.setAdapter(adapter);
    }

    private void viewTubes1(String ref)
    {
        Cursor data = db.getAllStock1(ref);

        if(data.getCount()==0)
        {
            return;
        }
        String[] from = new String[] { "Reference"};

        int[] to = new int[] {
                R.id.reference_formulaireCol_textview};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,
                R.layout.activity_shuttlesregline, data, from, to, 0);
        tubeReference.setAdapter(adapter);

    }


    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .show();

    }

    public void getNomInputText()
    {
        nomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                cli.setmNom(nomInput.getText().toString());
            }
        });
    }

    public void getPrenomInputText()
    {
        prenomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    //////////////////// menu/////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //Méthode qui se déclenchera au clic sur un item
    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        switch (item.getItemId()) {
            case R.id.menu_purchase:

                Intent myIntent = new Intent(this, PurchaseActivity.class);
                startActivityForResult(myIntent,0);
                return true;

            case R.id.menu_stocks:

                Intent myIntent1 = new Intent(this, StockActivity.class);
                startActivityForResult(myIntent1,0);
                return true;

            case R.id.menu_shuttlesreg:

                Intent myIntent2 = new Intent(this, ShuttlesRegActivity.class);
                startActivityForResult(myIntent2,0);
                return true;


        }


        return false;
    }
}
