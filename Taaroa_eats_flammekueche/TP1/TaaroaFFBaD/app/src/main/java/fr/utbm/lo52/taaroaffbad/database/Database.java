package fr.utbm.lo52.taaroaffbad.Database;

/**
 * Created by Jordan on 22/09/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {


    // table VOLANT
    public static final String TABLE_VOLANT = "volant";
    public static final String VOL_VAL1 = "validite1_volant";
    public static final String VOL_VAL2 = "validite2_volant";
    public static final String VOL_MARQUE = "marque_volant";
    public static final String VOL_REF = "ref_volant";
    public static final String VOL_CLASSEMENT = "classement_volant";
    public static final String VOL_PRIX = "prix_volant";
    public static final String VOL_STOCK = "stock_volant";

    // create table VOLANT
    private static final String CREATE_VOLANT = "create table " + TABLE_VOLANT
            + "(" + VOL_VAL1 + " text not null, "
            + VOL_VAL2 + " text not null, "
            + VOL_MARQUE + " text not null, "
            + VOL_REF + " text not null, "
            + VOL_CLASSEMENT + " integer not null, "
            + VOL_PRIX + " real not null, "
            + VOL_STOCK + " integer not null, "
            + "primary key(" + VOL_MARQUE+ "," + VOL_REF + ")"
            + ");";

    // table FABRICANT
    public static final String TABLE_FABRICANT = "fabricant";
    public static final String FAB_ID = "num_fabricant";
    public static final String FAB_NOM = "nom_fabricant";
    public static final String FAB_ADR = "adr_fabricant";
    public static final String FAB_CONTACT = "contact_fabricant";
    public static final String FAB_TEL = "tel_fabricant";
    public static final String FAB_mail = "mail_fabricant";

    // create table FABRICANT
    private static final String CREATE_FABRICANT = "create table " + TABLE_FABRICANT
            + "(" + FAB_ID + " integer primary key autoincrement, "
            + FAB_NOM + " text not null, "
            + FAB_ADR + " text not null, "
            + FAB_CONTACT + " text not null, "
            + FAB_TEL + " text not null, "
            + FAB_mail + " text not null"
            + ");";

    // table ACHETEUR
    public static final String TABLE_ACHETEUR = "acheteur";
    public static final String ACH_ID = "num_acheteur";
    public static final String ACH_NOM = "nom_acheteur";
    public static final String ACH_TYPE = "type_acheteur";

    // create table ACHETEUR
    private static final String CREATE_ACHETEUR = "create table " + TABLE_ACHETEUR
            + "(" + ACH_ID + " integer primary key autoincrement, "
            + ACH_NOM + " text not null, "
            + ACH_TYPE + " text not null"
            + ");";

    // table VENTE
    public static final String TABLE_VENTE = "vente";
    public static final String VEN_ID = "num_vente";//primary key
    public static final String VEN_VOL_MARQUE = "marque_volant";//foreign key
    public static final String VEN_VOL_REF = "ref_volant";      //same
    public static final String VEN_FAB_ID = "num_fabricant";    //same
    public static final String VEN_ACH_ID = "num_acheteur";     //same
    public static final String VEN_PRIX = "prix_vente";
    public static final String VEN_PAYE = "paye_vente";
    public static final String VEN_QUANTITE = "quantite_vente";
    public static final String VEN_DATE_VENTE = "date_achat_vente";
    public static final String VEN_DATE_PAYE = "date_paye_vente";

    // create table VENTE
    private static final String CREATE_VENTE = "create table " + TABLE_VENTE
            + "(" + VEN_ID + " integer primary key autoincrement, "
            + VEN_VOL_MARQUE + " text not null, "
            + VEN_VOL_REF + " text not null, "
            + VEN_FAB_ID + " integer not null, "
            + VEN_ACH_ID + " integer not null, "
            + VEN_PRIX + " numeric not null, " // nb
            + VEN_PAYE + " numeric not null, "   // nb
            + VEN_QUANTITE + " numeric not null, "
            + VEN_DATE_VENTE + " text not null, "
            + VEN_DATE_PAYE + " text null"
            /*+ "foreign key(" + VEN_VOL_MARQUE + "," + VEN_VOL_REF + ") references " + TABLE_VOLANT + " ("+VOL_MARQUE+","+VOL_REF+"), "
            + "foreign key(" + VEN_FAB_ID + ") references " + TABLE_FABRICANT + " (" + FAB_ID + "), "
            + "foreign key(" + VEN_ACH_ID + ") references " + TABLE_ACHETEUR + " (" + ACH_ID + ")"*/
            + ");";



    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_FABRICANT);
        database.execSQL(CREATE_ACHETEUR);
        database.execSQL(CREATE_VOLANT);
        database.execSQL(CREATE_VENTE);
        Log.i("YVAN-DB","CREATION DES TABLES");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("YVAN-DB",
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOLANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FABRICANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACHETEUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENTE);
        onCreate(db);
    }
}