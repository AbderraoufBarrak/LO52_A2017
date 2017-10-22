package utbm.fr.ffbad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Adapter;
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
        setContentView(R.layout.activity_main);
        DbHelper dbHelper = new DbHelper(this,DbHelper.DATABASE_NAME,null,1);
        Log.v("TEST",dbHelper.getDatabaseName());

        //Retrieving widgets
        this.achats_btn = (Button) findViewById(R.id.achats_btn);
        this.stock_listView = (ListView) findViewById(R.id.stock_listView);

        //Populating the listView
        List<StockLine> stockLines = dbHelper.getStock();
        ListAdapter adapter = new StockListAdapter(this, stockLines);
        this.stock_listView.setAdapter(adapter);

    }
}
