package com.example.android.contentprovidersample.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.provider.BaseColumns;

import java.util.Date;

@Entity(tableName = Historique.TABLE_NAME,
        foreignKeys = @ForeignKey(entity = Volant.class,
        parentColumns = BaseColumns._ID,
        childColumns = "volant_id"))
public class Historique {

    public static final String TABLE_NAME = "historique";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_VOLANT_ID = "volant_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_WHO = "who";
    public static final String COLUMN_QUANTITY = "quantity";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    @ColumnInfo(name = COLUMN_VOLANT_ID)
    public long volant_id;

    @ColumnInfo(name = COLUMN_DATE)
    public Date date;

    @ColumnInfo(name = COLUMN_WHO)
    public String who;

    @ColumnInfo(name = COLUMN_QUANTITY)
    public String quantity;

    public static Historique fromContentValues(ContentValues values) {
        final Historique historique = new Historique();
        if (values.containsKey(COLUMN_ID)) {
            historique.id = values.getAsLong(COLUMN_ID);
        }
        if (values.containsKey(COLUMN_VOLANT_ID)) {
            historique.volant_id = values.getAsLong(COLUMN_VOLANT_ID);
        }
        if (values.containsKey(COLUMN_DATE)) {
            Object date = values.get(COLUMN_DATE);
            if (date.getClass().isAssignableFrom(Date.class)) {
              historique.date = (Date) date;
            }
        }
        if (values.containsKey(COLUMN_WHO)){
            historique.who = values.getAsString(COLUMN_WHO);
        }
        if (values.containsKey(COLUMN_QUANTITY)){
            historique.quantity = values.getAsString(COLUMN_QUANTITY);
        }
        return historique;
    }
}
