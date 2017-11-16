package utbm.fr.ffbad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import utbm.fr.ffbad.adapter.PurchaseListAdapter;
import utbm.fr.ffbad.adapter.StockListAdapter;
import utbm.fr.ffbad.db.DbHelper;
import utbm.fr.ffbad.entity.Purchase;
import utbm.fr.ffbad.entity.PurchaseLine;
import utbm.fr.ffbad.entity.StockLine;

/**
 * Created by Antoine on 11/11/2017.
 */

public class PurchaseActivity extends AppCompatActivity {

    private ListView purchaseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_layout);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.purchase_toolbar);
        setSupportActionBar(myToolbar);


        DbHelper dbHelper = new DbHelper(this,DbHelper.DATABASE_NAME,null,1);

        //Retrieving widgets
        this.purchaseListView = (ListView) findViewById(R.id.purchaseListView);

        //Populating the listView
        List<Purchase> purchases = dbHelper.getPurchases();
        ListAdapter adapter = new PurchaseListAdapter(this, purchases);
        this.purchaseListView.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.stocks:
                goToStocks();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_purchases, menu);
        return true;
    }

    private void goToStocks(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
