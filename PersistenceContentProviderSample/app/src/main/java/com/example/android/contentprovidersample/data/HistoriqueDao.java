package com.example.android.contentprovidersample.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

@Dao
public interface HistoriqueDao {

    @Query("SELECT COUNT(*) FROM " + Historique.TABLE_NAME)
    int count();

    @Insert
    long insert(Historique historique);

    @Insert
    long[] insertAll(Historique[] historique);

    @Query("SELECT * FROM " + Historique.TABLE_NAME)
    Cursor selectAll();

    @Query("SELECT * FROM " + Historique.TABLE_NAME + " WHERE " + Historique.COLUMN_ID + " = :id")
    Cursor selectById(long id);

    @Query("DELETE FROM " + Historique.TABLE_NAME + " WHERE " + Historique.COLUMN_ID + " = :id")
    int deleteById(long id);

    @Query("SELECT * FROM " + Historique.TABLE_NAME
            + " INNER JOIN " + Volant.TABLE_NAME
            + " ON " + Historique.TABLE_NAME + "." + Historique.COLUMN_VOLANT_ID
            + " = " + Volant.TABLE_NAME + "." + Volant.COLUMN_ID
            + " WHERE " + Volant.TABLE_NAME + "." + Volant.COLUMN_ID + " = :id")
    Cursor selectByVolantId(long id);

    @Update
    int update(Historique historique);

}