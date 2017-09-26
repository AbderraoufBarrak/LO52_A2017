package fr.utbm.lo52.taaroaffbad.Database;

import android.content.ContentValues;
import android.content.Context;
import fr.utbm.lo52.taaroaffbad.Beans.Vente;

/**
 * Created by Jordan on 26/09/2017.
 */

public class VenteDAO extends BaseDAO {

    public VenteDAO(Context pContext) {
        super(pContext);
    }

    // drop table VENTE
    public static final String DROP_VENTE =  "DROP TABLE IF EXISTS " + handler.TABLE_VENTE + ";";

    // insert into table VENTE
    public void addVente(Vente v) {
        ContentValues values = new ContentValues();
        values.put(handler.VEN_ID, v.getVenteId());
        values.put(handler.VEN_VOL_MARQUE, v.getMarque());
        values.put(handler.VEN_VOL_REF, v.getReference());
        values.put(handler.VEN_FAB_ID, v.getFabricantId());
        values.put(handler.VEN_ACH_ID, v.getAcheteurId());
        values.put(handler.VEN_PRIX, v.getPrix());
        values.put(handler.VEN_PAYE, v.getPaye());
        values.put(handler.VEN_QUANTITE, v.getQuantite());
        values.put(handler.VEN_DATE_VENTE, v.getDateAchat().toString());
        values.put(handler.VEN_DATE_PAYE, v.getDatePaye().toString());

        sqlite.insert(handler.TABLE_VENTE, null, values);
    }

    /*public void supprimer(long id) {
        // CODE
    }*/

    /*public void modifier(Volant m) {
        // CODE
    }*/

}
