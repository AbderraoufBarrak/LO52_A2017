package utbm.fr.ffbad;

import android.content.Intent;
import android.os.Bundle;
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

import utbm.fr.ffbad.adapter.StockListAdapter;
import utbm.fr.ffbad.db.DbHelper;
import utbm.fr.ffbad.entity.StockLine;

public class MainActivity extends AppCompatActivity {

    private ListView stock_listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_layout);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.stock_toolbar);
        setSupportActionBar(myToolbar);


        DbHelper dbHelper = new DbHelper(this,DbHelper.DATABASE_NAME,null,1);

        //Retrieving widgets
        this.stock_listView = (ListView) findViewById(R.id.stock_listView);

        //Populating the listView
        final List<StockLine> stockLines = dbHelper.getStock();
        ListAdapter adapter = new StockListAdapter(this, stockLines);
        this.stock_listView.setAdapter(adapter);

        this.stock_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,PurchaseFormActivity.class);
                StockLine stockLine = stockLines.get(i);
                intent.putExtra("TUBE_REF",stockLine.getTube().getRef());
                intent.putExtra("PRICE",stockLine.getTube().getPrix());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.purchase:
                goToPurchases();
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
        inflater.inflate(R.menu.menu_stock, menu);
        return true;
    }

    private void goToPurchases(){

        Intent intent = new Intent(this, PurchaseActivity.class);
        startActivity(intent);

    }
}
