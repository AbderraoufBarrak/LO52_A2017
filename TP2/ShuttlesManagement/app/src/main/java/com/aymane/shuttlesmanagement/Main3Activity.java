package com.aymane.shuttlesmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.shuttles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if(getIntent().hasExtra("ref"))
        {
            Bundle extras = getIntent().getExtras();
            if(extras.getString("ref").equals("AS30"))
                    spinner.setSelection(0);
            if(extras.getString("ref").equals("Grade 3"))
                    spinner.setSelection(1);
            if(extras.getString("ref").equals("Grade A9"))
                    spinner.setSelection(2);
            if(extras.getString("ref").equals("Grade A1"))
                    spinner.setSelection(3);


            spinner.setEnabled(false);

            EditText acheteur= (EditText) findViewById(R.id.editAcheteur);
            acheteur.setText(extras.getString("acheteur"));
            acheteur.setEnabled(false);

            EditText qte= (EditText) findViewById(R.id.editQte);
            qte.setText(extras.getString("qte"));
            qte.setEnabled(false);

            CheckBox paye = (CheckBox) findViewById(R.id.checkBox);
            paye.setEnabled(false);
            if(extras.getString("couleur").equals("#ff0000"))
            {
                paye.setChecked(false);
            }
            else
            {
                paye.setChecked(true);
            }

            Button b = (Button) findViewById(R.id.button);
            b.setEnabled(false);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_achats:
                startActivity(new Intent(Main3Activity.this, MainActivity.class));
                return true;
            case R.id.item_stock:
                startActivity(new Intent(Main3Activity.this, Main2Activity.class));
                return true;
            case R.id.item_form:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
