package fr.utbm.lo52.taaroaffbad.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import fr.utbm.lo52.taaroaffbad.Beans.Acheteur;

/**
 * Created by Jordan on 26/09/2017.
 */


public class AcheteurDAO extends BaseDAO {

    public AcheteurDAO(Context pContext) {
        super(pContext);
    }

    // drop table ACHETEUR
    public static final String DROP_ACHETEUR =  "DROP TABLE IF EXISTS " + handler.TABLE_ACHETEUR + ";";

    // insert into table FABRICANT
    public long addAcheteur(Acheteur a) {
        ContentValues values = new ContentValues();
        values.putNull(handler.ACH_ID);
        values.put(handler.ACH_NOM, a.getNom());
        values.put(handler.ACH_PRENOM, a.getPrenom());
        values.put(handler.ACH_ADR, a.getAdresse());
        values.put(handler.ACH_TEL, a.getTel());
        values.put(handler.ACH_TYPE, a.getType());

        return sqlite.insert(handler.TABLE_ACHETEUR, null, values);
    }

    /*public void supprimer(long id) {
        // CODE
    }*/

    public Acheteur getAcheteur(long id) {

        Log.i("JOJO-AchID",id+"");
        Acheteur a = null;
        Cursor c = sqlite.rawQuery("select * from " + handler.TABLE_ACHETEUR +
                " where " + handler.ACH_ID + "=" + id, new String[] {});
        while (c.moveToNext()) {
            a = new Acheteur(c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getString(4),
                    c.getString(5));
        }
        c.close();
        return a;
    }

}
