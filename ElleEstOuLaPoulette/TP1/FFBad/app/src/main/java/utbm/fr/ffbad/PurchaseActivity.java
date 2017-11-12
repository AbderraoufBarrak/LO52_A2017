package utbm.fr.ffbad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import utbm.fr.ffbad.adapter.PurchaseListAdapter;
import utbm.fr.ffbad.adapter.StockListAdapter;
import utbm.fr.ffbad.db.DbHelper;
import utbm.fr.ffbad.entity.PurchaseLine;
import utbm.fr.ffbad.entity.StockLine;

/**
 * Created by Antoine on 11/11/2017.
 */

public class PurchaseActivity extends AppCompatActivity {


    private Button stockButton;

    private ListView purchaseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_layout);

        DbHelper dbHelper = new DbHelper(this,DbHelper.DATABASE_NAME,null,1);

        //Retrieving widgets
        stockButton = (Button) findViewById(R.id.stocksButton);
        this.purchaseListView = (ListView) findViewById(R.id.purchaseListView);

        //Populating the listView
        List<PurchaseLine> purchaseLines = dbHelper.getPurchases();
        ListAdapter adapter = new PurchaseListAdapter(this, purchaseLines);
        this.purchaseListView.setAdapter(adapter);


        stockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToStocks();
            }
        });
    }

    private void goToStocks(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
