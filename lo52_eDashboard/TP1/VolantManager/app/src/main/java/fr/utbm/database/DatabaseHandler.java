package fr.utbm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import fr.utbm.DAO.LotVolantDAO;
import fr.utbm.DAO.VolantsDAO;
import fr.utbm.entity.LotVolant;
import fr.utbm.entity.Volant;

/**
 * Created by Exige on 23/09/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * --------- Table Volant ---------
     */

    public static final String VOLANT_ID = "id";
    public static final String VOLANT_MARQUE = "marque";
    public static final String VOLANT_REF = "ref";
    public static final String VOLANT_CLASSEMENT = "classement";
    public static final String VOLANT_LOT_ID = "lotId";

    public static final String VOLANTS_TABLE_NAME = "Volant";
    public static final String VOLANTS_TABLE_CREATE =
            "CREATE TABLE " + VOLANTS_TABLE_NAME + " (" +
                    VOLANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    VOLANT_MARQUE + " TEXT, " +
                    VOLANT_REF + " TEXT, " +
                    VOLANT_CLASSEMENT + " INTEGER, " +
                    VOLANT_LOT_ID + " INTEGER);";

    public static final String VOLANTS_TABLE_DROP = "DROP TABLE IF EXISTS " + VOLANTS_TABLE_NAME + ";";


    /**
     * --------- Table LotVolant ---------
     */

    public static final String LOTVOLANT_ID = "id";
    public static final String LOTVOLANT_TAILLE = "taille";
    public static final String LOTVOLANT_PRIX = "prix";

    public static final String LOTVOLANT_TABLE_NAME = "LotVolant";
    public static final String LOTVOLANT_TABLE_CREATE =
            "CREATE TABLE " + LOTVOLANT_TABLE_NAME + " (" +
                    LOTVOLANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LOTVOLANT_TAILLE + " INTEGER, " +
                    LOTVOLANT_PRIX + " REAL);";

    public static final String LOTVOLANT_TABLE_DROP = "DROP TABLE IF EXISTS " + LOTVOLANT_TABLE_NAME + ";";


    /**
     * --------- Table Date ---------
     */

    public static final String DATE_ID = "id";
    public static final String DATE_DATE = "date";

    public static final String DATE_TABLE_NAME = "Date";
    public static final String DATE_TABLE_CREATE =
            "CREATE TABLE " + DATE_TABLE_NAME + " (" +
                    DATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DATE_DATE + " TEXT);";

    public static final String DATE_TABLE_DROP = "DROP TABLE IF EXISTS " +DATE_TABLE_NAME + ";";


    /**
     * --------- Table Acheteur ---------
     */

    public static final String ACHETEUR_MATRICULE = "matricule";
    public static final String ACHETEUR_NOM = "nom";
    public static final String ACHETEUR_PRENOM = "prénom";
    public static final String ACHETEUR_SOCIETE = "société";

    public static final String ACHETEUR_TABLE_NAME= "Acheteur";
    public static final String ACHETEUR_TABLE_CREATE =
            "CREATE TABLE " + ACHETEUR_TABLE_NAME + " (" +
                    ACHETEUR_MATRICULE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ACHETEUR_NOM + " TEXT, " +
                    ACHETEUR_PRENOM + " TEXT, " +
                    ACHETEUR_SOCIETE + " TEXT);";

    public static final String ACHETEUR_TABLE_DROP = "DROP TABLE IF EXISTS " + ACHETEUR_TABLE_NAME + ";";


    /**
     * --------- Table Acheter ---------
     */

    public static final String ACHETER_ID = "id";
    public static final String ACHETER_LOT_ID = "lot_id";
    public static final String ACHETER_DATE_ID = "date_id";
    public static final String ACHETER_ACHETEUR_ID = "acheteur_id";
    public static final String ACHETER_QUANTITE = "quantité";
    public static final String ACHETER_PAYED = "payed";

    public static final String ACHETER_TABLE_NAME = "Acheter";
    public static final String ACHETER_TABLE_CREATE =
            "CREATE TABLE " + ACHETER_TABLE_NAME + " (" +
                    ACHETER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ACHETER_LOT_ID + " INTEGER, " +
                    ACHETER_DATE_ID + " INTEGER, " +
                    ACHETER_ACHETEUR_ID + " INTEGER, " +
                    ACHETER_QUANTITE + " INTEGER, " +
                    ACHETER_PAYED + " INTEGER);";

    public static final String ACHETER_TABLE_DROP = "DROP TABLE IF EXISTS " + ACHETER_TABLE_NAME + ";";


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(VOLANTS_TABLE_CREATE);
        sqLiteDatabase.execSQL(LOTVOLANT_TABLE_CREATE);
        sqLiteDatabase.execSQL(ACHETEUR_TABLE_CREATE);
        sqLiteDatabase.execSQL(DATE_TABLE_CREATE);
        sqLiteDatabase.execSQL(ACHETER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d("eDBTEAM/DatabaseHandler", "Updating database (from v" + oldVersion + " to v" + newVersion + ")");
        sqLiteDatabase.execSQL(VOLANTS_TABLE_DROP);
        sqLiteDatabase.execSQL(LOTVOLANT_TABLE_DROP);
        sqLiteDatabase.execSQL(ACHETEUR_TABLE_DROP);
        sqLiteDatabase.execSQL(DATE_TABLE_DROP);
        sqLiteDatabase.execSQL(ACHETER_TABLE_DROP);
        onCreate(sqLiteDatabase);
    }


}
