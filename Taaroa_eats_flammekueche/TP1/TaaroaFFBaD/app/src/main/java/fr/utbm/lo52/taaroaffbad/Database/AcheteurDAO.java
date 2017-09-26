package fr.utbm.lo52.taaroaffbad.Database;

import android.content.ContentValues;
import android.content.Context;
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
    public void addAcheteur(Acheteur a) {
        ContentValues values = new ContentValues();
        values.put(handler.ACH_ID, a.getId());
        values.put(handler.ACH_NOM, a.getNom());
        values.put(handler.ACH_TYPE, a.getType());

        sqlite.insert(handler.TABLE_ACHETEUR, null, values);
    }

    /*public void supprimer(long id) {
        // CODE
    }*/

    /*public void modifier(Volant m) {
        // CODE
    }*/

    public Acheteur getAcheteur(long id) {
        // CODE
        Acheteur a = new Acheteur();
        return a;
    }

}
