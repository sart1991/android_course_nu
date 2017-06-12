package com.exercises.sart1991.evaluacionfinal8s.data.provider;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.exercises.sart1991.evaluacionfinal8s.data.apischool.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 6/11/2017.
 */

public class ProviderLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = ProviderLoader.class.getSimpleName();

    private static final String PROVIDER_NAME =
            "com.exercises.sart1991.evaluacionfinal8p.data.provider.ProviderContainer";
    private static final String URL = "content://" + PROVIDER_NAME + "/cte";
    private static final Uri CONTENT_URI = Uri.parse(URL);

    private CursorLoader cursorLoader;
    private Context context;
    private Callback callback;
    private int studentId;

    ProviderLoader(Context context) {
        this.context = context;
    }

    void provideCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        cursorLoader = new CursorLoader(
                context, CONTENT_URI, null, " student_id = ? ", new String[]{String.valueOf(studentId)}, null
        );
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()) {
            StringBuilder sb = new StringBuilder();
            List<Task> tasks = new ArrayList<>();
            do {
                /*sb.append("\n")
                        .append(data.getInt(data.getColumnIndex("id")))
                        .append(" - ")
                        .append(data.getString(data.getColumnIndex("name")))
                        .append(" - ")
                        .append(data.getString(data.getColumnIndex("student_id")))
                        .append(" - ")
                        .append(data.getString(data.getColumnIndex("course_id")))
                        .append(" - ")
                        .append(data.getString(data.getColumnIndex("grade_point")));*/
                tasks.add(new Task(
                        data.getInt(data.getColumnIndex("id")),
                        data.getString(data.getColumnIndex("name")),
                        "",
                        data.getString(data.getColumnIndex("course_id")),
                        data.getDouble(data.getColumnIndex("grade_point"))
                ));

            } while (data.moveToNext());
            if (callback != null) {
                callback.onLoaderFinished(tasks);
            } else {
                Log.i(TAG, "onLoadFinished: no error");
            }
        } else {
            if (callback != null) {
                callback.onLoaderError();
            } else {
                Log.i(TAG, "onLoadFinished: error");
            }
        }
    }

    void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public interface Callback {
        void onLoaderFinished(List<Task> task);

        void onLoaderError();
    }
}
