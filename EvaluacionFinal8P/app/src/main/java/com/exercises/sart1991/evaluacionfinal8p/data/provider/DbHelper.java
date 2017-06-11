package com.exercises.sart1991.evaluacionfinal8p.data.provider;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sart1 on 6/3/2017.
 */

class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "local_tasks";
    private static final String TABLE_NAME = "Tasks";
    private static final int DB_VERSION = 1;
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_GRADE = "grade_point";
    private static final String COLUMN_STUDENT = "student_id";
    private static final String COLUMN_COURSE = "course_id";
    private static final String SENTENCE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INT PRIMARY KEY, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_GRADE + " INT, " +
            COLUMN_STUDENT + " INT, " +
            COLUMN_COURSE + " TEXT" +
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

    static String getColumnId() {
        return COLUMN_ID;
    }

    static String getColumnName() {
        return COLUMN_NAME;
    }

    static String getColumnGrade() {
        return COLUMN_GRADE;
    }

    static String getColumnStudent() {
        return COLUMN_STUDENT;
    }

    static String getColumnCourse() {
        return COLUMN_COURSE;
    }
}
