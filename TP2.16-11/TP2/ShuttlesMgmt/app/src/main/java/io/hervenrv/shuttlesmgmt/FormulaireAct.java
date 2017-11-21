package io.hervenrv.shuttlesmgmt;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import io.hervenrv.shuttlesmgmt.BDD.Achat.Achat;
import io.hervenrv.shuttlesmgmt.BDD.Achat.AchatDAO;
import io.hervenrv.shuttlesmgmt.BDD.Produit.ProduitDAO;
import io.hervenrv.shuttlesmgmt.BDD.Stock.Stock;
import io.hervenrv.shuttlesmgmt.BDD.Stock.StockDAO;

public class FormulaireAct extends AppCompatActivity {

    private boolean setId;
    private int id;
    private AchatDAO achatDAO;
    private ProduitDAO produitDAO;
    private StockDAO stockDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        setId = false;
        achatDAO = MainActivity.achatDAO;
        produitDAO = MainActivity.produitDAO;
        stockDAO = MainActivity.stockDAO;

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            if(extras == null) {
                setId = false;
            } else {
                setId = true;
                id= (extras.getInt("ID"));
            }
        } else {
            setId = true;
            id= Integer.parseInt((String) savedInstanceState.getSerializable("ID"));
        }


        if(setId)
        {
            FormulaireAct.FomulaireBiewHolder viewHolder = null;
            viewHolder = new FormulaireAct.FomulaireBiewHolder();
            viewHolder.ref = (TextView) findViewById(R.id.ref);
            viewHolder.acheteur = (EditText) findViewById(R.id.acheteur);
            viewHolder.quantite = (EditText) findViewById(R.id.quantite);
            viewHolder.toggleButton = (ToggleButton) findViewById(R.id.toggleButton);


            Achat selected = achatDAO.selectionner(id);
            viewHolder.ref.setText(selected.getRef());
            viewHolder.acheteur.setText(selected.getAcheteur());
            viewHolder.acheteur.setFreezesText(true);
            viewHolder.quantite.setText(""+ selected.getQuantite());
            viewHolder.acheteur.setFreezesText(true);
            viewHolder.toggleButton.setChecked(selected.getPaye());
            if(selected.getPaye())
                viewHolder.toggleButton.setFreezesText(true);
            else
                viewHolder.toggleButton.setFreezesText(false);

        }



    }


    public void saveAchat(View view) {

    }

    public class FomulaireBiewHolder{
        public TextView ref;
        public EditText acheteur;
        public EditText quantite;
        public ToggleButton toggleButton;
    }
}
