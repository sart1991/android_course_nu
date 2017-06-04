package com.exercises.sart1991.backgroundtasks.data.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sart1 on 6/3/2017.
 */

class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Example";
    private static final String TABLE_NAME = "Test";
    private static final int DB_VERSION = 1;
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String SENTENCE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT" +
            ")";

    DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SENTENCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    static String getTableName() {
        return TABLE_NAME;
    }

    public static String getColumnId() {
        return COLUMN_ID;
    }

    public static String getColumnName() {
        return COLUMN_NAME;
    }
}
