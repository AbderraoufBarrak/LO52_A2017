package fr.utbm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.utbm.beans.AchatInfo;
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
    public static final String QUANTITE = "quantité";
    public static final String PAYED = "payed";

    public static final String TABLE_NAME = "Acheter";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LOT_ID + " INTEGER, " +
                    DATE_ID + " INTEGER, " +
                    ACHETEUR_ID + " INTEGER, " +
                    QUANTITE + " INTEGER, " +
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
        cv.put(AcheterDAO.QUANTITE, a.getQuantité());
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
                                QUANTITE + ", " +
                                PAYED +
                                " from " +
                                TABLE_NAME, null);

        Acheter achat = new Acheter (0, 0, 0, 0, 0, false);
        List<Acheter> achats = new ArrayList<>();
        while (c.moveToNext()) {
            Boolean currentPayed = (c.getInt(5) != 0);
            achat = new Acheter(c.getLong(0), c.getLong(1), c.getLong(2), c.getLong(3), c.getInt(4), currentPayed);
            achats.add(achat);
            Log.d("eDBTEAM/AcheterDAO", "getAchats -> " + achat);
        }
        c.close();
        return achats;
    }

    /**
     * Récupère la liste complète des informations concernant une transaction
     */
    public List<AchatInfo> getAchatsInfos(VolantsDAO vDAO, LotVolantDAO lotVolantDAO, DateDAO dateDAO, AcheteurDAO acheteurDAO) {
        Cursor c =
                sqLiteDatabase.rawQuery(
                        "select acher." +
                                ID + ", acher." +
                                LOT_ID + ", acher." +
                                DATE_ID + ", acher." +
                                ACHETEUR_ID + ", acher." +
                                QUANTITE + ", acher." +
                                PAYED + ", lot." +
                                lotVolantDAO.TAILLE + ", lot." +
                                lotVolantDAO.PRIX + ", dat." +
                                dateDAO.DATE + ", acheur." +
                                acheteurDAO.NOM + ", acheur." +
                                acheteurDAO.PRENOM + ", acheur." +
                                acheteurDAO.SOCIETE + ", vol." +
                                vDAO.REF + ", vol." +
                                vDAO.MARQUE + ", vol." +
                                vDAO.CLASSEMENT +
                                " from " +
                                lotVolantDAO.TABLE_NAME + " lot inner join " + TABLE_NAME + " acher on lot." +  lotVolantDAO.ID + "=acher." + LOT_ID + " inner join " + acheteurDAO.TABLE_NAME + " acheur on acher." + ACHETEUR_ID + "=acheur." + acheteurDAO.MATRICULE + " inner join " + dateDAO.TABLE_NAME + " dat on acher." + DATE_ID + "=dat." + dateDAO.ID + " inner join " + vDAO.TABLE_NAME + " vol on lot." + lotVolantDAO.ID + "=vol." + vDAO.ID, null);

        AchatInfo achat = new AchatInfo(0, false, 0, 0, 0, 0, null, null, null, null, 0, 0, null, null, null);
        List<AchatInfo> achats = new ArrayList<>();
        while (c.moveToNext()) {
            Boolean currentPayed = (c.getInt(5) != 0);
            achat = new AchatInfo(c.getLong(0), currentPayed, c.getInt(4), c.getLong(1), c.getLong(3), c.getLong(2), new java.util.Date(c.getString(8)), c.getString(9), c.getString(10), c.getString(11), c.getInt(6), c.getFloat(7), c.getString(12), c.getString(13), c.getString(14));
            achats.add(achat);
            Log.d("eDBTEAM/AcheterDAO", "getAchatsInfos -> " + achat);
        }
        c.close();
        return achats;
    }
}
