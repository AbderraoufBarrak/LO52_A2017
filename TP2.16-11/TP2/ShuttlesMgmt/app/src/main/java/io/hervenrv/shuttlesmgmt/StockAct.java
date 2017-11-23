package io.hervenrv.shuttlesmgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import io.hervenrv.shuttlesmgmt.BDD.Produit.ProduitDAO;
import io.hervenrv.shuttlesmgmt.BDD.Stock.Stock;
import io.hervenrv.shuttlesmgmt.BDD.Stock.StockDAO;

public class StockAct extends AppCompatActivity {

    private ProduitDAO produitDAO;
    private StockDAO stockDAO;
    private StockAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        produitDAO = MainActivity.produitDAO;
        stockDAO = MainActivity.stockDAO;

        final ArrayList<Stock> stock = stockDAO.getList();

        ListView listView = (ListView) findViewById(R.id.listview);

        adapter = new StockAdapter(this, stock, produitDAO);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StockAct.this, FormulaireAct.class);
                Stock selected = stock.get(position);
                intent.putExtra("REF", selected.getRef());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.clear();
        adapter.addAll(stockDAO.getList());
        adapter.notifyDataSetChanged();
    }


}
