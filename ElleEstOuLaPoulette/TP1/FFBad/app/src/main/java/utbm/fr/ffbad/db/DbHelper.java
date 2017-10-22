package utbm.fr.ffbad.db;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import utbm.fr.ffbad.R;
import utbm.fr.ffbad.entity.StockLine;
import utbm.fr.ffbad.entity.Tube;
import utbm.fr.ffbad.entity.Volant;

/**
 * Created by Clément on 22/09/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SHUTTLECOCK";

    private SQLiteDatabase db;

    Context context;
    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        this.db = getReadableDatabase();
    }

   /* public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        this.context = context;

    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {

        InputStream is = this.context.getResources().openRawResource(R.raw.shuttlecock2);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bfr = new BufferedReader(isr);
        String query = "";
        String line;
        try {
            while((line = bfr.readLine()) != null){
                query+=line+"\n";
            }
            System.out.println(query);
            db.execSQL(query);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e){
            Log.e("Erreur sql",e.toString());
        }

    }

    public List<StockLine> getStock(){

        List<StockLine> stockLinesResult = new ArrayList<>();

        String query_str = "SELECT * FROM TUBE";

        //TEST
        ArrayList<String> arrTblNames = new ArrayList<String>();
        Cursor cu = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (cu.moveToFirst()) {
            while ( !cu.isAfterLast() ) {
                arrTblNames.add( cu.getString( cu.getColumnIndex("name")) );
                cu.moveToNext();
            }
        }
        Log.d("TABLES", arrTblNames.toString());
        //TEST

        Cursor c = this.db.rawQuery(query_str ,null);
        c.moveToFirst();
        List<String> results= new ArrayList<String>();
        int totalPO = c.getCount();
        do
        {
            String ref = c.getString(0);

            StockLine line = new StockLine(new Volant(ref,"default"),new Tube(12,25,ref,""));

            c.moveToNext();
            totalPO = totalPO -1;
        }while(c.moveToNext());
        c.close();

        return stockLinesResult;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db){

    }
}
