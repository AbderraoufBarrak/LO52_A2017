package com.aymane.shuttlesmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.lang.Integer.parseInt;

/**
 * Created by .Aymane on 06/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {



    public DatabaseHelper(Context context) {
        super(context, "Shuttles.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Volant (Volant_ID integer PRIMARY KEY AUTOINCREMENT, marque varchar(30),reference varchar(30), prix integer,stock integer, image integer )");
        db.execSQL("create table Acheteur (acheteur_id integer primary key autoincrement,nom varchar(30) )");
        db.execSQL("create table Achat (achat_id integer primary key autoincrement, acheteur varchar(30),paye_ou_non numeric, reference varchar(30), quantite integer , prix integer, acheteur_id integer, FOREIGN KEY (acheteur_id) REFERENCES acheteur(acheteur_id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists achat ");
        db.execSQL("drop table if exists volant ");
        db.execSQL("drop table if exists acheteur ");


        onCreate(db);
    }



    public long insertData(String m , String r, int p,int s, int i){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("marque",m);
        content.put("reference",r);
        content.put("prix",p);
        content.put("stock",s);
        content.put("image",i);
        return (db.insert("volant",null,content));

    }

    public int count(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from achat",null);
        //db.execSQL("delete from acheteur");
        return c.getCount();
    }

    public int count_shuttles(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from volant",null);
        return c.getCount();
    }

    public Cursor select_volants(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from volant",null);
        return c;
    }

    public Cursor select_achats(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from achat",null);
        return c;
    }

    public boolean insert_achat(String ref, String acheteur, String qte, boolean paye){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c3 = db.rawQuery("select stock from volant where reference = \""+ref+"\"",null);
        c3.moveToFirst();
        if(c3.getInt(0) > parseInt(qte)) {

            ContentValues content = new ContentValues();
            content.put("nom", acheteur);
            db.insert("acheteur", null, content);


            Cursor c2 = db.rawQuery("select prix from volant where reference = \"" + ref + "\"", null);
            c2.moveToFirst();

            content = new ContentValues();
            content.put("acheteur", acheteur);
            content.put("paye_ou_non", paye);
            content.put("quantite", qte);
            content.put("reference", ref);
            content.put("prix", parseInt(qte) * c2.getInt(0));
            db.insert("achat", null, content);

            db.execSQL("update volant set stock=stock-" + qte + " where reference=\"" + ref + "\"");
            return true;
        }
        else
        {
            return false;
        }


    }

}
