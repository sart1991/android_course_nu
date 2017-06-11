package com.exercises.sart1991.evaluacionfinal8p.data.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by sart1 on 6/3/2017.
 */

public class ProviderContainer extends ContentProvider {

    private static final String PROVIDER_NAME =
            "com.exercises.sart1991.evaluacionfinal8p.data.provider.ProviderContainer";
    private SQLiteDatabase db;
    private static final String URL = "content://" + PROVIDER_NAME + "/cte";
    private static final Uri CONTENT_URI = Uri.parse(URL);
    private static final int uriCode = 1;
    private static final UriMatcher uriMatcher;
    private static HashMap<String, String> values;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "cte", uriCode);
        uriMatcher.addURI(PROVIDER_NAME, "cte/*", uriCode);
    }

    @Override
    public boolean onCreate() {

        DbHelper dbHelper = new DbHelper(getContext());
        db = dbHelper.getWritableDatabase();
        return db != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DbHelper.getTableName());

        switch (uriMatcher.match(uri)) {
            case uriCode:
                builder.setProjectionMap(values);
                break;
            default:
                throw new IllegalArgumentException("Uri is not valid: " + uri);
        }
        if (sortOrder == null || sortOrder.equals("")) {
            sortOrder = "name";
        }
        Cursor c = builder.query(
                db, projection, selection, selectionArgs, null, null, sortOrder
        );
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case uriCode:
                return "vnd.android.cursor.dir/cte";
        }
        throw new IllegalArgumentException("Uri is not valid: " + uri);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowId = db.insert(DbHelper.getTableName(), "", values);
        if (rowId <= 0) {
            rowId = db.update(
                    DbHelper.getTableName(), values,
                    " id = " + values.getAsString(DbHelper.getColumnId()), null
            );
        }
        Uri cUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
        getContext().getContentResolver().notifyChange(cUri, null);
        return cUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        long rowId = db.delete(DbHelper.getTableName(), selection, selectionArgs);
        if (rowId > 0) {
            Uri cUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(cUri, null);
            return 1;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }

    public static Uri getContentUri() {
        return CONTENT_URI;
    }
}
