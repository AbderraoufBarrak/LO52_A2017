/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.contentprovidersample.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import java.util.Calendar;

/**
 * The Room database.
 */
@Database(entities = {Volant.class, Historique.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class SampleDatabase extends RoomDatabase {

    /**
     * @return The DAO for the Volant table.
     */
    @SuppressWarnings("WeakerAccess")
    public abstract VolantDao volant();

    @SuppressWarnings("WeakerAccess")
    public abstract HistoriqueDao historique();

    /** The only instance */
    private static SampleDatabase sInstance;

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */
    public static synchronized SampleDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), SampleDatabase.class, "ex")
                    .build();
            sInstance.populateInitialData();
        }
        return sInstance;
    }

    /**
     * Switches the internal implementation with an empty in-memory database.
     *
     * @param context The context.
     */
    @VisibleForTesting
    public static void switchToInMemory(Context context) {
        sInstance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                SampleDatabase.class).build();
    }

    /**
     * Inserts the dummy data into the database if it is currently empty.
     */
    private void populateInitialData() {
        if (volant().count() == 0) {
            Volant volant = new Volant();
            Historique historique = new Historique();
            beginTransaction();
            try {
                for (int i = 0; i < Volant.VOLANTS_NOMS.length; i++) {
                    volant.name = Volant.VOLANTS_NOMS[i];
                    volant.image = Volant.VOLANTS_IMAGES[i];
                    volant.prix = Volant.VOLANTS_PRIX[i];
                    volant().insert(volant);
                    historique.date = Calendar.getInstance().getTime();
                    historique.volant_id = volant().getLastId();
                    historique().insert(historique);
                }
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

}
