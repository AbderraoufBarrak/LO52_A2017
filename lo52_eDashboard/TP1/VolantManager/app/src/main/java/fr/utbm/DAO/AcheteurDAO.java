package fr.utbm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.utbm.entity.Acheteur;
import fr.utbm.entity.Volant;

/**
 * Created by Exige on 06/10/2017.
 */

public class AcheteurDAO extends DAOManager {

    public static final String MATRICULE = "matricule";
    public static final String NOM = "nom";
    public static final String PRENOM = "prénom";
    public static final String SOCIETE = "société";

    public static final String TABLE_NAME = "Acheteur";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    MATRICULE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOM + " TEXT, " +
                    PRENOM + " TEXT, " +
                    SOCIETE + " TEXT);";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    /**
     * Constructeur prenant en compte le contexte
     * @param pContext
     */
    public AcheteurDAO(Context pContext) {
        super(pContext);
    }

    /**
     * Permet de supprimer la table. Son utilisation implique un changement de version de la base de données
     */
    public void dropTable() {
        Log.d("eDBTEAM/AcheteurDAO", "Deleting " + TABLE_NAME + " table...");
        sqLiteDatabase.execSQL(TABLE_DROP);
    }

    /**
     * Permet de créer la table
     */
    public void createTable() {
        Log.d("eDBTEAM/AcheteurDAO", "Creating " + TABLE_NAME + " table...");
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
    public void addAcheteur(Acheteur a) {
        a.setMatricule(getMaxID(TABLE_NAME, MATRICULE) + 1);
        Log.d("eDBTEAM/AcheteurDAO", "addAcheteur -> " + a.toString());
        ContentValues cv = new ContentValues();
        cv.put(AcheteurDAO.NOM, a.getNom());
        cv.put(AcheteurDAO.PRENOM, a.getPrénom());
        cv.put(AcheteurDAO.SOCIETE, a.getSociété());
        sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }

    /**
     * Efface une entrée de la table en fonction de son ID
     * @param matricule
     */
    public void deleteAcheteur(long matricule) {
        sqLiteDatabase.delete(TABLE_NAME, MATRICULE + " = ?", new String[] {String.valueOf(matricule)});
    }

    /**
     * Récupère la liste de tous les acheteurs
     * @return
     */
    public List<Acheteur> getAcheteurs() {
        Cursor c =
                sqLiteDatabase.rawQuery(
                        "select " +
                                MATRICULE + ", " +
                                NOM + ", " +
                                PRENOM + ", " +
                                SOCIETE + ", " +
                                " from " +
                                TABLE_NAME, null);

        Acheteur acheteur = new Acheteur(0, null, null, null);
        List<Acheteur> acheteurs = new ArrayList<>();
        while (c.moveToNext()) {
            acheteur = new Acheteur(c.getLong(0), c.getString(1), c.getString(2), c.getString(3));
            acheteurs.add(acheteur);
            Log.d("eDBTEAM/AcheteurDAO", "getAcheteur -> " + acheteur);
        }
        c.close();
        return acheteurs;
    }
}
