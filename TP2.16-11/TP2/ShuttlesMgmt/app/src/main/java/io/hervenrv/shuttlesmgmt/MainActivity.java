package io.hervenrv.shuttlesmgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.hervenrv.shuttlesmgmt.BDD.Achat.Achat;
import io.hervenrv.shuttlesmgmt.BDD.Achat.AchatDAO;
import io.hervenrv.shuttlesmgmt.BDD.Produit.Produit;
import io.hervenrv.shuttlesmgmt.BDD.Produit.ProduitDAO;
import io.hervenrv.shuttlesmgmt.BDD.Stock.Stock;
import io.hervenrv.shuttlesmgmt.BDD.Stock.StockDAO;

public class MainActivity extends AppCompatActivity {

    public static ProduitDAO produitDAO;
    public static AchatDAO achatDAO;
    public static StockDAO stockDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getApplicationContext().deleteDatabase("Achat.db");
        //getApplicationContext().deleteDatabase("Produit.db");
        //getApplicationContext().deleteDatabase("Stock.db");

        achatDAO = new AchatDAO(getApplicationContext());
        stockDAO = new StockDAO(getApplicationContext());
        produitDAO = new ProduitDAO(getApplicationContext());

        achatDAO.open();
        stockDAO.open();
        produitDAO.open();

        //GenereAchats();
        //GenereStock();
        //GenereProduits();
    }

    public void openAchat(View view) {
        Intent intent = new Intent(this, AchatsAct.class);
        startActivity(intent);
    }

    public void openStock(View view) {
        Intent intent = new Intent(this, StockAct.class);
        startActivity(intent);
    }

    public void openFormulaire(View view) {
        Intent intent = new Intent(this, Formulaire.class);
        startActivity(intent);
    }

    public void GenereProduits(){

        produitDAO.ajouter(new Produit("Grade_3", "RSL", 5.0, "No 3", R.drawable.rsl_3));
        produitDAO.ajouter(new Produit("Grade_A9", "RSL", 4.0, "A9", R.drawable.rsl_a9));
        produitDAO.ajouter(new Produit("Grade_A1", "RSL", 6.0, "A1", R.drawable.rsl_a1));
        produitDAO.ajouter(new Produit("AS30", "Yonex", 11.0, "AS30", R.drawable.yonex_as30));
    }

    public void GenereAchats() {
        achatDAO.ajouter(new Achat(0,"Jean", "Grade_3", 2, true, "26/02/2017"));
        achatDAO.ajouter(new Achat(0,"Louis", "Grade_3", 1, false, "30/02/2017"));
        achatDAO.ajouter(new Achat(0,"Ablert", "AS30", 1, true, "11/04/2017"));

    }

    public void GenereStock(){
        stockDAO.ajouter(new Stock("Grade_3", 400));
        stockDAO.ajouter(new Stock("Grade_A9", 200));
        stockDAO.ajouter(new Stock("AS30", 50));
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        achatDAO.close();
        stockDAO.close();
        produitDAO.close();
    }
}
