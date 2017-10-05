package ovh.sene.androidnet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TubeDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_TUBENAME, MySQLiteHelper.COLUMN_TUBEPRICE, MySQLiteHelper.COLUMN_TUBERATE };

    public TubeDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    private long id;
    private String tubeName;
    private String tubePrice;
    private String tubeRate;

    public Tube createTube(String tubeName, String tubePrice, String tubeRate) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TUBENAME, tubeName);
        values.put(MySQLiteHelper.COLUMN_TUBEPRICE, tubePrice);
        values.put(MySQLiteHelper.COLUMN_TUBERATE, tubeRate);

        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Tube newTube = cursorToTube(cursor);
        cursor.close();
        return newTube;
    }

    public void deleteTube(Tube tube) {
        long id = tube.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Tube> getAllTube() {
        List<Tube> tubes = new ArrayList<Tube>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tube tube = cursorToTube(cursor);
            tubes.add(tube);
            cursor.moveToNext();
        }
        //assurez-vous de la fermeture du Tube
        cursor.close();
        return tubes;
    }

    private Tube cursorToTube(Cursor cursor) {
        Tube tube = new Tube();
        tube.setId(cursor.getLong(0));
        tube.setTubeName(cursor.getString(1));
        return tube;
    }
}
