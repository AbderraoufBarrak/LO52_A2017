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

    @Update
    int update(Historique historique);

}