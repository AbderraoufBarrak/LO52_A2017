package com.example.missornela.shuttlesmanagement.Model;


public class Tube_Volant {
    private long id;
    private String reference;
    private double prix;
    private String saison;
    private String classement;
    private long mDistributeurID;
    private int mStock;
    private String mMarque;

    public static final String TABLE_NAME = "Tube_Volant";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Reference";
    public static final String COL_3 = "Prix";
    public static final String COL_4 = "Saison";
    public static final String COL_5 = "Classement";
    public static final String COL_6 = "Distributeur_ID";
    public static final String COL_7 = "Stock";
    public static final String COL_8 = "Marque";


    public static final String TUBE_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_2 + " TEXT, "
            + COL_3 + " REAL NOT NULL, "
            + COL_4 + " TEXT, "
            + COL_5 + " TEXT, "
            + COL_6 + " INTEGER NOT NULL, "
            + COL_7 + " INTEGER NOT NULL, "
            + COL_8 + " TEXT, "
            + "FOREIGN KEY(Distributeur_ID) REFERENCES Distributeur(ID));";


    public Tube_Volant() {
    }

    public Tube_Volant(String reference, double prix, String saison, String classement, long mDistributeurID, int mStock, String mMarque) {
        this.reference = reference;
        this.prix = prix;
        this.saison = saison;
        this.classement = classement;
        this.mStock = mStock;
        this.mDistributeurID = mDistributeurID;
        this.mMarque = mMarque;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    public int getmStock() {
        return mStock;
    }

    public void setmStock(int mStock) {
        this.mStock = mStock;
    }


    public long getmDistributeurID() {
        return mDistributeurID;
    }

    public void setmDistributeurID(long mDistributeurID) {
        this.mDistributeurID = mDistributeurID;
    }


    public String getmMarque() {
        return mMarque;
    }

    public void setmMarque(String mMarqueD) {
        this.mMarque = mMarque;
    }

}
