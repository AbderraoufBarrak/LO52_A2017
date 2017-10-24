package fr.utbm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.utbm.entity.Acheteur;
import fr.utbm.entity.Date;

public class DateDAO extends DAOManager {

    public static final String ID = "id";
    public static final String DATE = "date";

    public static final String TABLE_NAME = "Date";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DATE + " TEXT);";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    /**
     * Constructeur prenant en compte le contexte
     * @param pContext
     */
    public DateDAO(Context pContext) {
        super(pContext);
    }

    /**
     * Permet de supprimer la table. Son utilisation implique un changement de version de la base de données
     */
    public void dropTable() {
        Log.d("eDBTEAM/DateDAO", "Deleting " + TABLE_NAME + " table...");
        sqLiteDatabase.execSQL(TABLE_DROP);
    }

    /**
     * Permet de créer la table
     */
    public void createTable() {
        Log.d("eDBTEAM/DateDAO", "Creating " + TABLE_NAME + " table...");
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
    public void addDate(Date d) {
        d.setId(getMaxID(TABLE_NAME, ID) + 1);
        Log.d("eDBTEAM/DateDAO", "addDate -> " + d.toString());
        ContentValues cv = new ContentValues();
        cv.put(DateDAO.DATE, d.getDate().toString());
        sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }

    /**
     * Efface une entrée de la table en fonction de son ID
     * @param id
     */
    public void deleteDate(long id) {
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ?", new String[] {String.valueOf(id)});
    }

    /**
     * Récupère la liste de toutes les dates
     * @return
     */
    public List<Date> getDates() {
        Cursor c =
                sqLiteDatabase.rawQuery(
                        "select " +
                                ID + ", " +
                                DATE +
                                " from " +
                                TABLE_NAME, null);

        Date date = new Date(0, null);
        List<Date> dates = new ArrayList<>();
        while (c.moveToNext()) {
            date = new Date(c.getLong(0), new java.util.Date(c.getString(1)));
            dates.add(date);
            Log.d("eDBTEAM/DateDAO", "getDates -> " + date);
        }
        c.close();
        return dates;
    }
}
