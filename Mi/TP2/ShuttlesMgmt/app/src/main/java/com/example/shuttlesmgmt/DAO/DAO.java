package com.example.shuttlesmgmt.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.db.DBHandler;
import com.example.shuttlesmgmt.db.ShuttlesSchema;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Michel on 2017/10/31.
 */

public abstract class DAO<T> {

    protected SQLiteDatabase sqldb = null;
    protected DBHandler dbhandler = null;

    public DAO(Context c){
        this.dbhandler = new DBHandler(c, null);
    }

    public SQLiteDatabase openRead(){
        this.sqldb = dbhandler.getReadableDatabase();
        return sqldb;
    }

    public SQLiteDatabase openWrite(){
        this.sqldb = dbhandler.getWritableDatabase();
        return sqldb;
    }

    public SQLiteDatabase getDBRead(){
        return openRead();
    }

    public SQLiteDatabase getDBWrite(){
        return openWrite();
    }

    public void close(){
        this.sqldb.close();
    }

    public SQLiteDatabase getDB(){
        return sqldb;
    }

    /**
     * test if the obj exists
     * @param obj
     * @return
     */
    public abstract boolean isExist(T obj);

    /**
     * create or add a new obj
     * @param obj
     * @return
     */
    public abstract boolean create(T obj);

    /**
     * find by id
     * @param id of T
     * @return
     */
    public abstract T fetchById(long id);

    /**
     * get all obj
     * @return
     */
    public abstract List<T> fetchAll();

    /**
     * add or create all obj
     * @param obj a list
     * @return
     */
    public boolean addAll(List<T> obj){
        int i = obj.size();
        int i_temp = 0;
        for(T temp : obj){
            i_temp++;
            create(temp);
        }
        if(i_temp == i){
            return true;
        }else{
            return false;
        }
    }

    /**
     * update the values of the obj
     * @param obj
     * @return
     */
    public abstract boolean update(T obj);

    /**
     * delete all obj of the table
     * @return
     */
    public abstract boolean deleteAll();

    /**
     * delete a obj by id
     * @param id of the obj
     * @return
     */
    public abstract boolean delete(long id);

    /**
     * to know if is it empty
     * @return
     */
    public abstract boolean isEmpty();

    /**
     * read the data in a txt file
     * @param datafile path of the file
     * @param c context
     */
    public abstract void readData(int datafile, Context c);
}
