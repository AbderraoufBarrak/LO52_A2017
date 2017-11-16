package utbm.fr.ffbad.db;

import android.content.Context;
import android.database.Cursor;
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
import utbm.fr.ffbad.entity.PurchaseLine;
import utbm.fr.ffbad.entity.StockLine;
import utbm.fr.ffbad.entity.Tube;
import utbm.fr.ffbad.entity.Volant;

/**
 * Created by Clément on 22/09/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SHUTTLECOCK";

    private SQLiteDatabase db;
    private Context context;

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        this.db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Cursor cu = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        if(cu.getCount()<=1)
        {
            DbUtil.executeScript(db,this.context,R.raw.shuttlecock);
        }
    }

    public List<StockLine> getStock(){
        List<StockLine> stockLinesResult = new ArrayList<>();
        String query_str = "SELECT * FROM TUBE";
        Cursor c = this.db.rawQuery(query_str ,null);
        if(c.moveToFirst()){
            do{
                String ref = c.getString(0);
                Integer cost = Integer.parseInt(c.getString(2));
                Integer nbStock = Integer.parseInt(c.getString(3));
                String image = c.getString(4);

                //Retrieve the brand
                String brandQuery_str = "SELECT MARQUE FROM VOLANT WHERE REF = '" + ref +"'";
                Cursor brandC = this.db.rawQuery(brandQuery_str ,null);
                String brand = "default";
                if(brandC.moveToNext()){
                   brand = brandC.getString(0);
                }
                StockLine line = new StockLine(new Volant(ref,brand),new Tube(nbStock,cost,ref,image));
                stockLinesResult.add(line);
            }while(c.moveToNext());
            c.close();
        }
        return stockLinesResult;
    }

    public List<PurchaseLine> getPurchases(){
        List<PurchaseLine> purchaseLines = new ArrayList<>();
        /*String query_str = "SELECT ";
        Cursor c = this.db.rawQuery(query_str ,null);
        if(c.moveToFirst()){
            do{
                String buyerName = "Michel";
                Double price = 0.0;
                Integer qty = 2;
                String ref = "D6r27-H59p";
                boolean paid = false;
                PurchaseLine line = new PurchaseLine(buyerName,price,qty,ref,paid);
                purchaseLines.add(line);
            }while(c.moveToNext());
            c.close();
        }*/
        for(int i=0; i<10;i++){
            PurchaseLine line = new PurchaseLine("Michel",42.03,12,"D6r27-H59p",i%2 == 0);
            purchaseLines.add(line);
        }
        return purchaseLines;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db){

    }
}
