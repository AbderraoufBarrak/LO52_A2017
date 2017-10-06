package fr.utbm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.utbm.beans.LotInfo;
import fr.utbm.entity.LotVolant;

/**
 * Created by Exige on 06/10/2017.
 */

public class LotVolantDAO extends DAOManager {

    public static final String ID = "id";
    public static final String TAILLE = "taille";
    public static final String PRIX = "prix";

    public static final String TABLE_NAME = "LotVolant";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TAILLE + " INTEGER, " +
                    PRIX + " REAL);";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    /**
     * Constructeur prenant en compte le contexte
     * @param pContext
     */
    public LotVolantDAO(Context pContext) {
        super(pContext);
    }

    /**
     * Permet de supprimer la table. Son utilisation implique un changement de version de la base de données
     */
    public void dropTable() {
        Log.d("eDBTEAM/LotVolantDAO", "Deleting " + TABLE_NAME + " table...");
        sqLiteDatabase.execSQL(TABLE_DROP);
    }

    /**
     * Permet de créer la table
     */
    public void createTable() {
        Log.d("eDBTEAM/LotVolantDAO", "Creating " + TABLE_NAME + " table...");
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    /**
     * Efface le contenu de la table
     */
    public void eraseContent() {
        sqLiteDatabase.delete(TABLE_NAME, null, null);
    }

    /**
     * Ajoute un lot de volant en base de données
     * @param lot
     */
    public void addLotVolant(LotVolant lot) {
        lot.setId(getMaxID(TABLE_NAME, ID) + 1);
        Log.d("eDBTEAM/LotVolantDAO", "addLotVolant -> " + lot.toString());
        ContentValues cv = new ContentValues();
        cv.put(LotVolantDAO.TAILLE, lot.getTaille());
        cv.put(LotVolantDAO.PRIX, lot.getPrix());
        sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }

    /**
     * Efface une entrée de la table en fonction de son ID
     * @param id
     */
    public void deleteLotVolant(long id) {
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ?", new String[] {String.valueOf(id)});
    }


    /**
     * Met à jour les informations d'un lot de volant
     * @param lot
     */
    public void updateLotVolant (LotVolant lot) {
        ContentValues cv = new ContentValues();
        cv.put(TAILLE, lot.getTaille());
        cv.put(PRIX, lot.getPrix());
        sqLiteDatabase.update(TABLE_NAME, cv, ID + " = ?", new String[] {String.valueOf(lot.getId())});
    }


    /**
     * Récupère un lot de volant en fonction de ID
     * @param id
     * @return
     */
    public LotVolant getLotVolant (int id) {
        Cursor c =
                sqLiteDatabase.rawQuery(
                        "select * from " +
                                TABLE_NAME +
                                " where id = ?"
                        , new String[] {String.valueOf(id)});

        LotVolant lotVolant = new LotVolant(0, 0, 0);

        // Affichage des résultats répondants à la requête
        while (c.moveToNext()) {
            lotVolant = new LotVolant(c.getLong(0), c.getInt(1), c.getFloat(2));
            Log.d("eDBTEAM/LotVolantDAO", "getLotVolant(" + id + ") -> " + lotVolant);
        }
        c.close();
        return lotVolant;
    }

    public List<LotVolant> getLotsVolant() {
        Cursor c =
                sqLiteDatabase.rawQuery(
                        "select " +
                                ID + ", " +
                                TAILLE + ", " +
                                PRIX +
                                " from " +
                                TABLE_NAME, null);

        LotVolant lotVolant = new LotVolant(0, 0, 0);
        List<LotVolant> lotsVolants = new ArrayList<>();
        while (c.moveToNext()) {
            lotVolant = new LotVolant(c.getLong(0), c.getInt(1), c.getFloat(2));
            lotsVolants.add(lotVolant);
            Log.d("eDBTEAM/LotVolantDAO", "getLotsVolant -> " + lotVolant);
        }
        c.close();
        return lotsVolants;
    }

    /**
     * Récupère la liste de tous les lots de volants
     * @param vDAO
     * @return
     */
    public List<LotInfo> getTestVolants(VolantsDAO vDAO) {
        Cursor c =
                sqLiteDatabase.rawQuery(
                        "select l." +
                                ID + ", v." +
                                vDAO.MARQUE + ", v." +
                                vDAO.REF + ", v." +
                                vDAO.CLASSEMENT + ", l." +
                                TAILLE + ", l." +
                                PRIX +
                                " from " +
                                TABLE_NAME + " l inner join " + vDAO.TABLE_NAME + " v on l." + ID + "=v." + vDAO.LOT_ID, null);

        LotInfo vi = new LotInfo(0, null, null, null, 0, 0);
        List<LotInfo> vis = new ArrayList<>();
        while (c.moveToNext()) {
            vi = new LotInfo(c.getLong(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), c.getFloat(5));
            vis.add(vi);
            Log.d("eDBTEAM/LotVolantDAO", "getTestVolants -> " + vi);
        }
        c.close();
        return vis;
    }
}
