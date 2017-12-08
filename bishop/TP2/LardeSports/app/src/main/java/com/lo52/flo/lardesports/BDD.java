package com.lo52.flo.lardesports;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Flo on 24.10.2017.
 */

public class BDD extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bdd.db" ;

    public static final String STOCK_TABLE_NAME = "stock";
    public static final String STOCK_COLUMN_MARQUE_ID = "stock_marque_id";
    public static final String STOCK_COLUMN_QUANTITE = "stock_quantite";

    public static final String VOLANTS_TABLE_NAME = "volant";
    public static final String VOLANTS_COLUMN_ID = "volant_id";
    public static final String VOLANTS_COLUMN_REF = "volant_ref";
    public static final String VOLANTS_COLUMN_MARQUE = "volant_marque_id";
    public static final String VOLANTS_COLUMN_CATEGORIE = "volant_categ";
    public static final String VOLANTS_COLUMN_DISTRIBUTEUR = "volant_distrib_id";
    public static final String VOLANTS_COLUMN_PRIX = "volant_prix";

    public static final String DISTRIBUTEUR_TABLE_NAME = "distrib";
    public static final String DISTRIBUTEUR_COLUMN_ID = "distrib_id";
    public static final String DISTRIBUTEUR_COLUMN_NAME = "distrib_name";
    public static final String DISTRIBUTEUR_COLUMN_ADRESS = "distrib_adress";
    public static final String DISTRIBUTEUR_COLUMN_CONTACT = "distrib_contact";
    public static final String DISTRIBUTEUR_COLUMN_TEL = "distrib_tel";
    public static final String DISTRIBUTEUR_COLUMN_EMAIL = "distrib_email";

    public static final String MARQUE_TABLE_NAME = "marque";
    public static final String MARQUE_COLUMN_ID = "marque_id";
    public static final String MARQUE_COLUMN_LIBELLE = "marque_libelle";

    public static final String ACHAT_TABLE_NAME = "achat";
    public static final String ACHAT_COLUMN_ID = "achat_id";
    public static final String ACHAT_COLUMN_CLIENT_NAME = "achat_client_name";
    public static final String ACHAT_COLUMN_VOLANT = "achat_volant_id";
    public static final String ACHAT_COLUMN_QUANTITE = "achat_quantite";
    public static final String ACHAT_COLUMN_PAYE = "achat_payé";

    private int databaseVersion;

    public BDD(Context context) {
        super(context, DATABASE_NAME, null, 1);
        databaseVersion = 1;
    }

    public BDD(Context context, int databaseVersion) {
        super(context, DATABASE_NAME, null, databaseVersion);
        this.databaseVersion = databaseVersion;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table " + STOCK_TABLE_NAME +
                        " (" + STOCK_COLUMN_MARQUE_ID + " integer primary key, " +
                        STOCK_COLUMN_QUANTITE + " integer)"
        );

        db.execSQL(
                "create table " + VOLANTS_TABLE_NAME +
                        " (" + VOLANTS_COLUMN_ID + " integer primary key, " +
                        VOLANTS_COLUMN_REF + " text, " +
                        VOLANTS_COLUMN_MARQUE + " integer, " +
                        VOLANTS_COLUMN_CATEGORIE + " text, " +
                        VOLANTS_COLUMN_DISTRIBUTEUR + " integer, " +
                        VOLANTS_COLUMN_PRIX + " double)"
        );

        db.execSQL(
                "create table " + DISTRIBUTEUR_TABLE_NAME +
                        " (" + DISTRIBUTEUR_COLUMN_ID + " integer primary key, " +
                        DISTRIBUTEUR_COLUMN_ADRESS + " text, " +
                        DISTRIBUTEUR_COLUMN_CONTACT + " text, " +
                        DISTRIBUTEUR_COLUMN_TEL + " text, " +
                        DISTRIBUTEUR_COLUMN_EMAIL + " text)"
        );

        db.execSQL(
                "create table " + MARQUE_TABLE_NAME +
                        " (" + MARQUE_COLUMN_ID + " integer primary key, " +
                        MARQUE_COLUMN_LIBELLE + " text)"
        );

        db.execSQL(
                "create table " + ACHAT_TABLE_NAME +
                        " (" + ACHAT_COLUMN_ID + " integer primary key, " +
                        ACHAT_COLUMN_CLIENT_NAME + " text, " +
                        ACHAT_COLUMN_VOLANT + " integer, " +
                        ACHAT_COLUMN_QUANTITE + " integer, " +
                        ACHAT_COLUMN_PAYE + " integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + STOCK_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VOLANTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DISTRIBUTEUR_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MARQUE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ACHAT_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertStockArticle (int marque_id, int qte) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STOCK_COLUMN_MARQUE_ID, marque_id);
        contentValues.put(STOCK_COLUMN_QUANTITE, qte);
        db.insert(STOCK_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateStockArticle (int marque_id, int qte) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STOCK_COLUMN_MARQUE_ID, marque_id);
        contentValues.put(STOCK_COLUMN_QUANTITE, qte);
        db.update(STOCK_TABLE_NAME, contentValues, "marque_id = ? ", new String[] { Integer.toString(marque_id) } );
        return true;
    }

    public boolean insertArticle (int id, String ref, int marque_id, Categorie cat, int distributeur_id, double prix) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VOLANTS_COLUMN_ID, id);
        contentValues.put(VOLANTS_COLUMN_REF, ref);
        contentValues.put(VOLANTS_COLUMN_MARQUE, marque_id);
        contentValues.put(VOLANTS_COLUMN_CATEGORIE, Categorie.catToString(cat));
        contentValues.put(VOLANTS_COLUMN_DISTRIBUTEUR, distributeur_id);
        contentValues.put(VOLANTS_COLUMN_PRIX, prix);
        db.insert(VOLANTS_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateArticle (int id, String ref, int marque_id, Categorie cat, int distributeur_id, double prix) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VOLANTS_COLUMN_ID, id);
        contentValues.put(VOLANTS_COLUMN_REF, ref);
        contentValues.put(VOLANTS_COLUMN_MARQUE, marque_id);
        contentValues.put(VOLANTS_COLUMN_CATEGORIE, Categorie.catToString(cat));
        contentValues.put(VOLANTS_COLUMN_DISTRIBUTEUR, distributeur_id);
        contentValues.put(VOLANTS_COLUMN_PRIX, prix);
        db.update(VOLANTS_TABLE_NAME, contentValues, "volant_id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean insertDistributeur (int id, String name, String adress, String contact, String tel, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DISTRIBUTEUR_COLUMN_ID, id);
        contentValues.put(DISTRIBUTEUR_COLUMN_NAME, name);
        contentValues.put(DISTRIBUTEUR_COLUMN_ADRESS, adress);
        contentValues.put(DISTRIBUTEUR_COLUMN_CONTACT, contact);
        contentValues.put(DISTRIBUTEUR_COLUMN_TEL, tel);
        contentValues.put(DISTRIBUTEUR_COLUMN_EMAIL, email);
        db.insert(DISTRIBUTEUR_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateDistributeur (int id, String name, String adress, String contact, String tel, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DISTRIBUTEUR_COLUMN_ID, id);
        contentValues.put(DISTRIBUTEUR_COLUMN_NAME, name);
        contentValues.put(DISTRIBUTEUR_COLUMN_ADRESS, adress);
        contentValues.put(DISTRIBUTEUR_COLUMN_CONTACT, contact);
        contentValues.put(DISTRIBUTEUR_COLUMN_TEL, tel);
        contentValues.put(DISTRIBUTEUR_COLUMN_EMAIL, email);
        db.update(DISTRIBUTEUR_TABLE_NAME, contentValues, "distrib_id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean insertMarque (int id, String libelle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MARQUE_COLUMN_ID, id);
        contentValues.put(MARQUE_COLUMN_LIBELLE, libelle);
        db.insert(MARQUE_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateMarque (int id, String libelle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MARQUE_COLUMN_ID, id);
        contentValues.put(MARQUE_COLUMN_LIBELLE, libelle);
        db.update(MARQUE_TABLE_NAME, contentValues, "marque_id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean insertAchat (String client_name, int volant_id, int qte, boolean isPayed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        int payedIntegerBool;
        if(isPayed) payedIntegerBool = 1;
        else payedIntegerBool = 0;

        //contentValues.put(ACHAT_COLUMN_ID, id);
        contentValues.put(ACHAT_COLUMN_CLIENT_NAME, client_name);
        contentValues.put(ACHAT_COLUMN_VOLANT, volant_id);
        contentValues.put(ACHAT_COLUMN_QUANTITE, qte);
        contentValues.put(ACHAT_COLUMN_PAYE, payedIntegerBool);
        db.insert(ACHAT_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateAchat (int id, String client_name, int volant_id, int qte, boolean isPayed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACHAT_COLUMN_ID, id);
        contentValues.put(ACHAT_COLUMN_CLIENT_NAME, client_name);
        contentValues.put(ACHAT_COLUMN_VOLANT, volant_id);
        contentValues.put(ACHAT_COLUMN_QUANTITE, qte);
        contentValues.put(ACHAT_COLUMN_PAYE, isPayed);
        db.update(ACHAT_TABLE_NAME, contentValues, "achat_id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean articleExists(int id) {
        Cursor cursor = this.getReadableDatabase().rawQuery( "select 1 from " + VOLANTS_TABLE_NAME + " where volant_id=" + id, null );
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public boolean distributeurExists(int id) {
        Cursor cursor = this.getReadableDatabase().rawQuery( "select 1 from " + DISTRIBUTEUR_TABLE_NAME + " where distrib_id=" + id, null );
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public boolean marqueExists(int id) {
        Cursor cursor = this.getReadableDatabase().rawQuery( "select 1 from " +MARQUE_TABLE_NAME + " where marque_id=" + id, null );
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public ArrayList<ArticleBDD> getAllArticles() {
        ArrayList<ArticleBDD> array_list = new ArrayList<ArticleBDD>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + VOLANTS_TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){

            array_list.add(new ArticleBDD(
                    res.getInt(res.getColumnIndex(VOLANTS_COLUMN_ID)),
                    res.getString(res.getColumnIndex(VOLANTS_COLUMN_REF)),
                    res.getInt(res.getColumnIndex(VOLANTS_COLUMN_MARQUE)),
                    Categorie.stringToCat(res.getString(res.getColumnIndex(VOLANTS_COLUMN_CATEGORIE))),
                    res.getInt(res.getColumnIndex(VOLANTS_COLUMN_DISTRIBUTEUR)),
                    res.getDouble(res.getColumnIndex(VOLANTS_COLUMN_PRIX))));

            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Achat> getAllAchats() {
        ArrayList<Achat> array_list = new ArrayList<Achat>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + ACHAT_TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){

            array_list.add(new Achat(
                    res.getString(res.getColumnIndex(ACHAT_COLUMN_CLIENT_NAME)),
                    res.getInt(res.getColumnIndex(ACHAT_COLUMN_QUANTITE)),
                    res.getInt(res.getColumnIndex(ACHAT_COLUMN_VOLANT)),
                    (res.getInt(res.getColumnIndex(ACHAT_COLUMN_PAYE))) != 0));

            res.moveToNext();
        }
        return array_list;
    }

    public Achat getAchatWithId(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + ACHAT_TABLE_NAME +
                " where " + ACHAT_COLUMN_ID + "=" + Integer.toString(id) , null );
        res.moveToFirst();

        if(!res.isAfterLast()){

            return new Achat(
                    res.getString(res.getColumnIndex(ACHAT_COLUMN_CLIENT_NAME)),
                    res.getInt(res.getColumnIndex(ACHAT_COLUMN_QUANTITE)),
                    res.getInt(res.getColumnIndex(ACHAT_COLUMN_VOLANT)),
                    (res.getInt(res.getColumnIndex(ACHAT_COLUMN_PAYE))) != 0);
        }
        return null;
    }

    public ArrayList<MarqueBDD> getAllMarques() {
        ArrayList<MarqueBDD> array_list = new ArrayList<MarqueBDD>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + MARQUE_TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){

            array_list.add(new MarqueBDD(res.getInt(res.getColumnIndex(MARQUE_COLUMN_ID)),
                    res.getString(res.getColumnIndex(MARQUE_COLUMN_LIBELLE))));

            res.moveToNext();
        }
        return array_list;
    }

    public int getArticleIdWithRef(String ref) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + VOLANTS_TABLE_NAME + " where " + VOLANTS_COLUMN_REF
                + "=\"" + ref + "\"", null );
        res.moveToFirst();

        if(!res.isAfterLast()){
            return res.getInt(res.getColumnIndex(VOLANTS_COLUMN_ID));
        }
        return -1;
    }

    public ArrayList<ArticleBDD> getArticlesWithMarque(int marque_id) {
        ArrayList<ArticleBDD> array_list = new ArrayList<ArticleBDD>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + VOLANTS_TABLE_NAME + " where " + VOLANTS_COLUMN_MARQUE
                + "=" + Integer.toString(marque_id), null );
        res.moveToFirst();

        while(!res.isAfterLast()){

            array_list.add(new ArticleBDD(
                    res.getInt(res.getColumnIndex(VOLANTS_COLUMN_ID)),
                    res.getString(res.getColumnIndex(VOLANTS_COLUMN_REF)),
                    res.getInt(res.getColumnIndex(VOLANTS_COLUMN_MARQUE)),
                    Categorie.stringToCat(res.getString(res.getColumnIndex(VOLANTS_COLUMN_CATEGORIE))),
                    res.getInt(res.getColumnIndex(VOLANTS_COLUMN_DISTRIBUTEUR)),
                    res.getDouble(res.getColumnIndex(VOLANTS_COLUMN_PRIX))));

            res.moveToNext();
        }
        return array_list;
    }

    public HashMap<Integer, Integer> getStockArticles() {

        HashMap<Integer, Integer> stock = new HashMap<Integer, Integer>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + STOCK_TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            stock.put(res.getInt(res.getColumnIndex(STOCK_COLUMN_MARQUE_ID)),
                    res.getInt(res.getColumnIndex(STOCK_COLUMN_QUANTITE)));
            res.moveToNext();
        }
        return stock;
    }

    public DistributeurBDD getDistributeurById(int id){

        DistributeurBDD distrib = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + DISTRIBUTEUR_TABLE_NAME + " where "
                + DISTRIBUTEUR_COLUMN_ID + "=" + Integer.toString(id), null );
        res.moveToFirst();

        if(!res.isAfterLast()){
            distrib = new DistributeurBDD(
                    res.getInt(res.getColumnIndex(DISTRIBUTEUR_COLUMN_ID)),
                    res.getString(res.getColumnIndex(DISTRIBUTEUR_COLUMN_NAME)),
                    res.getString(res.getColumnIndex(DISTRIBUTEUR_COLUMN_ADRESS)),
                    res.getString(res.getColumnIndex(DISTRIBUTEUR_COLUMN_CONTACT)),
                    res.getString(res.getColumnIndex(DISTRIBUTEUR_COLUMN_TEL)),
                    res.getString(res.getColumnIndex(DISTRIBUTEUR_COLUMN_EMAIL)));
        }
        return distrib;
    }

    public ArticleBDD getArticleBDDById(int id){

        ArticleBDD article = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + VOLANTS_TABLE_NAME + " where "
                + VOLANTS_COLUMN_ID + "=" + Integer.toString(id), null );
        res.moveToFirst();

        if(!res.isAfterLast()){
            article = new ArticleBDD(
                    res.getInt(res.getColumnIndex(VOLANTS_COLUMN_ID)),
                    res.getString(res.getColumnIndex(VOLANTS_COLUMN_REF)),
                    res.getInt(res.getColumnIndex(VOLANTS_COLUMN_MARQUE)),
                    Categorie.stringToCat(res.getString(res.getColumnIndex(VOLANTS_COLUMN_CATEGORIE))),
                    res.getInt(res.getColumnIndex(VOLANTS_COLUMN_DISTRIBUTEUR)),
                    res.getDouble(res.getColumnIndex(VOLANTS_COLUMN_PRIX)));
        }
        return article;
    }

    public MarqueBDD getMarqueById(int id){

        MarqueBDD marqueBdd = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + MARQUE_TABLE_NAME + " where "
                + MARQUE_COLUMN_ID + "=" + Integer.toString(id), null );
        res.moveToFirst();

        if(!res.isAfterLast()){
            marqueBdd = new MarqueBDD(
                    res.getInt(res.getColumnIndex(MARQUE_COLUMN_ID)),
                    res.getString(res.getColumnIndex(MARQUE_COLUMN_LIBELLE)));
        }
        return marqueBdd;
    }

    public int getDatabaseVersion(){
        return  databaseVersion;
    }

    public Integer deleteArticle (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(VOLANTS_TABLE_NAME, "volant_id = ? ", new String[] { Integer.toString(id) });
    }

    public Integer deleteDistributeur (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DISTRIBUTEUR_TABLE_NAME, "distrib_id = ? ", new String[] { Integer.toString(id) });
    }

    public Integer deleteMarque (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(MARQUE_TABLE_NAME, "marque_id = ? ", new String[] { Integer.toString(id) });
    }

    public void initBDD(){

        insertMarque(1, "Artengo");
        insertMarque(2, "Ashaway");
        insertMarque(3, "Babolat");
        insertMarque(4, "Carlton");
        insertMarque(5, "FZ Forza");
        insertMarque(6, "Honkoo");
        insertMarque(7, "Kunli");
        insertMarque(8, "Li-Ning");
        insertMarque(9, "Oliver");
        insertMarque(10, "Pro Kennex");
        insertMarque(11, "Redclear");
        insertMarque(12, "RSL");
        insertMarque(13, "Shadow16");
        insertMarque(14, "SOTX");
        insertMarque(15, "Spin");
        insertMarque(16, "Trinity");
        insertMarque(17, "Trump");
        insertMarque(18, "Victor");
        insertMarque(19, "Wilson");
        insertMarque(20, "Yin Tai");
        insertMarque(21, "Yonex");

        insertDistributeur(1, "OXYLANE - DMI Artengo", "4 Bd de Mons - BP 299 59650 Villeneuve D'Ascq", "M. Jensen Hans Peter", "0611163746", "hans.jensen@artengo.com");
        insertDistributeur(2, "Ashaway", "11 rue Georges Feydeau 72100 Le Mans", "--", "--", "--");
        insertDistributeur(3, "Babolat", "93 rue André Bollier 69007 Lyon", "M. Laverty Grégoire", "0478697869", "nsene@babolat.com");
        insertDistributeur(4, "Dunlop Slazenger Group", "8 rue de l'Angounois - BP 8003 95811 Argenteuil Cedex", "Mme Baril Claire", "0139983446", "claire@dunlop-slazenger.fr");
        insertDistributeur(5, "FZ Forza", "3 allée du golf 74160 Bossey", "M. Galent Bruno", "0650553931", "bruno.galent@fzforza.fr");
        insertDistributeur(6, "Kunli & Honkoo", "5 rue Saint Just 92000 Nanterre", "Carle", "0676715072", "--");
        insertDistributeur(7, "Kason Europa GMBH", "Monschauer Strabe 21 40549 Dusseldorf - Allemagne", "--", "+492119764803", "oliver.schimdt@li-ning.de");
        insertDistributeur(8, "Bad'N Squash", "-BP 1307 44213 Pornic Cedex", "M. Guibreteau Pascal", "0621211971", "badminton.oliver.fr");
        insertDistributeur(9, "Pat Sport SARL", "Avenue du théatre 16 1010 Lausanne - Suisse", "M. Guibreteau Pascal", "+782005483", "badminton.oliver.fr");
        insertDistributeur(10, "West Gut Pro Kennex", "2 rue Roland Garros - ZA Val de Sauve 31570 Sainte Foy d'Aigrefeuille", "M. Gabalda Eric", "0561216810", "west.gut@wanadoo.fr");
        insertDistributeur(11, "SDIC s.p.r.l.", "Rue des Palais 33 boîte G7 1030 Bruxelles - Belgique", "M. Bol Stéphane", "+32492768815", "info@sdic.be");
        insertDistributeur(12, "Larde Sports", "7 rue Albert Cacquot 28500 Vernouillet", "M. Larde Patrick", "0237501683", "lardesports@wanadoo.fr");
        insertDistributeur(13, "Shadow16", "4 allée Maurice Ravel 93160 Noisy le Grand", "M. Gort Léon", "0661731397", "contact@shadow16.com");
        insertDistributeur(14, "Evosport SARL", "2022 rue d'Alvette 62232 Annezin", "M. Druon Pascal", "0972573667", "contact@evosport.fr");
        insertDistributeur(15, "Trinisports", "2 rue Jules Forey 21000 Dijon", "M. Trinh Thai", "0689294819", "contact.trinisports.fr");
        insertDistributeur(16, "Eurobad", "4 Avenue Jean de la Fontaine 44500 La Baule", "M. Brosse Christophe", "0685123827", "trump@eurobad.fr");
        insertDistributeur(17, "CFB Victor", "Le River - 5 bd George Méliès 94356 Villiers sur Marne Cedex", "Mmes Biacchesti et Forge", "0149307770", "fbsport@wanadoo.fr");
        insertDistributeur(18, "Amer Sports France", "63 rue Condorcet - BP 128 38093 Villefontaine Cedex", "M. Wauters Franck", "0474991515", "wilsonfrance@amersports.com");
        insertDistributeur(19, "Kim Kim Pacifique CIE", "5/7 Rue Marcelin Berhelot 92762 Antony", "M. Jin Jérôme", "0680481928", "kim.kim.pacifique@gmail.com");
        insertDistributeur(20, "BDE Yonex", "7 rue Joseph Marie Jacquard 67400 Illkirch", "--", "0614182424", "p.lauret@bde-sports.com");


        insertArticle(1, "BSC 880", 1, Categorie.Standard, 1, 0);
        insertArticle(2, "BSC 950", 1, Categorie.Elite, 1, 0);
        insertArticle(3, "BSC 930", 1, Categorie.Standard, 1, 0);
        insertArticle(4, "A6", 2, Categorie.Elite, 2, 0);
        insertArticle(5, "A8", 2, Categorie.Standard, 2, 0);
        insertArticle(6, "2", 3, Categorie.Elite, 3, 0);
        insertArticle(7, "3", 3, Categorie.Standard, 3, 0);
        insertArticle(8, "4", 3, Categorie.Standard, 3, 0);
        insertArticle(9, "CARLTON GT 1", 4, Categorie.Elite, 4, 0);
        insertArticle(10, "CARLTON GT 3", 4, Categorie.Standard, 4, 0);
        insertArticle(11, "S-3000", 5, Categorie.Standard, 5, 0);
        insertArticle(12, "S-5000", 5, Categorie.Standard, 5, 0);
        insertArticle(13, "S-6000", 5, Categorie.Elite, 5, 0);
        insertArticle(14, "VIP", 5, Categorie.Elite, 5, 0);
        insertArticle(15, "SHKO", 6, Categorie.Standard, 6, 0);
        insertArticle(16, "KL-02", 7, Categorie.Standard, 6, 0);
        insertArticle(17, "A+300", 8, Categorie.Elite, 7, 0);
        insertArticle(18, "GrandPrix Gold", 8, Categorie.Elite, 7, 0);
        insertArticle(19, "APEX 100", 9, Categorie.Elite, 8, 0);
        insertArticle(20, "APEX 100", 9, Categorie.Elite, 9, 0);
        insertArticle(21, "Premium", 10, Categorie.Elite, 10, 0);
        insertArticle(22, "Classic", 10, Categorie.Standard, 10, 0);
        insertArticle(23, "T-30", 11, Categorie.Standard, 11, 0);
        insertArticle(24, "RSL Grade 1", 12, Categorie.Elite, 12, 0);
        insertArticle(25, "RSL Grade 3", 12, Categorie.Elite, 12, 0);
        insertArticle(26, "RSL Grade 4", 12, Categorie.Elite, 12, 0);
        insertArticle(27, "Ultimate", 13, Categorie.Standard, 13, 0);
        insertArticle(28, "Advanced", 13, Categorie.Standard, 13, 0);
        insertArticle(29, "Original", 13, Categorie.Standard, 13, 0);
        insertArticle(30, "Tough 03", 14, Categorie.Elite, 14, 0);
        insertArticle(31, "Speed Silver", 15, Categorie.Standard, 15, 0);
        insertArticle(32, "TR3", 16, Categorie.Standard, 15, 0);
        insertArticle(33, "TR2", 16, Categorie.Standard, 15, 0);
        insertArticle(34, "T-101", 17, Categorie.Elite, 16, 0);
        insertArticle(35, "T-303", 17, Categorie.Elite, 16, 0);
        insertArticle(36, "Champion 1", 18, Categorie.Standard, 17, 0);
        insertArticle(37, "Gold", 18, Categorie.Elite, 17, 0);
        insertArticle(38, "Champion 5", 18, Categorie.Standard, 17, 0);
        insertArticle(39, "Pro", 19, Categorie.Elite, 18, 0);
        insertArticle(40, "Team", 19, Categorie.Standard, 18, 0);
        insertArticle(41, "A100", 20, Categorie.Elite, 19, 0);
        insertArticle(42, "A200", 20, Categorie.Elite, 19, 0);
        insertArticle(43, "A300", 20, Categorie.Standard, 19, 0);
        insertArticle(44, "Aeroclub TR", 21, Categorie.Standard, 20, 0);
        insertArticle(45, "AS 20", 21, Categorie.Elite, 20, 0);
        insertArticle(46, "AS 30", 21, Categorie.Elite, 20, 0);
        insertArticle(47, "League 7", 21, Categorie.Standard, 20, 0);

        for(int i=1; i<=47;++i){
            insertStockArticle(i, Double.valueOf(Math.random()*200).intValue());
        }

        insertAchat("Client 1", 5, 12, true);
        insertAchat("Client 2", 2, 24, true);
        insertAchat("Client 3", 45, 6, false);
        insertAchat("Client 4", 7, 1, true);
        insertAchat("Client 5", 21, 410, false);
        insertAchat("Client 6", 26, 120, false);
        insertAchat("Client 7", 31, 86, true);
        insertAchat("Client 8", 11, 450, true);
        insertAchat("Client 9", 18, 89, false);
        insertAchat("Client 10", 4, 2, true);
        insertAchat("Client 11", 5, 6, true);
        insertAchat("Client 12", 4,  10, true);
        insertAchat("Client 13", 45, 23, false);
    }
}
