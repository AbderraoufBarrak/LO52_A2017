package io.hervenrv.shuttlesmgmt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import io.hervenrv.shuttlesmgmt.BDD.Produit.ProduitDAO;
import io.hervenrv.shuttlesmgmt.BDD.Stock.Stock;
import io.hervenrv.shuttlesmgmt.BDD.Stock.StockDAO;

public class StockAct extends AppCompatActivity {

    private ProduitDAO produitDAO;
    private StockDAO stockDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        produitDAO = MainActivity.produitDAO;
        stockDAO = MainActivity.stockDAO;

        ArrayList<Stock> stock = stockDAO.getList();

        ListView listView = (ListView) findViewById(R.id.listview);

        StockAdapter adapter = new StockAdapter(this, stock, produitDAO);
        listView.setAdapter(adapter);
    }


}
