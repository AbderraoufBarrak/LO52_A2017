package io.hervenrv.shuttlesmgmt.BDD.Produit;

/**
 * Created by Utilisateur on 17/11/2017.
 */

public final class DBConst {
    public static final String PRODUIT_MARQUE = "marque";
    public static final String PRODUIT_REF = "ref";
    public static final String PRODUIT_PRIX = "prix";
    public static final String PRODUIT_NOM = "nom";
    public static final String PRODUIT_IMAGEID = "imageID";


    public static final String PRODUIT_TABLE_NAME = "Produit";

    public static final String PRODUIT_TABLE_CREATE =
            "CREATE TABLE " + PRODUIT_TABLE_NAME + " (" +
                    PRODUIT_REF + " TEXT PRIMARY KEY, " +
                    PRODUIT_MARQUE + " TEXT, " +
                    PRODUIT_PRIX  + " REAL, " +
                    PRODUIT_NOM + " STRING, " +
                    PRODUIT_IMAGEID + " INTEGER);";

    public static final String PRODUIT_TABLE_DROP = "DROP TABLE IF EXISTS " + PRODUIT_TABLE_NAME + ";";

    private DBConst(){}
}
