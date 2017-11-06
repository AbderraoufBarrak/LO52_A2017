package com.example.shuttlesmgmt.DAOImplements;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shuttlesmgmt.DAO.DAO;
import com.example.shuttlesmgmt.db.ShuttlesSchema;
import com.example.shuttlesmgmt.entity.Supplier;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 2017/10/31.
 */

public class SupplierDAOImpl extends DAO<Supplier> {

    public SupplierDAOImpl(Context c) {
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
    public boolean isExist(Supplier obj) {

        Cursor c = getDBRead().rawQuery(
                "SELECT * FROM " + ShuttlesSchema.Supplier.SUPPLIER_TABLE_NAME,
                null
        );
        if(c != null){
            while(c.moveToNext()){
                if(c.getString(1).contentEquals(obj.getName())&& c.getString(2).contentEquals(obj.getAdd())){
                    c.close();
                    return true;
                }
            }
        }
        c.close();
        return false;
    }

    @Override
    public boolean create(Supplier obj) {
        if(isExist(obj) == false){
            ContentValues values = new ContentValues();
            values.put(ShuttlesSchema.Supplier.SUPPLIER_NAME, obj.getName());
            values.put(ShuttlesSchema.Supplier.SUPPLIER_ADDRESSE, obj.getAdd());
            values.put(ShuttlesSchema.Supplier.SUPPLIER_PHONE, obj.getPhone());
            getDBWrite().insert(ShuttlesSchema.Supplier.SUPPLIER_TABLE_NAME, null, values);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Supplier fetchById(long id) {

        Cursor c = getDBRead().rawQuery(
                "SELECT * FROM " +
                        ShuttlesSchema.Supplier.SUPPLIER_TABLE_NAME +
                        " WHERE " +
                        ShuttlesSchema.Supplier.SUPPLIER_ID + " = ? ",
                new String[]{Long.toString(id)});

        if(c != null){
            c.moveToFirst();
            Supplier supplier = new Supplier();
            supplier.setId(c.getLong(0));
            supplier.setAdd(c.getString(2));
            supplier.setName(c.getString(1));
            supplier.setPhone(c.getString(3));
            c.close();
            return supplier;
        }else{
            return null;
        }
    }

    @Override
    public List<Supplier> fetchAll() {
        Cursor c = getDBRead().rawQuery(
                "SELECT * FROM " +
                        ShuttlesSchema.Supplier.SUPPLIER_TABLE_NAME,
                null);
        Supplier supplier;
        List<Supplier> listSupplier = new ArrayList<>();
        if(c != null){
            while(c.moveToNext()){
                supplier = new Supplier(c.getLong(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3)
                );
                Log.i("AppInfoSupplier", supplier.toString());
                listSupplier.add(supplier);
            }
            c.close();
            return listSupplier;
        }else{
            return null;
        }
    }

    @Override
    public boolean addAll(List<Supplier> obj) {
        return super.addAll(obj);
    }

    @Override
    public boolean isEmpty() {
        Cursor c = getDBRead().rawQuery(
                "SELECT COUNT ( " +
                        ShuttlesSchema.Supplier.SUPPLIER_ID +
                        " ) FROM " +
                        ShuttlesSchema.Supplier.SUPPLIER_TABLE_NAME,
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
            List<Supplier> listSupplier = new ArrayList<>();
            Supplier supplier;
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
                        supplier = new Supplier();
                        supplier.setPhone(splits[2]);
                        supplier.setAdd(splits[1]);
                        supplier.setName(splits[0]);
                        listSupplier.add(supplier);
                    }
                    buffreader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                addAll(listSupplier);
            }else{
                Log.i("AppInfo", "Jai pas trouve le fichier");
            }
    }

    @Override
    public boolean update(Supplier obj) {

        if(isExist(obj) == false){
            ContentValues values = new ContentValues();
            values.put(ShuttlesSchema.Supplier.SUPPLIER_NAME, obj.getName());
            values.put(ShuttlesSchema.Supplier.SUPPLIER_ADDRESSE, obj.getAdd());
            values.put(ShuttlesSchema.Supplier.SUPPLIER_PHONE, obj.getPhone());
            getDBWrite().update(ShuttlesSchema.Supplier.SUPPLIER_TABLE_NAME, values, ShuttlesSchema.Supplier.SUPPLIER_ID + " =  ? ", new String[]{Long.toString(obj.getId())});
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        return getDBWrite().delete(ShuttlesSchema.Supplier.SUPPLIER_TABLE_NAME, null, null)>0;
    }

    @Override
    public boolean delete(long id) {
        return getDBWrite().delete(ShuttlesSchema.Supplier.SUPPLIER_TABLE_NAME, ShuttlesSchema.Supplier.SUPPLIER_ID + " = ? ", new String[] {Long.toString(id)})>0;
    }

    public List<String> getListSuppliersName(){
        String query = "SELECT " +
                ShuttlesSchema.Supplier.SUPPLIER_NAME +
                " FROM " +
                ShuttlesSchema.Supplier.SUPPLIER_TABLE_NAME +
                " ORDER BY "+ ShuttlesSchema.Supplier.SUPPLIER_NAME +" ASC";
        Cursor c = getDBRead().rawQuery(query, null);
        List<String> listSupplier = new ArrayList<>();
        if(c != null){
            while(c.moveToNext()){
                listSupplier.add(c.getString(0));
            }
            c.close();
            return listSupplier;
        }else{
            return null;
        }
    }

    @Override
    public long fetchByName(String name) {
        String query = "SELECT " +
                ShuttlesSchema.Supplier.SUPPLIER_ID +
                " FROM " +
                ShuttlesSchema.Supplier.SUPPLIER_TABLE_NAME +
                " WHERE " +
                ShuttlesSchema.Supplier.SUPPLIER_NAME + " =  ? ";
        Cursor c = getDBRead().rawQuery(query, new String[]{name});
        if(c != null){
            c.moveToNext();
            long id = c.getLong(0);
            return id;

        }else{
            return 0;
        }
    }
}
