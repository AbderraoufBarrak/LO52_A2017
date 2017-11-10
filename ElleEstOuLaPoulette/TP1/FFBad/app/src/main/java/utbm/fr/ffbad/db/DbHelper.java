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
    private DbUtil dbUtil;

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.db = getReadableDatabase();
        dbUtil = new DbUtil(this.db, context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Cursor cu = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        if(cu.getCount()<=1) dbUtil.executeScript(R.raw.shuttlecock3);
    }

    public List<StockLine> getStock(){
        List<StockLine> stockLinesResult = new ArrayList<>();
        String query_str = "SELECT * FROM TUBE";
        Cursor c = this.db.rawQuery(query_str ,null);
        if(c.moveToFirst()){
            do{
                String ref = c.getString(0);
                String image = c.getString(4);
                StockLine line = new StockLine(new Volant(ref,"default"),new Tube(12,25,ref,image));
                stockLinesResult.add(line);
            }while(c.moveToNext());
            c.close();
        }
        return stockLinesResult;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db){

    }
}
