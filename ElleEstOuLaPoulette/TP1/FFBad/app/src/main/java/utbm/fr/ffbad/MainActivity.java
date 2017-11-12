package utbm.fr.ffbad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import utbm.fr.ffbad.adapter.StockListAdapter;
import utbm.fr.ffbad.db.DbHelper;
import utbm.fr.ffbad.entity.StockLine;

public class MainActivity extends AppCompatActivity {

    private Button achats_btn;

    private ListView stock_listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_layout);
        DbHelper dbHelper = new DbHelper(this,DbHelper.DATABASE_NAME,null,1);

        //Retrieving widgets
        this.achats_btn = (Button) findViewById(R.id.achats_btn);
        this.stock_listView = (ListView) findViewById(R.id.stock_listView);

        //Populating the listView
        List<StockLine> stockLines = dbHelper.getStock();
        ListAdapter adapter = new StockListAdapter(this, stockLines);
        this.stock_listView.setAdapter(adapter);

        this.stock_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,PurchaseFormActivity.class);
                intent.putExtra("test",i);
                startActivity(intent);
            }
        });

        achats_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPurchases();
            }
        });

    }

    private void goToPurchases(){

        Intent intent = new Intent(this, PurchaseActivity.class);
        startActivity(intent);

    }
}
