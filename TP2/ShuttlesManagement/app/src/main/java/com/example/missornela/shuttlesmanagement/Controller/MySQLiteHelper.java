package com.example.missornela.shuttlesmanagement.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.missornela.shuttlesmanagement.Model.Categorie;
import com.example.missornela.shuttlesmanagement.Model.Client;
import com.example.missornela.shuttlesmanagement.Model.Distributeur;
import com.example.missornela.shuttlesmanagement.Model.Facture;
import com.example.missornela.shuttlesmanagement.Model.Tube_Volant;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public int paid ;

    Categorie categorie = new Categorie();
    Client client = new Client();
    Distributeur distributeur = new Distributeur();
    Tube_Volant tube_volant = new Tube_Volant();
    Facture facture = new Facture();

    private static final String DATABASE_NAME = "ShuttlesManagement.db";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    //On crée la BDD et ses tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(client.CLIENT_CREATE_TABLE);
        db.execSQL(distributeur.DISTRIBUTEUR_CREATE_TABLE);
        db.execSQL(tube_volant.TUBE_CREATE_TABLE);
        db.execSQL(facture.FACTURE_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + client.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + distributeur.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + tube_volant.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + facture.TABLE_NAME + ";");
        onCreate(db);
    }

    //INSERTION
    //Distributeurs
    public void insertDistributeurData(Distributeur dist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dist.COL_2, dist.getmNom());
        contentValues.put(dist.COL_3, dist.getmAdresse());
        contentValues.put(dist.COL_4, dist.getmContact());
        contentValues.put(dist.COL_5, dist.getmTelephone());
        contentValues.put(dist.COL_6, dist.getmEmail());
        long result = db.insert(dist.TABLE_NAME, null, contentValues);
       /* if (result== -1)
            return false;
        else
            return  true;*/
    }

    //Categorie
    public void insertCategorieData(Categorie cat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cat.COL_2, cat.getmStock());
        contentValues.put(cat.COL_3, cat.getmDistributeurID());
        long result = db.insert(cat.TABLE_NAME, null, contentValues);

    }

    //Tube volant
    public void insertTube_VolantData(Tube_Volant tube) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(tube.COL_2, tube.getReference());
        contentValues.put(tube.COL_3, tube.getPrix());
        contentValues.put(tube.COL_4, tube.getSaison());
        contentValues.put(tube.COL_5, tube.getClassement());
        contentValues.put(tube.COL_6, tube.getmDistributeurID());
        contentValues.put(tube.COL_7, tube.getmStock());
        contentValues.put(tube.COL_8, tube.getmMarque());

        long result = db.insert(tube.TABLE_NAME, null, contentValues);

    }

    //client
    public void insertClientData(Client cl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cl.COL_2, cl.getmPrenom());
        contentValues.put(cl.COL_3, cl.getmNom());
        contentValues.put(cl.COL_4, cl.getmType());
        contentValues.put(cl.COL_5, cl.getmAdresse());
        contentValues.put(cl.COL_6, cl.getmTelephone());
        long result = db.insert(cl.TABLE_NAME, null, contentValues);

    }

    //facture
    public boolean insertFactureData(Facture fact)
    {
        int paye;
        if (fact.ismPaye()== true)
            paye = 1;
        else
            paye = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(fact.COL_2,fact.getmClientID());
        contentValues.put(fact.COL_3,fact.getmTubeVolantID());
        contentValues.put(fact.COL_4,fact.getmQuantite());
        contentValues.put(fact.COL_5,paye);
        long result = db.insert(fact.TABLE_NAME,null,contentValues);
        if (result == - 1)
            return  false;
        else
            return  true;

    }

    ///////////////////// QUERIES///////////////////////
    //Récuperation du stock
    public Cursor getAllStock() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select ID as _id, Marque, Reference, Stock from "
                + tube_volant.TABLE_NAME , null);
        return result;

    }
    //Récuperation du reference de shuttle
    public Cursor getrefData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select ID as _id, Reference from "
                + tube_volant.TABLE_NAME, null);
        return result;
    }



    public Cursor getAllStock1( String ref)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor result = db.rawQuery("select ID as _id, Marque, Reference, Stock from "
                + tube_volant.TABLE_NAME
                +" WHERE Reference= '"+ref+"'",null);
        return result;
    }

    public Cursor getAllClient( String name)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor result = db.rawQuery("select ID as _id, Nom from "
                + client.TABLE_NAME
                +" WHERE Nom= '"+name+"'",null);
        return result;

    }




    public Cursor getAllPurchase() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select F.ID as _id, C." + client.COL_2 +
                ", C." + client.COL_3 + ",F." + facture.COL_4 + "," +
                " T." + tube_volant.COL_2 + ",T." + tube_volant.COL_3 + ",F." + facture.COL_5 + " from " + client.TABLE_NAME + " C, "
                + tube_volant.TABLE_NAME + " T, " + facture.TABLE_NAME +
                " F WHERE F." + facture.COL_2 + " = C." + client.COL_1 + " AND" +
                " F." + facture.COL_3 + " = T." + tube_volant.COL_1, null);
        return result;
    }

    public Cursor getClient(Client c)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select C.ID as _id, C."+c.COL_2+", C."+ c.COL_3+
                ", C."+ c.COL_4+", C."+ c.COL_5+", C."+c.COL_6+" from "
                +c.TABLE_NAME+ " C WHERE C."+c.COL_2+ "= '"+c.getmPrenom()+"'"+" AND C."
                +c.COL_3+" = '"+c.getmNom()+"'",null);
        return result;
    }







}