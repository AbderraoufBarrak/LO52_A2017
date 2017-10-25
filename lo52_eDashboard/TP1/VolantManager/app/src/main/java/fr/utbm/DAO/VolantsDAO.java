package fr.utbm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.utbm.entity.Volant;

public class VolantsDAO extends DAOManager {

    public static final String TABLE_NAME = "Volant";
    public static final String ID = "id";
    public static final String MARQUE = "marque";
    public static final String REF = "ref";
    public static final String CLASSEMENT = "classement";
    public static final String LOT_ID = "lotId";

    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MARQUE + " TEXT, " +
                    REF + " TEXT, " +
                    CLASSEMENT + " INTEGER, " +
                    LOT_ID + " INTEGER, " +
                    "FOREIGN KEY(" + LOT_ID + ") REFERENCES " + LotVolantDAO.TABLE_NAME + "(" + LotVolantDAO.ID + "));";

    public static final String TABLE_DROP =
            "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    /**
     * Constructeur prenant en compte le contexte
     * @param pContext
     */
    public VolantsDAO(Context pContext) {
        super(pContext);
    }

    /**
     * Permet de supprimer la table. Son utilisation implique un changement de version de la base de données
     */
    public void dropTable() {
        Log.d("eDBTEAM/VolantsDAO", "Deleting " + TABLE_NAME + " table...");
        sqLiteDatabase.execSQL(TABLE_DROP);
    }

    /**
     * Permet de créer la table
     */
    public void createTable() {
        Log.d("eDBTEAM/VolantsDAO", "Creating " + TABLE_NAME + " table...");
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    /**
     * Efface le contenu de la table
     */
    public void eraseContent() {
        sqLiteDatabase.delete(TABLE_NAME, null, null);
        Log.d("eDBTEAM/VolantsDAO","eraseAllVolant");
    }

    /**
     * Ajoute un volant en base de données
     * @param v
     */
    public void addVolant (Volant v) {
        v.setId(getMaxID(TABLE_NAME, ID) + 1);
        Log.d("eDBTEAM/VolantsDAO", "addVolant -> " + v.toString());
        ContentValues cv = new ContentValues();
        cv.put(VolantsDAO.MARQUE, v.getMarque());
        cv.put(VolantsDAO.REF, v.getRef());
        cv.put(VolantsDAO.CLASSEMENT, v.getClassement());
        cv.put(VolantsDAO.LOT_ID, v.getLotId());
        sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }

    /**
     * Efface une entrée de la table en fonction de son ID
     * @param id
     */
    public void deleteVolant(long id) {
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ?", new String[] {String.valueOf(id)});
    }


    /**
     * Met à jour les informations d'un volant
     * @param v
     */
    public void updateClassementVolant (Volant v) {
        ContentValues cv = new ContentValues();
        cv.put(CLASSEMENT, v.getClassement());
        sqLiteDatabase.update(TABLE_NAME, cv, ID + " = ?", new String[] {String.valueOf(v.getId())});
    }


    /**
     * Récupère l'ID du lot attaché à un volant
     * @param marque
     * @param ref
     * @return
     */
    public long getLotVolantID (String marque, String ref) {
        Cursor c =
                sqLiteDatabase.rawQuery(
                        "select " +
                                LOT_ID +
                        " from " +
                                TABLE_NAME +
                        " where marque = ? and ref = ?"
                , new String[] {marque, ref});

        //Volant volant = new Volant(0, null, null, null, 0);
        long id = 0;
        // Affichage des résultats répondants à la requête
        while (c.moveToNext()) {
            //volant = new Volant(c.getLong(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4));
            id = c.getLong(0);
            //Log.d("eDBTEAM/VolantsDAO", "getVolant(" + marque + ", " + ref + ") -> " + volant);
            Log.d("eDBTEAM/VolantsDAO", "getVolant(" + marque + ", " + ref + ") -> " + id);
        }
        c.close();
        return id;
    }

    /**
     * Récupère un volant en fonction de sa marque et de sa référence
     * @param marque
     * @param ref
     * @return
     */
    public Volant getVolant (String marque, String ref) {
        Cursor c =
                sqLiteDatabase.rawQuery(
                        "select " +
                                ID + ", " +
                                MARQUE + ", " +
                                REF + ", " +
                                CLASSEMENT + ", " +
                                LOT_ID +
                                " from " +
                                TABLE_NAME +
                                " where marque = ? and ref = ?"
                        , new String[] {marque, ref});

        Volant volant = new Volant(0, null, null, null, 0);
        // Affichage des résultats répondants à la requête
        while (c.moveToNext()) {
            volant = new Volant(c.getLong(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4));
            Log.d("eDBTEAM/VolantsDAO", "getVolant(" + marque + ", " + ref + ") -> " + volant);
        }
        c.close();
        return volant;
    }

    /**
     * Récupère la liste de tous les volants
     * @return
     */
    public List<Volant> getVolants() {
        Cursor c =
                sqLiteDatabase.rawQuery(
                        "select " +
                                ID + ", " +
                                MARQUE + ", " +
                                REF + ", " +
                                CLASSEMENT + ", " +
                                LOT_ID +
                                " from " +
                                TABLE_NAME, null);

        Volant volant = new Volant(0, null, null, null, 0);
        List<Volant> volants = new ArrayList<>();
        while (c.moveToNext()) {
            volant = new Volant(c.getLong(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4));
            volants.add(volant);
            Log.d("eDBTEAM/VolantsDAO", "getVolants -> " + volant);
        }
        c.close();
        return volants;
    }


    public boolean isEmpty(){
        Cursor c =
                sqLiteDatabase.rawQuery("select count ( " + ID + ") from " + TABLE_NAME, null);
        if (c.moveToFirst()){
            return c.getInt(0) <= 0;
        }
        // en cas de non accès au curseur on retourne false
        else
        {
            return false;
        }
    }

}
