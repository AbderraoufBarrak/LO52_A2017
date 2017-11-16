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

package com.example.android.contentprovidersample.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android.contentprovidersample.data.Historique;
import com.example.android.contentprovidersample.data.HistoriqueDao;
import com.example.android.contentprovidersample.data.StarLordDatabase;
import com.example.android.contentprovidersample.data.Volant;
import com.example.android.contentprovidersample.data.VolantDao;

import java.util.ArrayList;


/**
 * A {@link ContentProvider} based on a Room database.
 *
 * <p>Note that you don't need to implement a ContentProvider unless you want to expose the data
 * outside your process or your application already uses a ContentProvider.</p>
 */
public class SampleContentProvider extends ContentProvider {

    /** The authority of this content provider. */
    public static final String AUTHORITY = "com.example.android.contentprovidersample.provider";

    /** The URI for the Volant table. */
    public static final Uri URI_VOLANT = Uri.parse(
            "content://" + AUTHORITY + "/" + Volant.TABLE_NAME);

    /** The URI for the Historique table. */
    public static final Uri URI_HISTORIQUE = Uri.parse(
            "content://" + AUTHORITY + "/" + Historique.TABLE_NAME);

    /** The match code for some items in the Volant table. */
    private static final int CODE_VOLANT_DIR = 1;

    /** The match code for an item in the Volant table. */
    private static final int CODE_VOLANT_ITEM = 2;

    /** The match code for some items in the Volant table. */
    private static final int CODE_HISTORIQUE_DIR = 3;

    /** The match code for an item in the Volant table. */
    private static final int CODE_HISTORIQUE_ITEM = 4;

    /** The URI matcher. */
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, Volant.TABLE_NAME, CODE_VOLANT_DIR);
        MATCHER.addURI(AUTHORITY, Volant.TABLE_NAME + "/*", CODE_VOLANT_ITEM);
        MATCHER.addURI(AUTHORITY, Historique.TABLE_NAME, CODE_HISTORIQUE_DIR);
        MATCHER.addURI(AUTHORITY, Historique.TABLE_NAME + "/*", CODE_HISTORIQUE_ITEM);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
            @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = MATCHER.match(uri);
        if (code == CODE_VOLANT_DIR || code == CODE_VOLANT_ITEM) {
            final Context context = getContext();
            if (context == null) {
                return null;
            }
            VolantDao volant = StarLordDatabase.getInstance(context).volant();
            final Cursor cursor;
            if (code == CODE_VOLANT_DIR) {
                cursor = volant.selectAll();
            } else {
                cursor = volant.selectById(ContentUris.parseId(uri));
            }
            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        } else if (code == CODE_HISTORIQUE_DIR || code == CODE_HISTORIQUE_ITEM) {
            final Context context = getContext();
            if (context == null) {
                return null;
            }
            HistoriqueDao historique = StarLordDatabase.getInstance(context).historique();
            final Cursor cursor;
            if (code == CODE_HISTORIQUE_DIR) {
                cursor = historique.selectAll();
            } else {
                cursor = historique.selectById(ContentUris.parseId(uri));
            }
            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_VOLANT_DIR:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + Volant.TABLE_NAME;
            case CODE_VOLANT_ITEM:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + Volant.TABLE_NAME;
            case CODE_HISTORIQUE_DIR:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + Historique.TABLE_NAME;
            case CODE_HISTORIQUE_ITEM:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + Historique.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match_uri = MATCHER.match(uri);
        switch (match_uri) {
            case CODE_VOLANT_DIR:
            case CODE_HISTORIQUE_DIR:
                final Context context = getContext();
                if (context == null) {
                    return null;
                }
                switch (match_uri) {
                    case CODE_VOLANT_DIR:
                        final long volant_id = StarLordDatabase.getInstance(context).volant()
                                .insert(Volant.fromContentValues(values));
                        context.getContentResolver().notifyChange(uri, null);
                        return ContentUris.withAppendedId(uri, volant_id);
                    case CODE_HISTORIQUE_DIR:
                        final long historique_id = StarLordDatabase.getInstance(context).volant()
                                .insert(Volant.fromContentValues(values));
                        context.getContentResolver().notifyChange(uri, null);
                        return ContentUris.withAppendedId(uri, historique_id);
                }
            case CODE_VOLANT_ITEM :
            case CODE_HISTORIQUE_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
            @Nullable String[] selectionArgs) {
        final int match_uri = MATCHER.match(uri);
        switch (match_uri) {
            case CODE_VOLANT_DIR:
            case CODE_HISTORIQUE_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_VOLANT_ITEM:
            case CODE_HISTORIQUE_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                switch (match_uri) {
                    case CODE_VOLANT_ITEM:
                        final int volant_count = StarLordDatabase.getInstance(context).volant()
                                .deleteById(ContentUris.parseId(uri));
                        context.getContentResolver().notifyChange(uri, null);
                        return volant_count;
                    case CODE_HISTORIQUE_ITEM:
                        final int historique_count = StarLordDatabase.getInstance(context).historique()
                                .deleteById(ContentUris.parseId(uri));
                        context.getContentResolver().notifyChange(uri, null);
                        return historique_count;
                }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
            @Nullable String[] selectionArgs) {
        final int match_uri = MATCHER.match(uri);
        switch (match_uri) {
            case CODE_VOLANT_DIR:
            case CODE_HISTORIQUE_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_VOLANT_ITEM:
            case CODE_HISTORIQUE_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                switch (match_uri) {
                    case CODE_VOLANT_ITEM:
                        final Volant volant = Volant.fromContentValues(values);
                        volant.id = ContentUris.parseId(uri);
                        final int volant_count = StarLordDatabase.getInstance(context).volant()
                                .update(volant);
                        context.getContentResolver().notifyChange(uri, null);
                        return volant_count;
                    case CODE_HISTORIQUE_ITEM:
                        final Historique historique = Historique.fromContentValues(values);
                        historique.id = ContentUris.parseId(uri);
                        final int historique_count = StarLordDatabase.getInstance(context).historique()
                                .update(historique);
                        context.getContentResolver().notifyChange(uri, null);
                        return historique_count;
                }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(
            @NonNull ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        final Context context = getContext();
        if (context == null) {
            return new ContentProviderResult[0];
        }
        final StarLordDatabase database = StarLordDatabase.getInstance(context);
        database.beginTransaction();
        try {
            final ContentProviderResult[] result = super.applyBatch(operations);
            database.setTransactionSuccessful();
            return result;
        } finally {
            database.endTransaction();
        }
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] valuesArray) {
        final int match_uri = MATCHER.match(uri);
        switch (match_uri) {
            case CODE_VOLANT_DIR:
            case CODE_HISTORIQUE_DIR:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                final StarLordDatabase database = StarLordDatabase.getInstance(context);
                switch (match_uri) {
                    case CODE_VOLANT_DIR:
                        final Volant[] volants = new Volant[valuesArray.length];
                        for (int i = 0; i < valuesArray.length; i++) {
                            volants[i] = Volant.fromContentValues(valuesArray[i]);
                        }
                        return database.volant().insertAll(volants).length;
                    case CODE_HISTORIQUE_DIR:
                        final Historique[] historiques = new Historique[valuesArray.length];
                        for (int i = 0; i < valuesArray.length; i++) {
                            historiques[i] = Historique.fromContentValues(valuesArray[i]);
                        }
                        return database.historique().insertAll(historiques).length;
                }
            case CODE_VOLANT_ITEM:
            case CODE_HISTORIQUE_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

}
