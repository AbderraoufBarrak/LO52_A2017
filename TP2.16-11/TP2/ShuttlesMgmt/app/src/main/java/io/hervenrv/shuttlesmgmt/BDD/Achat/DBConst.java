package io.hervenrv.shuttlesmgmt.BDD.Achat;

/**
 * Created by Utilisateur on 17/11/2017.
 */

public final class DBConst {
    public static final String ACHAT_KEY = "id";
    public static final String ACHAT_ACHETEUR = "acheteur";
    public static final String ACHAT_REF = "ref";
    public static final String ACHAT_QUANTITE = "quantite";
    public static final String ACHAT_PAYE = "paye";
    public static final String ACHAT_DATE = "Date";


    public static final String ACHAT_TABLE_NAME = "Achat";

    public static final String ACHAT_TABLE_CREATE =
            "CREATE TABLE " + ACHAT_TABLE_NAME + " (" +
                    ACHAT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ACHAT_ACHETEUR + " TEXT, " +
                    ACHAT_REF + " TEXT, " +
                    ACHAT_QUANTITE  + " INTEGER, " +
                    ACHAT_PAYE + " INTEGER, "+
                    ACHAT_DATE + " TEXT);";

    public static final String ACHAT_TABLE_DROP = "DROP TABLE IF EXISTS " + ACHAT_TABLE_NAME + ";";

    private DBConst(){}
}
