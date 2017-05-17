package com.exercises.sart1991.sqlitedrawer.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sart1 on 5/15/2017.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String SENTENCE_CREATE_TABLE =
            DbInfo.DDL.CREATE_TABLE.get() + " " + DbInfo.Info.VEHICLE + " (" +
                    DbInfo.Info.ID + " " +
                    DbInfo.DDL.INTEGER.get() + " " +
                    DbInfo.DDL.PRIMARY_KEY.get() + " " +
                    DbInfo.DDL.AUTOINCREMENT.get() + ", " +
                    DbInfo.Info.BRAND + " " +
                    DbInfo.DDL.TEXT.get() + ", " +
                    DbInfo.Info.QUANTITY + " " +
                    DbInfo.DDL.INT.get() +
            ");";
    private static final String SENTENCE_DROP_TABLE =
            DbInfo.DML.DROP_TABLE.get() + " " + DbInfo.DML.IF_EXISTS.get() + " " +
                    DbInfo.Info.VEHICLE;

    public DbOpenHelper(Context context) {
        super(context, DbInfo.Info.APP_DATABASE.toString(), null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SENTENCE_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SENTENCE_DROP_TABLE);
        onCreate(db);
    }
}
