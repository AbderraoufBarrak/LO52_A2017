package io.hervenrv.shuttlesmgmt.BDD.Stock;

/**
 * Created by Utilisateur on 17/11/2017.
 */

public final class DBConst {
    public static final String STOCK_REF = "ref";
    public static final String STOCK_QUANTITE = "quantite";


    public static final String STOCK_TABLE_NAME = "Stock";

    public static final String STOCK_TABLE_CREATE =
            "CREATE TABLE " + STOCK_TABLE_NAME + " (" +
                    STOCK_REF + " TEXT PRIMARY KEY, " +
                    STOCK_QUANTITE  + " INTEGER);";

    public static final String STOCK_TABLE_DROP = "DROP TABLE IF EXISTS " + STOCK_TABLE_NAME + ";";

    private DBConst(){}
}
