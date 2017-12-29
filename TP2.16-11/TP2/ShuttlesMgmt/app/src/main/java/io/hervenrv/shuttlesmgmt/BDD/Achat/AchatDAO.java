package io.hervenrv.shuttlesmgmt.BDD.Achat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Utilisateur on 17/11/2017.
 */

public class AchatDAO extends DAOAchatBase {

    public AchatDAO(Context context){
        super(context);
    }

    public void ajouter(Achat a){

        ContentValues value = new ContentValues();

        value.put(DBConst.ACHAT_ACHETEUR, a.getAcheteur());
        value.put(DBConst.ACHAT_REF, a.getRef());
        value.put(DBConst.ACHAT_QUANTITE, a.getQuantite());
        value.put(DBConst.ACHAT_PAYE, a.getPaye());
        value.put(DBConst.ACHAT_DATE, a.getDate());

        mDb.insert(DBConst.ACHAT_TABLE_NAME, null, value);

    }

    public void supprimer(long id){

        mDb.delete(DBConst.ACHAT_TABLE_NAME, DBConst.ACHAT_KEY + " = ?", new String[]{String.valueOf(id)});

    }

    public void modifier(Achat a){
        ContentValues value = new ContentValues();
        value.put(DBConst.ACHAT_ACHETEUR, a.getAcheteur());
        value.put(DBConst.ACHAT_REF, a.getRef());
        value.put(DBConst.ACHAT_QUANTITE, a.getQuantite());
        value.put(DBConst.ACHAT_PAYE, a.getPaye());
        value.put(DBConst.ACHAT_DATE, a.getDate());
        mDb.update(DBConst.ACHAT_TABLE_NAME, value, DBConst.ACHAT_KEY + " = ?", new String[]{String.valueOf(a.getId())});
    }

    public Achat selectionner(long id){

        Cursor c = mDb.rawQuery("select * from " + DBConst.ACHAT_TABLE_NAME + " where id = ?", new String[]{String.valueOf(id)});

        Achat resultat = null;

        if(c.moveToFirst()) {
            String acheteur = c.getString(1);
            String ref = c.getString(2);
            int quantite = c.getInt(3);
            boolean paye =  c.getInt(4) != 0;
            String date = c.getString(5);

            resultat = new Achat(id, acheteur, ref, quantite, paye, date);
        }

        c.close();

        return resultat;
    }

    public ArrayList<Achat> getList(){

        ArrayList<Achat> result = new ArrayList<>();

        Cursor c = mDb.rawQuery("select * from " + DBConst.ACHAT_TABLE_NAME, null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {

                long id = c.getLong(0);
                String acheteur = c.getString(1);
                String ref = c.getString(2);
                int quantite = c.getInt(3);
                boolean paye =  c.getInt(4) != 0;
                String date = c.getString(5);

                Achat elem = new Achat(id, acheteur, ref, quantite, paye, date);

                result.add(elem);
                c.moveToNext();
            }
        }

        c.close();

        return result;


    }


}
