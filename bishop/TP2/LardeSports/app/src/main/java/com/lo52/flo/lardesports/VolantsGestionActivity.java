package com.lo52.flo.lardesports;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP;

public class VolantsGestionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final List<String> tubeList = new ArrayList<String>();
    private final List<String> marqueList = new ArrayList<String>();
    private BDD bdd;
    private int selectedMarque = 1;
    private Spinner tubeSpinner;
    private Spinner marqueSpinner;

    private Button plusButton;
    private Button minusButton;
    private EditText quantite;
    private EditText acheteurEdittext;
    private CheckBox isPayedCheckBox;

    private Achat currentAchat;
    private int currentId = -1;

    private boolean readOnly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volants_gestion);

        readOnly = getIntent().getBooleanExtra("LECTURE_SEULE", true);

        bdd = MainActivity.bdd;

        plusButton = (Button)findViewById(R.id.plus_button);
        minusButton = (Button)findViewById(R.id.minus_button);
        quantite = (EditText)findViewById(R.id.quantite_gestion);

        marqueSpinner = (Spinner) findViewById(R.id.marque_typespinner);
        tubeSpinner = (Spinner) findViewById(R.id.tube_typespinner);

        acheteurEdittext = (EditText) findViewById(R.id.acheteur_name);

        isPayedCheckBox = (CheckBox) findViewById(R.id.achat_paid);

        // Spinner click listener
        marqueSpinner.setOnItemSelectedListener(this);
        tubeSpinner.setOnItemSelectedListener(this);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(new GradientDrawable(BOTTOM_TOP, new int[]{0xFFA796FF, 0xFF3529FF}));
        ab.setTitle("Gestion");

        for(MarqueBDD marque : bdd.getAllMarques()){
            marqueList.add(marque.getLibelle());
        }
        for(ArticleBDD article : bdd.getAllArticles()){
            tubeList.add(article.getRef());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> marqueDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, marqueList);
        ArrayAdapter<String> tubeDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tubeList);

        // attaching data adapter to spinner
        marqueSpinner.setAdapter(marqueDataAdapter);
        tubeSpinner.setAdapter(tubeDataAdapter);

        // Set the readonly mode
        if(readOnly){

            int achatId = getIntent().getIntExtra("ACHAT_ID", -1);
            if(achatId > 0){
                currentAchat = MainActivity.bdd.getAchatWithId(achatId);
                currentId = achatId;
            }
            quantite.setText(Integer.toString(currentAchat.getQuantite()));
            acheteurEdittext.setText(currentAchat.getAcheteur());

            isPayedCheckBox.setChecked(currentAchat.getPaye());

            ArticleBDD currentArticle = MainActivity.bdd.getArticleBDDById(currentAchat.getVolantId());
            MarqueBDD currentMarque = MainActivity.bdd.getMarqueById(currentArticle.getMarque());

            tubeSpinner.setSelection(tubeDataAdapter.getPosition(currentArticle.getRef()));
            marqueSpinner.setSelection(marqueDataAdapter.getPosition(currentMarque.getLibelle()));

            quantite.setEnabled(false);
            //((Spinner) tubeSpinner).getSelectedView().setEnabled(false);
            tubeSpinner.setEnabled(false);
            //((Spinner) marqueSpinner).getSelectedView().setEnabled(false);
            marqueSpinner.setEnabled(false);
            acheteurEdittext.setEnabled(false);
            minusButton.setEnabled(false);
            plusButton.setEnabled(false);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.toString().contains("tube_typespinner")){

        }
        else if(parent.toString().contains("marque_typespinner")){
            selectedMarque = position + 1;

            tubeList.clear();

            for(ArticleBDD article : bdd.getArticlesWithMarque(selectedMarque)){
                tubeList.add(article.getRef());
            }
            ArrayAdapter<String> tubeDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tubeList);
            tubeSpinner.setAdapter(tubeDataAdapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void onPlusButtonClick(View view){
        if(!readOnly){
            int newValue;
            try{
                newValue=Integer.parseInt(quantite.getText().toString()) +1;
            }catch (Exception e){
                newValue = 0;
            }
            quantite.setText(Integer.toString(newValue));
        }
    }

    public void onMinusButtonClick(View view){
        if(!readOnly){
            int newValue;
            try{
                newValue=Integer.parseInt(quantite.getText().toString()) -1;
            }catch (Exception e){
                newValue = 0;
            }
            quantite.setText(Integer.toString(newValue));
        }
    }

    public void onSaveButtonClick(View view){

        if(readOnly && currentId > 0){
            MainActivity.bdd.updateAchat(currentId, currentAchat.getAcheteur(), currentAchat.getVolantId(),
                    currentAchat.getQuantite(), isPayedCheckBox.isChecked());
        }
        else{
            MainActivity.bdd.insertAchat(acheteurEdittext.getText().toString(),
                    MainActivity.bdd.getArticleIdWithRef(tubeSpinner.getSelectedItem().toString()),
                    Integer.parseInt(quantite.getText().toString()),
                    isPayedCheckBox.isChecked());
        }
        Toast.makeText(getApplicationContext(), "Achat sauvé", Toast.LENGTH_SHORT).show();
        finish();
    }


}
