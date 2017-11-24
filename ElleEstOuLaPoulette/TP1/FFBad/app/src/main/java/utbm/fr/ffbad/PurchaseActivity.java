package utbm.fr.ffbad;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import utbm.fr.ffbad.adapter.PurchaseListAdapter;
import utbm.fr.ffbad.db.DbHelper;
import utbm.fr.ffbad.entity.Purchase;

/**
 * Created by Antoine on 11/11/2017.
 */

public class PurchaseActivity extends AppCompatActivity {

    private ListView purchaseListView;
    private DbHelper dbHelper;
    private String cmdRef;
    private List<Purchase> purchases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_layout);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.purchase_toolbar);
        setSupportActionBar(myToolbar);
        dbHelper = new DbHelper(this,DbHelper.DATABASE_NAME,null,1);
        //Retrieving widgets
        this.purchaseListView = (ListView) findViewById(R.id.purchaseListView);
        //Populating the listView
        purchases = dbHelper.getPurchases();
        ListAdapter adapter = new PurchaseListAdapter(this, purchases);
        this.purchaseListView.setAdapter(adapter);
        setListener();


    }

    private void setListener(){
        this.purchaseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Purchase purchase = purchases.get(i);
                updatePayment(view, purchase.getCmdRef());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.stocks:
                returnToStocks();
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

    private void returnToStocks(){
        finish();
    }

    public void updatePayment(View v, final String ref){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Mettre à jour le payement");
        builder.setPositiveButton("PAYE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.updatePaid(true, ref);
                purchaseListView = (ListView) findViewById(R.id.purchaseListView);
                //Populating the listView
                List<Purchase> purchases = dbHelper.getPurchases();
                ListAdapter adapter = new PurchaseListAdapter(PurchaseActivity.this, purchases);
                purchaseListView.setAdapter(adapter);
                setListener();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NON PAYE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.updatePaid(false, ref);
                purchaseListView = (ListView) findViewById(R.id.purchaseListView);
                //Populating the listView
                List<Purchase> purchases = dbHelper.getPurchases();
                ListAdapter adapter = new PurchaseListAdapter(PurchaseActivity.this, purchases);
                purchaseListView.setAdapter(adapter);
                setListener();
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
