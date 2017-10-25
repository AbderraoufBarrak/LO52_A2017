package fr.utbm.volantmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import fr.utbm.DAO.LotVolantDAO;
import fr.utbm.DAO.VolantsDAO;
import fr.utbm.entity.LotVolant;
import fr.utbm.entity.Volant;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VolantsDAO volantsDAO = new VolantsDAO(MainActivity.this);
        volantsDAO.open();

        //Remplissage des volants si c'est la première connexion et que par conséquent la table est vide
        if (volantsDAO.isEmpty()){

            //Suppression du contenu des lots de volants si il y en avait
            LotVolantDAO lotVolantDAO = new LotVolantDAO(MainActivity.this);
            lotVolantDAO.open();
            lotVolantDAO.eraseContent();

            //Insertion des lots de volants
            lotVolantDAO.addLotVolant(new LotVolant(500, 27f));
            lotVolantDAO.addLotVolant(new LotVolant(5000, 16.70f));
            lotVolantDAO.addLotVolant(new LotVolant(10000, 13.70f));
            lotVolantDAO.addLotVolant(new LotVolant(6000, 21f));
            lotVolantDAO.addLotVolant(new LotVolant(1000, 10f));

            //Insertion des volants
            volantsDAO.addVolant(new Volant("Yonex", "AS30", "3", (int) (lotVolantDAO.getMaxID(lotVolantDAO.TABLE_NAME, lotVolantDAO.ID) - 4)));
            volantsDAO.addVolant(new Volant("RSL", "Grade 3", "1", (int) (lotVolantDAO.getMaxID(lotVolantDAO.TABLE_NAME, lotVolantDAO.ID) - 3)));
            volantsDAO.addVolant(new Volant("RSL", "Grade A9", "2", (int) (lotVolantDAO.getMaxID(lotVolantDAO.TABLE_NAME, lotVolantDAO.ID) - 2)));
            volantsDAO.addVolant(new Volant("RSL", "Grade A1", "2", (int) (lotVolantDAO.getMaxID(lotVolantDAO.TABLE_NAME, lotVolantDAO.ID) - 1)));
            volantsDAO.addVolant(new Volant("eDBTEAM", "B3ST V0LANT", "3", (int) (lotVolantDAO.getMaxID(lotVolantDAO.TABLE_NAME, lotVolantDAO.ID))));
            volantsDAO.addVolant(new Volant("Undefined", "Undefined", "0", (int) (lotVolantDAO.getMaxID(lotVolantDAO.TABLE_NAME, lotVolantDAO.ID))));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // on ne fait rien quand on est sur la page d'accueil
        return true;
    }

    /**
     * Accès à l'activité VolantsDisplay
     * @param view
     */
    public void browseVolants(View view) {
        Intent intent = new Intent(this, VolantsDisplay.class);
        startActivity(intent);
    }

    /**
     * Accès à l'activité Purchase
     * @param view
     */
    public void purchaseVolants(View view) {
        Intent intent = new Intent(this, Purchase.class);
        startActivity(intent);
    }

    /**
     * Accès à l'activité PurchaseItem
     * @param view
     */
    public void purchaseItem(View view) {
        Intent intent = new Intent(this, PurchaseItem.class);
        startActivity(intent);
    }
}
