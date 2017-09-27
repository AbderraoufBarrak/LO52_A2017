package fr.utbm.lo52.taaroaffbad.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import fr.utbm.lo52.taaroaffbad.Beans.Volant;

/**
 * Created by Jordan on 25/09/2017.
 */

public class VolantDAO extends BaseDAO {

    public VolantDAO(Context pContext) {
        super(pContext);
    }

    // drop table VOLANT
    public static final String DROP_VOLANT =  "DROP TABLE IF EXISTS " + handler.TABLE_VOLANT + ";";

    // insert into table VOLANT
    public void addVolant(Volant v) {
        ContentValues values = new ContentValues();
        values.put(handler.VOL_VAL1, v.getValidite_1());
        values.put(handler.VOL_VAL2, v.getValidite_2());
        values.put(handler.VOL_MARQUE, v.getMarque());
        values.put(handler.VOL_REF, v.getReference());
        values.put(handler.VOL_CLASSEMENT, v.getClassement());
        values.put(handler.VOL_PRIX, v.getPrix());

        Log.i("JOJO-addVolant","avant INSERT");
        sqlite.insert(handler.TABLE_VOLANT, null, values);
        Log.i("JOJO-addVolant","après INSERT");
    }

    /*public void supprimer(long id) {
        // CODE
    }*/

    /*public void modifier(Volant m) {
        // CODE
    }*/

    public ArrayList<Volant> getVolant () {
        Cursor c =
                sqlite.rawQuery(
                        "select * " +
                                //VolantDAO + ", " +
                                //REF + ", "
                                //+ CLASSEMENT +
                                " from " +
                                handler.TABLE_VOLANT //+
                                //" where marque = ? and ref = ?"
                        , new String[] {/*marque, ref*/});

        Volant volant;
        ArrayList<Volant> volantList = new ArrayList<Volant>();
        while (c.moveToNext()) {
            volant = new Volant(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), c.getInt(5));
            volantList.add(volant);
            Log.d("YVAN", "getVolant() -> " + volant.toString());
        }
        c.close();
        return volantList;
    }



}
