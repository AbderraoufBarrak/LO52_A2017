package com.example.shuttlesmgmt.DAOImplements;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shuttlesmgmt.DAO.DAO;
import com.example.shuttlesmgmt.db.ShuttlesSchema;
import com.example.shuttlesmgmt.entity.Version2.Product;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 2017/10/31.
 */

public class ProductDAOImpl extends DAO<Product> {

    public ProductDAOImpl(Context c) {
        super(c);
    }

    @Override
    public SQLiteDatabase openRead() {
        return super.openRead();
    }

    @Override
    public SQLiteDatabase openWrite() {
        return super.openWrite();
    }

    @Override
    public SQLiteDatabase getDBRead() {
        return super.getDBRead();
    }

    @Override
    public SQLiteDatabase getDBWrite() {
        return super.getDBWrite();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public SQLiteDatabase getDB() {
        return super.getDB();
    }

    @Override
    public boolean isExist(Product obj) {
        Cursor c = getDBRead().rawQuery(
                "SELECT * FROM " + ShuttlesSchema.Product.PRODUCT_TABLE_NAME,
                null
        );
        if(c != null){
            while(c.moveToNext()){
                if(c.getString(1).contentEquals(obj.getName()) && c.getString(2).contentEquals(obj.getRef()) && c.getLong(5)==obj.getIdSupplier()){
                    c.close();
                    return true;
                }
            }
        }
        c.close();
        return false;
    }

    @Override
    public boolean isEmpty() {
        Cursor c = getDBRead().rawQuery(
                "SELECT COUNT ( " +
                        ShuttlesSchema.Product.PRODUCT_ID +
                        " ) FROM " +
                        ShuttlesSchema.Product.PRODUCT_TABLE_NAME,
                null);
        if(c.moveToFirst()){
            return c.getLong(0) == 0;
        }else{
            return false;
        }
    }

    @Override
    public void readData(int datafile, Context c) {
            InputStream inputstream = c.getResources().openRawResource(datafile);
            List<Product> listProduct = new ArrayList<>();
            Product product;
            String[] splits;
            String lines;

            if(inputstream != null){
                //Log.i("AppInfo", "J'ai trouve le fichier");
                InputStreamReader inputreader = new InputStreamReader(inputstream);
                BufferedReader buffreader = new BufferedReader(inputreader);

                try{
                    while((lines = buffreader.readLine()) != null){
                        Log.i("AppInfo", "Line :" + lines);
                        splits = lines.split(" - ");
                        product = new Product();
                        product.setIdSupplier(Long.valueOf(splits[0]));
                        product.setImage(splits[1]);
                        product.setName(splits[2]);
                        product.setRef(splits[3]);
                        product.setQuantity(Integer.valueOf(splits[4]));
                        product.setPrice(Double.valueOf(splits[5]));
                        listProduct.add(product);
                    }
                    buffreader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                addAll(listProduct);
            }else{
                Log.i("AppInfo", "Jai pas trouve le fichier");
            }
    }

    @Override
    public boolean create(Product obj) {
        if(isExist(obj) == false){
            ContentValues values = new ContentValues();
            values.put(ShuttlesSchema.Product.PRODUCT_NAME, obj.getName());
            values.put(ShuttlesSchema.Product.PRODUCT_REFERENCE, obj.getRef());
            values.put(ShuttlesSchema.Product.PRODUCT_STOCK, obj.getQuantity());
            values.put(ShuttlesSchema.Product.PRODUCT_PRICE, obj.getPrice());
            values.put(ShuttlesSchema.Product.PRODUCT_SUPPLIER_ID, obj.getIdSupplier());
            values.put(ShuttlesSchema.Product.PRODUCT_IMAGE, obj.getImage());
            getDBWrite().insert(ShuttlesSchema.Product.PRODUCT_TABLE_NAME, null, values);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Product fetchById(long id) {
        String query = "SELECT " +
                ShuttlesSchema.Product.PRODUCT_NAME + "," +
                ShuttlesSchema.Product.PRODUCT_REFERENCE + "," +
                ShuttlesSchema.Product.PRODUCT_STOCK  + "," +
                ShuttlesSchema.Product.PRODUCT_PRICE + "," +
                ShuttlesSchema.Product.PRODUCT_SUPPLIER_ID + "," +
                ShuttlesSchema.Product.PRODUCT_IMAGE +"," +
                ShuttlesSchema.Supplier.SUPPLIER_NAME +
                " FROM " +
                ShuttlesSchema.Product.PRODUCT_TABLE_NAME +
                " INNER JOIN " +
                ShuttlesSchema.Supplier.SUPPLIER_TABLE_NAME +
                " ON " +
                ShuttlesSchema.Supplier.SUPPLIER_ID + " = " +  ShuttlesSchema.Product.PRODUCT_SUPPLIER_ID +
                " WHERE " +
                ShuttlesSchema.Product.PRODUCT_ID + " = ? ";

        Cursor c = getDBRead().rawQuery( query,  new String[]{Long.toString(id)});

        if(c != null){
            c.moveToFirst();
            Product product = new Product();
            product.setId(id);
            product.setName(c.getString(0));
            product.setRef(c.getString(1));
            product.setQuantity(c.getInt(2));
            product.setPrice(c.getDouble(3));
            product.setIdSupplier(c.getLong(4));
            product.setImage(c.getString(5));
            product.setSupplierName(c.getString(6));
            c.close();
            return product;
        }else{
            return null;
        }
    }

    @Override
    public List<Product> fetchAll() {
        String query = "SELECT " +
                ShuttlesSchema.Product.PRODUCT_ID + "," +
                ShuttlesSchema.Product.PRODUCT_NAME + "," +
                ShuttlesSchema.Product.PRODUCT_REFERENCE + "," +
                ShuttlesSchema.Product.PRODUCT_STOCK  + "," +
                ShuttlesSchema.Product.PRODUCT_PRICE + "," +
                ShuttlesSchema.Product.PRODUCT_SUPPLIER_ID + "," +
                ShuttlesSchema.Product.PRODUCT_IMAGE +"," +
                ShuttlesSchema.Supplier.SUPPLIER_NAME +
                " FROM " +
                ShuttlesSchema.Product.PRODUCT_TABLE_NAME +
                " INNER JOIN " +
                ShuttlesSchema.Supplier.SUPPLIER_TABLE_NAME +
                " ON " +
                ShuttlesSchema.Supplier.SUPPLIER_ID + " = " +  ShuttlesSchema.Product.PRODUCT_SUPPLIER_ID;
        Cursor c = getDBRead().rawQuery(query, null);
        Product product;
        List<Product> listProduct = new ArrayList<>();
        if(c != null){
            while(c.moveToNext()){
                product = new Product();

                product.setId(c.getLong(0));
                product.setName(c.getString(1));
                product.setRef(c.getString(2));
                product.setQuantity(c.getInt(3));
                product.setPrice(c.getDouble(4));
                product.setIdSupplier(c.getLong(5));
                product.setImage(c.getString(6));
                product.setSupplierName(c.getString(7));

                Log.i("AppInfoProduct", product.toString());
                listProduct.add(product);
            }
            c.close();
            return listProduct;
        }else{
            return null;
        }
    }

    @Override
    public boolean addAll(List<Product> obj) {
        return super.addAll(obj);
    }

    @Override
    public boolean update(Product obj) {
        if(isExist(obj) == true){
            ContentValues values = new ContentValues();
            values.put(ShuttlesSchema.Product.PRODUCT_NAME, obj.getName());
            values.put(ShuttlesSchema.Product.PRODUCT_REFERENCE, obj.getRef());
            values.put(ShuttlesSchema.Product.PRODUCT_STOCK, obj.getQuantity());
            values.put(ShuttlesSchema.Product.PRODUCT_PRICE, obj.getPrice());
            values.put(ShuttlesSchema.Product.PRODUCT_SUPPLIER_ID, obj.getIdSupplier());
            values.put(ShuttlesSchema.Product.PRODUCT_IMAGE, obj.getImage());
            getDBWrite().update(ShuttlesSchema.Product.PRODUCT_TABLE_NAME, values, ShuttlesSchema.Product.PRODUCT_ID + " = ? ", new String[]{Long.toString(obj.getId())});
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        return getDBWrite().delete(ShuttlesSchema.Product.PRODUCT_TABLE_NAME, null, null)>0;
    }

    @Override
    public boolean delete(long id) {
        return getDBWrite().delete(ShuttlesSchema.Product.PRODUCT_TABLE_NAME, ShuttlesSchema.Product.PRODUCT_ID + " = ? ", new String[] {Long.toString(id)})>0;
    }

    public List<String> getListProductsName(){
        String query = "SELECT " +
                ShuttlesSchema.Product.PRODUCT_NAME +
                " FROM " +
                ShuttlesSchema.Product.PRODUCT_TABLE_NAME +
                " ORDER BY " + ShuttlesSchema.Product.PRODUCT_NAME + " ASC";
        Cursor c = getDBRead().rawQuery(query, null);
        List<String> listProductsName = new ArrayList<>();
        if(c != null){
            while(c.moveToNext()){
                listProductsName.add(c.getString(0));
            }
            c.close();
            return listProductsName;
        }else{
            return null;
        }
    }

    @Override
    public long fetchByName(String name) {
        return 0;
    }

    public long fetchByNameAndRef(String name, String ref){
        String query = "SELECT " +
                ShuttlesSchema.Product.PRODUCT_ID +
                " FROM " +
                ShuttlesSchema.Product.PRODUCT_TABLE_NAME +
                " WHERE " +
                ShuttlesSchema.Product.PRODUCT_NAME + " =  ? and " +
                ShuttlesSchema.Product.PRODUCT_REFERENCE + " = ? ";
        Cursor c = getDBRead().rawQuery(query, new String[]{name, ref});
        c.moveToNext();
        long id = c.getLong(0);
        return id;
    }

    public List<String> getListProductsRef(String name){
        String query = "SELECT " +
                ShuttlesSchema.Product.PRODUCT_REFERENCE +
                " FROM " +
                ShuttlesSchema.Product.PRODUCT_TABLE_NAME+
                " WHERE " +
                ShuttlesSchema.Product.PRODUCT_NAME + " =  ? " +
                " ORDER BY " + ShuttlesSchema.Product.PRODUCT_REFERENCE +" ASC";
        Cursor c = getDBRead().rawQuery(query, new String[]{name});
        List<String> listProductsRef = new ArrayList<>();
        if(c != null){
            while(c.moveToNext()){
                listProductsRef.add(c.getString(0));
            }
            c.close();
            return listProductsRef;
        }else{
            return null;
        }
    }

    public String getProductRef(long id){
        Product product = fetchById(id);
        return product.getRef();

    }

    public boolean decreaseQuantity(Long id, int quantity){
        Product product = fetchById(id);
        if(product.getQuantity()>=quantity){
            product.setQuantity(product.getQuantity()-quantity);
            return update(product);
        }else{
            return false;
        }
    }

    public boolean increaseQuantity(long id, int quantity){
        Product product = fetchById(id);
        product.setQuantity(product.getQuantity()+ quantity);
        return update(product);
    }

    public boolean modifyQuantity(long id, int newQuantity, int oldQuantity){
        if(increaseQuantity(id, oldQuantity)==true){
            return decreaseQuantity(id, newQuantity);
        }else{
            return false;
        }
    }
}
