package fr.utbm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.utbm.entity.Acheter;
import fr.utbm.entity.Date;

/**
 * Created by Exige on 06/10/2017.
 */

public class AcheterDAO extends DAOManager {

    public static final String ID = "id";
    public static final String LOT_ID = "lot_id";
    public static final String DATE_ID = "date_id";
    public static final String ACHETEUR_ID = "acheteur_id";
    public static final String PAYED = "payed";

    public static final String TABLE_NAME = "Acheter";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LOT_ID + " INTEGER, " +
                    DATE_ID + " INTEGER, " +
                    ACHETEUR_ID + " INTEGER, " +
                    PAYED + " INTEGER);";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    /**
     * Constructeur prenant en compte le contexte
     * @param pContext
     */
    public AcheterDAO(Context pContext) {
        super(pContext);
    }

    /**
     * Permet de supprimer la table. Son utilisation implique un changement de version de la base de données
     */
    public void dropTable() {
        Log.d("eDBTEAM/AcheterDAO", "Deleting " + TABLE_NAME + " table...");
        sqLiteDatabase.execSQL(TABLE_DROP);
    }

    /**
     * Permet de créer la table
     */
    public void createTable() {
        Log.d("eDBTEAM/AcheterDAO", "Creating " + TABLE_NAME + " table...");
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    /**
     * Efface le contenu de la table
     */
    public void eraseContent() {
        sqLiteDatabase.delete(TABLE_NAME, null, null);
    }

    /**
     * Ajoute un acheteur en base de données
     */
    public void addAcheter(Acheter a) {
        a.setId(getMaxID(TABLE_NAME, ID) + 1);
        Log.d("eDBTEAM/AcheterDAO", "addAcheter -> " + a.toString());
        ContentValues cv = new ContentValues();
        cv.put(AcheterDAO.LOT_ID, a.getLot_id());
        cv.put(AcheterDAO.DATE_ID, a.getDate_id());
        cv.put(AcheterDAO.ACHETEUR_ID, a.getAcheteur_matricule());
        cv.put(AcheterDAO.PAYED, a.isPayed());
        sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }

    /**
     * Efface une entrée de la table en fonction de son ID
     * @param id
     */
    public void deleteAcheter(long id) {
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ?", new String[] {String.valueOf(id)});
    }

    /**
     * Récupère la liste de tous les achats
     * @return
     */
    public List<Acheter> getAchats() {
        Cursor c =
                sqLiteDatabase.rawQuery(
                        "select " +
                                ID + ", " +
                                LOT_ID + ", " +
                                DATE_ID + ", " +
                                ACHETEUR_ID + ", " +
                                PAYED +
                                " from " +
                                TABLE_NAME, null);

        Acheter achat = new Acheter (0, 0, 0, 0, false);
        List<Acheter> achats = new ArrayList<>();
        while (c.moveToNext()) {
            Boolean currentPayed = (c.getInt(4) != 0);
            achat = new Acheter(c.getLong(0), c.getLong(1), c.getLong(2), c.getLong(3), currentPayed);
            achats.add(achat);
            Log.d("eDBTEAM/AcheterDAO", "getAchats -> " + achat);
        }
        c.close();
        return achats;
    }
}
