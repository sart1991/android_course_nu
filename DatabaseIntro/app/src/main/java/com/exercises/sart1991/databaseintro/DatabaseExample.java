package com.exercises.sart1991.databaseintro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sart1 on 5/11/2017.
 */

public class DatabaseExample extends SQLiteOpenHelper {

    private static final String DB_NAME = "myDb";
    private static final int DB_VERSION = 1;
    private static final String _ID = "id";
    private static final String _NAME = "name";
    protected static final String tableName = "student";

    private static final String query =
            "CREATE TABLE " + tableName + " (" + _ID + " INT PRIMARY KEY, " + _NAME + " TEXT)";

    public DatabaseExample(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTable() {
        this.getWritableDatabase().execSQL(query);
    }
}
