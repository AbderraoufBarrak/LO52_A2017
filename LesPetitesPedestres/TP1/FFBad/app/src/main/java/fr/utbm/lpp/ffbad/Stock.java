package fr.utbm.lpp.ffbad;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.widget.ListView;

import fr.utbm.lpp.ffbad.data.adapter.ShuttlecockCursorAdapter;
import fr.utbm.lpp.ffbad.data.db.FFBadDbContract;

public class Stock extends BasicActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        setupListView();

        setupDrawerLayout();
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            FFBadApplication app = (FFBadApplication) getApplication();
            db = app.getDb();
        }
        return db;
    }



    private void setupListView() {

        String[] projection = {
                FFBadDbContract.Shuttlecock._ID,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_BRAND,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_REFERENCE,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_STOCK,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_RATING
        };

        Cursor cursor = getDb().query(
                FFBadDbContract.Shuttlecock.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        ShuttlecockCursorAdapter cursorAdapter = new ShuttlecockCursorAdapter(this, cursor);

        ListView list = (ListView) findViewById(R.id.listview);
        list.setAdapter(cursorAdapter);

    }

        /*String[] projection = {
                FFBadDbContract.Shuttlecock._ID,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_BRAND,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_REFERENCE,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_STOCK
        };

        Cursor cursor = getDb().query(
                FFBadDbContract.Shuttlecock.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        String[] from = {
                FFBadDbContract.Shuttlecock.COLUMN_NAME_BRAND,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_REFERENCE,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_STOCK
        };

        int[] to = {
                R.id.brand,
                R.id.reference,
                R.id.stock
        };
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.item_shuttlecock, cursor, from, to, 0 );

        ListView list = (ListView) findViewById(R.id.listview);

        list.setAdapter(cursorAdapter);
    }*/

}
