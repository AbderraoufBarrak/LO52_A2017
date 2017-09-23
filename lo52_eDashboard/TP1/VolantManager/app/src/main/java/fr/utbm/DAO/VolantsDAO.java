package fr.utbm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import fr.utbm.entity.Volants;

/**
 * Created by Exige on 23/09/2017.
 */

public class VolantsDAO extends DAOManager {

    public static final String TABLE_NAME = "volants";
    public static final String ID = "id";
    public static final String MARQUE = "marque";
    public static final String REF = "ref";
    public static final String CLASSEMENT = "classement";

    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MARQUE + " TEXT, " +
                    REF + " TEXT, " +
                    CLASSEMENT + " INTEGER);";

    public static final String TABLE_DROP =
            "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public VolantsDAO(Context pContext) {
        super(pContext);
    }


    public void addVolant (Volants v) {
        Log.d("eDBTEAM/VolantsDAO", "addVolant -> " + v.toString());
        ContentValues cv = new ContentValues();
        cv.put(VolantsDAO.MARQUE, v.getMarque());
        cv.put(VolantsDAO.REF, v.getRef());
        cv.put(VolantsDAO.CLASSEMENT, v.getClassement());
        sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }


    public void deleteVolant(long id) {
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ?", new String[] {String.valueOf(id)});
    }


    public void updateClassementVolant (Volants v) {
        ContentValues cv = new ContentValues();
        cv.put(CLASSEMENT, v.getClassement());
        sqLiteDatabase.update(TABLE_NAME, cv, ID + " = ?", new String[] {String.valueOf(v.getId())});
    }


    public Volants getVolant (String marque, String ref) {
        Cursor c =
                sqLiteDatabase.rawQuery(
                        "select " +
                                MARQUE + ", " +
                                REF + ", "
                                + CLASSEMENT +
                        " from " +
                                TABLE_NAME +
                        " where marque = ? and ref = ?"
                , new String[] {marque, ref});
        Volants volant = new Volants(0, null, null, null);
        while (c.moveToNext()) {
            volant = new Volants(c.getString(0), c.getString(1), c.getString(2));
            Log.d("eDBTEAM/VolantsDAO", "getVolant(" + marque + ", " + ref + ") -> " + volant);
        }
        c.close();
        return volant;
    }
}
