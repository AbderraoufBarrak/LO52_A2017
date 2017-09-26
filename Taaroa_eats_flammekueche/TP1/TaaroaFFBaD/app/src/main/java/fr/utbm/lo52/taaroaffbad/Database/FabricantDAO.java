package fr.utbm.lo52.taaroaffbad.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import fr.utbm.lo52.taaroaffbad.Beans.Fabricant;

import java.util.ArrayList;

/**
 * Created by Jordan on 26/09/2017.
 */


public class FabricantDAO extends BaseDAO {

    public FabricantDAO(Context pContext) {
        super(pContext);
    }

    // drop table FABRICANT
    public static final String DROP_FABRICANT =  "DROP TABLE IF EXISTS " + handler.TABLE_FABRICANT + ";";

    // insert into table FABRICANT
    public void addFabricant(Fabricant f) {
        ContentValues values = new ContentValues();
        values.put(handler.FAB_ID, f.getId());
        values.put(handler.FAB_NOM, f.getNom());
        values.put(handler.FAB_ADR, f.getAdresse());
        values.put(handler.FAB_CONTACT, f.getContact());
        values.put(handler.FAB_TEL, f.getTel());
        values.put(handler.FAB_mail, f.getMail());

        sqlite.insert(handler.TABLE_FABRICANT, null, values);
    }

    /*public void supprimer(long id) {
        // CODE
    }*/

    /*public void modifier(Volant m) {
        // CODE
    }*/

    public Fabricant getFabricant(long id) {
        // CODE
        Fabricant f = new Fabricant();
        return f;
    }

}
