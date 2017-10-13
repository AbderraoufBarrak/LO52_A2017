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

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.graphics.drawable.Drawable;
import android.provider.BaseColumns;


/**
 * Represents one record of the Volant table.
 */
@Entity(tableName = Volant.TABLE_NAME)
public class Volant {

    /** The name of the Volant table. */
    public static final String TABLE_NAME = "volants";

    /** The name of the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;

    /** The name of the name column. */
    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_IMAGE = "image";

    /** The unique ID of the volant. */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    /** The name of the volant. */
    @ColumnInfo(name = COLUMN_NAME)
    public String name;

    @ColumnInfo(name = COLUMN_IMAGE)
    public int image;

    /**
     * Create a new {@link Volant} from the specified {@link ContentValues}.
     *
     * @param values A {@link ContentValues} that at least contain {@link #COLUMN_NAME}.
     * @return A newly created {@link Volant} instance.
     */
    public static Volant fromContentValues(ContentValues values) {
        final Volant volant = new Volant();
        if (values.containsKey(COLUMN_ID)) {
            volant.id = values.getAsLong(COLUMN_ID);
        }
        if (values.containsKey(COLUMN_NAME)) {
            volant.name = values.getAsString(COLUMN_NAME);
        }
        if (values.containsKey(COLUMN_IMAGE)) {
            volant.image = values.getAsInteger(COLUMN_IMAGE);
        }
        return volant;
    }

    /** Dummy data. */
    static final String[] VOLANTS = {
            "Test 1", "Test 2"
    };

}
