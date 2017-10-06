package fr.utbm.lo52.taaroaffbad.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import fr.utbm.lo52.taaroaffbad.Beans.Vente;
import fr.utbm.lo52.taaroaffbad.Beans.Volant;

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

        Log.i("JOjo-addVente","vente.getFabID="+v.getFabricantId());

        ContentValues values = new ContentValues();
        values.putNull(handler.VEN_ID);
        values.put(handler.VEN_VOL_MARQUE, v.getMarque());
        values.put(handler.VEN_VOL_REF, v.getReference());
        values.put(handler.VEN_FAB_ID, v.getFabricantId());
        values.put(handler.VEN_ACH_ID, v.getAcheteurId());
        values.put(handler.VEN_PRIX, v.getPrix());
        values.put(handler.VEN_PAYE, v.getPaye());
        values.put(handler.VEN_QUANTITE, v.getQuantite());
        values.put(handler.VEN_DATE_VENTE, v.getDateAchat().toString());
        values.put(handler.VEN_DATE_PAYE, (v.getDatePaye() != null)?v.getDatePaye().toString(): null);

        sqlite.insert(handler.TABLE_VENTE, null, values);
    }

    public void setPaye(Vente v) {
        Date date = new Date();

        sqlite.execSQL("update "+handler.TABLE_VENTE+" set "+handler.VEN_PAYE+"=1, "+handler.VEN_DATE_PAYE+"='"+date.toString()+
                "' where "+handler.VEN_ID+"="+v.getVenteId());
    }

    public ArrayList<Vente> getVente () {
        Cursor c =
                sqlite.rawQuery(
                        "select * " +
                                //VolantDAO + ", " +
                                //REF + ", "
                                //+ CLASSEMENT +
                                " from " +
                                handler.TABLE_VENTE //+
                        //" where marque = ? and ref = ?"
                        , new String[] {/*marque, ref*/});


        Vente vente;
        ArrayList<Vente> venteList = new ArrayList<Vente>();
        while (c.moveToNext()) {
            Date dateAchat = new Date(c.getString(8));
            Date datePaye = (c.getString(9) != null)?new Date(c.getString(9)):null;
            vente = new Vente(  c.getInt(0)
                                ,c.getString(1)
                                ,c.getString(2)
                                ,c.getInt(3)
                                ,c.getInt(4)
                                ,c.getInt(5)
                                ,c.getInt(6)>0
                                ,c.getInt(7)
                                ,dateAchat
                                ,datePaye);
            venteList.add(vente);
            Log.d("YVAN-OUI", "getVente() -> " + vente.toString());
        }
        c.close();
        return venteList;
    }

}
