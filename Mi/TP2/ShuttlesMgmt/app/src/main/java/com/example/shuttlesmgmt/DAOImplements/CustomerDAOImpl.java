package com.example.shuttlesmgmt.DAOImplements;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shuttlesmgmt.DAO.DAO;
import com.example.shuttlesmgmt.db.ShuttlesSchema;
import com.example.shuttlesmgmt.entity.Customer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 2017/10/31.
 */

public class CustomerDAOImpl extends DAO<Customer> {

    public CustomerDAOImpl(Context c){
        super(c);
    }

    @Override
    public SQLiteDatabase openRead() {
        return super.openRead();
    }

    @Override
    public SQLiteDatabase openWrite(){
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
    public boolean isExist(Customer obj) {
        Cursor c = getDBRead().rawQuery(
                "SELECT * FROM " + ShuttlesSchema.Customer.CUSTOMER_TABLE_NAME,
                null
        );
        if(c != null){
            while(c.moveToNext()){
                if(c.getString(1).contentEquals(obj.getName()) && c.getString(2).contentEquals(obj.getAdd())){
                    c.close();
                    return true;
                }
            }
        }
        c.close();
        return false;
    }

    @Override
    public boolean create(Customer obj) {
        if(isExist(obj) == false){
            ContentValues values = new ContentValues();
            values.put(ShuttlesSchema.Customer.CUSTOMER_NAME, obj.getName());
            values.put(ShuttlesSchema.Customer.CUSTOMER_ADDRESSE, obj.getAdd());
            values.put(ShuttlesSchema.Customer.CUSTOMER_PHONE, obj.getPhone());
            getDBWrite().insert(ShuttlesSchema.Customer.CUSTOMER_TABLE_NAME, null, values);
            //Log.i("AppInfo", "Obj created successfull");
            return true;
        }else{
            Log.i("AppInfo", "Can't create the obj because already existed");
            return false;
        }
    }

    @Override
    public Customer fetchById(long id) {
        Cursor c = getDBRead().rawQuery(
                "SELECT * FROM " +
                        ShuttlesSchema.Customer.CUSTOMER_TABLE_NAME +
                        " WHERE " + ShuttlesSchema.Customer.CUSTOMER_ID + " = ? ",
                new String[]{Long.toString(id)});

        if(c != null){
            c.moveToFirst();
            Customer customer = new Customer();
            customer.setId(c.getLong(0));
            customer.setAdd(c.getString(2));
            customer.setName(c.getString(1));
            customer.setPhone(c.getString(3));
            c.close();
            return customer;
        }else{
            return null;
        }
    }

    @Override
    public List<Customer> fetchAll() {
        Cursor c = getDBRead().rawQuery(
                "SELECT * FROM " +
                        ShuttlesSchema.Customer.CUSTOMER_TABLE_NAME,
                null);
        Customer customer;
        List<Customer> listCustomer = new ArrayList<>();
        if(c != null){
            while(c.moveToNext()){
                customer = new Customer(c.getLong(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3)
                );
                Log.i("AppInfoCustomer", customer.toString());
                listCustomer.add(customer);
            }
            c.close();
            return listCustomer;
        }else{
            return null;
        }
    }

    @Override
    public boolean addAll(List<Customer> obj) {
        return super.addAll(obj);
    }

    @Override
    public boolean update(Customer obj) {
        if(isExist(obj) == false){
            ContentValues values = new ContentValues();
            values.put(ShuttlesSchema.Customer.CUSTOMER_NAME, obj.getName());
            values.put(ShuttlesSchema.Customer.CUSTOMER_ADDRESSE, obj.getAdd());
            values.put(ShuttlesSchema.Customer.CUSTOMER_PHONE, obj.getPhone());
            getDBWrite().update(ShuttlesSchema.Customer.CUSTOMER_TABLE_NAME, values, ShuttlesSchema.Customer.CUSTOMER_ID + " = ? ", new String[]{Long.toString(obj.getId())});
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        return getDBWrite().delete(ShuttlesSchema.Customer.CUSTOMER_TABLE_NAME, null, null)>0;
    }

    @Override
    public boolean delete(long id) {
        return getDBWrite().delete(ShuttlesSchema.Customer.CUSTOMER_TABLE_NAME, ShuttlesSchema.Customer.CUSTOMER_ID + " = ? ", new String[] {Long.toString(id)})>0;
    }

    @Override
    public boolean isEmpty() {
        Cursor c = getDBRead().rawQuery(
                "SELECT COUNT ( " +
                        ShuttlesSchema.Customer.CUSTOMER_ID +
                        " ) FROM " +
                        ShuttlesSchema.Customer.CUSTOMER_TABLE_NAME,
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
            List<Customer> listCustomer = new ArrayList<>();
            Customer customer;
            String[] splits;
            String lines;

            if(inputstream != null){
                //Log.i("AppInfo", "J'ai trouve le fichier " + Integer.toString(datafile));
                InputStreamReader inputreader = new InputStreamReader(inputstream);
                BufferedReader buffreader = new BufferedReader(inputreader);

                try{
                    while((lines = buffreader.readLine()) != null){
                        Log.i("AppInfo", "Line :" + lines);
                        splits = lines.split(" - ");
                        customer = new Customer();
                        customer.setPhone(splits[2]);
                        customer.setAdd(splits[1]);
                        customer.setName(splits[0]);
                        listCustomer.add(customer);
                    }
                    buffreader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                addAll(listCustomer);
            }else{
                Log.i("AppInfo", "Jai pas trouve le fichier " + Integer.toString(datafile));
            }
        }

        public List<String> getListCustomersName(){
            String query = "SELECT " +
                    ShuttlesSchema.Customer.CUSTOMER_NAME +
                    " FROM " +
                    ShuttlesSchema.Customer.CUSTOMER_TABLE_NAME +
                    " ORDER BY "+ ShuttlesSchema.Customer.CUSTOMER_NAME + " ASC";
            Cursor c = getDBRead().rawQuery(query, null);
            List<String> listCustomersName = new ArrayList<>();
            if(c != null){
                while(c.moveToNext()){
                    listCustomersName.add(c.getString(0));
                }
                c.close();
                return listCustomersName;
            }else{
                return null;
            }
        }

    @Override
    public long fetchByName(String name) {
        String query = "SELECT " +
                ShuttlesSchema.Customer.CUSTOMER_ID +
                " FROM " +
                ShuttlesSchema.Customer.CUSTOMER_TABLE_NAME +
                " WHERE " +
                ShuttlesSchema.Customer.CUSTOMER_NAME + " =  ? ";
        Cursor c = getDBRead().rawQuery(query, new String[]{name});
        if(c != null){
            c.moveToNext();
            Long id = c.getLong(0);
            c.close();
            return id;

        }else{
            return 0;
        }
    }
}
