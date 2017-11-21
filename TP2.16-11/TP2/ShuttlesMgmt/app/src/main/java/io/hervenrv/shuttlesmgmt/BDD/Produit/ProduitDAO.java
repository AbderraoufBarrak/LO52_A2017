package io.hervenrv.shuttlesmgmt.BDD.Produit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Utilisateur on 17/11/2017.
 */

public class ProduitDAO extends DAOProduitBase {

    public ProduitDAO(Context context){
        super(context);
    }

    public void ajouter(Produit a){

        ContentValues value = new ContentValues();
        value.put(DBConst.PRODUIT_REF, a.getRef());
        value.put(DBConst.PRODUIT_MARQUE, a.getMarque());
        value.put(DBConst.PRODUIT_PRIX, a.getPrix());
        value.put(DBConst.PRODUIT_NOM, a.getNom());
        value.put(DBConst.PRODUIT_IMAGEID, a.getImageID());
        db.insert(DBConst.PRODUIT_TABLE_NAME, null, value);

    }

    public void supprimer(String ref){

        db.delete(DBConst.PRODUIT_TABLE_NAME, DBConst.PRODUIT_REF + " = ?", new String[]{ref});

    }

    public void modifier(Produit a){

        ContentValues value = new ContentValues();
        value.put(DBConst.PRODUIT_REF, a.getRef());
        value.put(DBConst.PRODUIT_MARQUE, a.getMarque());
        value.put(DBConst.PRODUIT_PRIX, a.getPrix());
        value.put(DBConst.PRODUIT_NOM, a.getNom());
        value.put(DBConst.PRODUIT_IMAGEID, a.getImageID());
        db.update(DBConst.PRODUIT_TABLE_NAME, value, DBConst.PRODUIT_REF + " = ?", new String[]{a.getRef()});

    }

    public Produit selectionner(String ref){

        Cursor c = db.rawQuery("select * from " + DBConst.PRODUIT_TABLE_NAME + " where ref = ?", new String[]{ref});

        Produit resultat = null;

        if(c.moveToFirst()) {
            String marque = c.getString(1);
            double prix = c.getDouble(2);
            String nom = c.getString(3);
            int imageID = c.getInt(4);
            resultat = new Produit(ref, marque, prix, nom, imageID);
        }

        c.close();

        return resultat;
    }


}
