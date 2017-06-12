package com.exercises.sart1991.evaluacionfinal8p.data.provider;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;

import java.util.List;

/**
 * Created by sart1 on 6/3/2017.
 */

public class AppDataProvider implements DataProviderHelper {

    private static final String TAG = AppDataProvider.class.getSimpleName();

    private ProviderContainer providerContainer =  new ProviderContainer();
    private ProviderLoader loaderProvider;
    private Context context;

    public AppDataProvider(Context context) {
        this.context = context;
        loaderProvider = new ProviderLoader(context);
    }

    @Override
    public ProviderContainer getContentProvider() {
        return providerContainer;
    }

    @Override
    public Uri insertProviderTask(List<Task> tasks) {
        Uri uri = null;
        Log.i(TAG, "insertProviderTask: appDtaProvider: " + tasks);
        for (Task task: tasks) {
            ContentValues values = new ContentValues();
            values.put(DbHelper.getColumnId(), task.getId());
            values.put(DbHelper.getColumnName(), task.getName());
            values.put(DbHelper.getColumnGrade(), task.getGradePoint());
            values.put(DbHelper.getColumnStudent(), task.getStudentId());
            values.put(DbHelper.getColumnCourse(), task.getCourseName());
            uri = context.getContentResolver().insert(ProviderContainer.getContentUri(), values);
        }
        return uri;
    }

    @Override
    public void insertProviderTask(Task task) {
        Log.i(TAG, "insertProviderTask: " + task);
        ContentValues values = new ContentValues();
        values.put(DbHelper.getColumnId(), task.getId());
        values.put(DbHelper.getColumnName(), task.getName());
        values.put(DbHelper.getColumnGrade(), task.getGradePoint());
        values.put(DbHelper.getColumnStudent(), task.getStudentId());
        values.put(DbHelper.getColumnCourse(), task.getCourseName());
        context.getContentResolver().insert(ProviderContainer.getContentUri(), values);
    }

    @Override
    public void deleteProviderTask(int id) {
        context.getContentResolver().delete(
                ProviderContainer.getContentUri(), " id = ? ", new String[]{String.valueOf(id)}
        );
    }

    @Override
    public void updateProviderTasks(Task task) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.getColumnId(), task.getId());
        values.put(DbHelper.getColumnName(), task.getName());
        values.put(DbHelper.getColumnGrade(), task.getGradePoint());
        values.put(DbHelper.getColumnStudent(), task.getStudentId());
        values.put(DbHelper.getColumnCourse(), task.getCourseName());
        context.getContentResolver().update(
                ProviderContainer.getContentUri(),
                values, " id = ? ",
                new String[]{String.valueOf(task.getId())}
        );
    }

    @Override
    public ProviderLoader getLoaderData(ProviderLoader.Callback callback) {
        loaderProvider.provideCallback(callback);
        return loaderProvider;
    }
}
