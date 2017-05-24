package com.exercises.sart1991.evaluacionfinal7.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sart1 on 5/22/2017.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = DbOpenHelper.class.getSimpleName();
    private static final String DB_NAME = DbInfo.DB_NAME.getDbName();

    private static int dbVersion = 1;
    private String userTable = DbInfo.TablesInfo.USER_TABLE.getTableName();
    private String donorTable = DbInfo.TablesInfo.DONOR_TABLE.getTableName();

    private String[] userColumns = new String[] {
            DbInfo.UserInfo.USER_NAME.getValue(),
            DbInfo.UserInfo.PASSWORD.getValue()
    };

    private String[] donorColumns = new String[] {
            DbInfo.DonorInfo.ID.getValue(),
            DbInfo.DonorInfo.NAME.getValue(),
            DbInfo.DonorInfo.LAST_NAME.getValue(),
            DbInfo.DonorInfo.AGE.getValue(),
            DbInfo.DonorInfo.BLOOD_TYPE.getValue(),
            DbInfo.DonorInfo.RH.getValue(),
            DbInfo.DonorInfo.WEIGHT.getValue(),
            DbInfo.DonorInfo.HEIGHT.getValue(),
            DbInfo.DonorInfo.USER_NAME.getValue()
    };


    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sentenceCreateTable(userTable, userColumns));
        db.execSQL(sentenceCreateTable(donorTable, donorColumns));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dbVersion = newVersion;
        db.execSQL(sentenceDropTable(userTable));
        db.execSQL(sentenceDropTable(donorTable));
        onCreate(db);
    }

    private String sentenceCreateTable(String tableName, String... columns) {
        StringBuilder createTable = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + "(");
        String delim = "";
        for (String column : columns) {
            createTable.append(delim).append(column);
            delim = ", ";
        }
        createTable.append(");");
        Log.i(TAG, "sentenceCreateTable: " + createTable);
        return createTable.toString();
    }

    private String sentenceDropTable(String tableName) {
        return "DROP TABLE IF EXISTS " + tableName + ";";
    }
}
