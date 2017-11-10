package utbm.fr.ffbad.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import utbm.fr.ffbad.R;

/**
 * Created by heros on 10/11/2017.
 */

public class DbUtil {

    private SQLiteDatabase db;
    private Context context;

    public DbUtil(SQLiteDatabase db, Context context){
        this.db = db;
        this.context = context;
    }

    public List<SQLException> executeScript(int resId){
        InputStream is = context.getResources().openRawResource(resId);
        ArrayList<SQLException> errors = new ArrayList<>();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bfr = new BufferedReader(isr);
        String query = new String();
        String line;
        try {
            while((line = bfr.readLine()) != null){
                query+=line+"\n";
            }
            String[] sqlStatements;
            sqlStatements= query.split(";");
            for (String statement : sqlStatements) {
                try {
                    db.execSQL(statement);
                }
                catch (SQLException e){
                    errors.add(e);
                    Log.e("Erreur sql",e.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errors;
    }

    public List<String> getTableNames(){
        ArrayList<String> arrTblNames = new ArrayList<String>();
        Cursor cu = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        if (cu.moveToFirst()) {
            while ( !cu.isAfterLast() ) {
                arrTblNames.add( cu.getString( cu.getColumnIndex("name")) );
                cu.moveToNext();
            }
        }
        return arrTblNames;
    }
}
