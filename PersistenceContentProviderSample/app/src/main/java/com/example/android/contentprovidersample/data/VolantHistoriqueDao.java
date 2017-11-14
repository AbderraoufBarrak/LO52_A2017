package com.example.android.contentprovidersample.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

@Dao
public interface VolantHistoriqueDao {

    @Query("SELECT * FROM " + Historique.TABLE_NAME
            + " INNER JOIN " + Volant.TABLE_NAME
            + " ON " + Historique.TABLE_NAME + "." + Historique.COLUMN_VOLANT_ID
            + " = " + Volant.TABLE_NAME + "." + Volant.COLUMN_ID
            + " WHERE " + Volant.TABLE_NAME + "." + Volant.COLUMN_ID + " = :id")
    Cursor selectByVolantId(long id);

}
