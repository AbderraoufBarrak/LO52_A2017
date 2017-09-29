package fr.utbm.lpp.ffbad;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import fr.utbm.lpp.ffbad.data.db.FFBadDbContract;
import fr.utbm.lpp.ffbad.data.db.FFbadDbHelper;

public class Stock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        FFbadDbHelper helper = new FFbadDbHelper(this);

        SQLiteDatabase db = helper.getReadableDatabase();

        String[] projection = {
                FFBadDbContract.Shuttlecock._ID,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_BRAND,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_REFERENCE,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_STOCK
        };

        Cursor cursor = db.query(
                FFBadDbContract.Shuttlecock.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        String[] projection2 = {
                FFBadDbContract.Shuttlecock.COLUMN_NAME_BRAND,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_REFERENCE,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_STOCK
        };

        int[] to = {
                R.id.brand,
                R.id.reference,
                R.id.stock
        };
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.item_shuttlecock, cursor, projection2, to, 0 );

        ListView list = (ListView) findViewById(R.id.listview);

        list.setAdapter(cursorAdapter);
    }
}
