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

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;


/**
 * Data access object for Volant.
 */
@Dao
public interface VolantDao {

    /**
     * Counts the number of cheeses in the table.
     *
     * @return The number of cheeses.
     */
    @Query("SELECT COUNT(*) FROM " + Volant.TABLE_NAME)
    int count();

    /**
     * Inserts a volant into the table.
     *
     * @param volant A new volant.
     * @return The row ID of the newly inserted volant.
     */
    @Insert
    long insert(Volant volant);

    /**
     * Inserts multiple volants into the database
     *
     * @param volants An array of new volants.
     * @return The row IDs of the newly inserted volants.
     */
    @Insert
    long[] insertAll(Volant[] volants);

    /**
     * Select all cheeses.
     *
     * @return A {@link Cursor} of all the cheeses in the table.
     */
    @Query("SELECT * FROM " + Volant.TABLE_NAME)
    Cursor selectAll();

    /**
     * Select a volant by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected volant.
     */
    @Query("SELECT * FROM " + Volant.TABLE_NAME + " WHERE " + Volant.COLUMN_ID + " = :id")
    Cursor selectById(long id);

    /**
     * Delete a volant by the ID.
     *
     * @param id The row ID.
     * @return A number of cheeses deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + Volant.TABLE_NAME + " WHERE " + Volant.COLUMN_ID + " = :id")
    int deleteById(long id);

    /**
     * Update the volant. The volant is identified by the row ID.
     *
     * @param volant The volant to update.
     * @return A number of cheeses updated. This should always be {@code 1}.
     */
    @Update
    int update(Volant volant);

}
