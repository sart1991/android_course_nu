package com.exercises.sart1991.evaluacionfinal8p.data;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.ApiSchoolHelper;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.AppApiSchool;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Professor;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8p.data.preferences.AppPreferences;
import com.exercises.sart1991.evaluacionfinal8p.data.preferences.PreferencesHelper;
import com.exercises.sart1991.evaluacionfinal8p.data.provider.AppDataProvider;
import com.exercises.sart1991.evaluacionfinal8p.data.provider.DataProviderHelper;
import com.exercises.sart1991.evaluacionfinal8p.data.provider.ProviderContainer;
import com.exercises.sart1991.evaluacionfinal8p.data.provider.ProviderLoader;

import java.util.List;

/**
 * Created by sart1 on 6/7/2017.
 */

public class AppDataManager implements DataManager {

    private ApiSchoolHelper apiSchoolHelper;
    private PreferencesHelper preferencesHelper;
    private DataProviderHelper providerHelper;

    public AppDataManager(Context context) {
        apiSchoolHelper = new AppApiSchool(context);
        preferencesHelper = new AppPreferences(context);
        providerHelper = new AppDataProvider(context);
    }

    @Override
    public void getTokenProfessor(Professor professor, ListenRequest<String> listener) {
        apiSchoolHelper.getTokenProfessor(professor, listener);
    }

    @Override
    public void checkProfessorLogin(ListenRequest<Professor> listener) {
        apiSchoolHelper.checkProfessorLogin(listener);
    }

    @Override
    public void getCourses(ListenRequest<List<Course>> listener) {
        apiSchoolHelper.getCourses(listener);
    }

    @Override
    public void getStudents(ListenRequest<List<Student>> listener) {
        apiSchoolHelper.getStudents(listener);
    }

    @Override
    public void getTasks(ListenRequest<List<Task>> listener) {
        apiSchoolHelper.getTasks(listener);
    }

    @Override
    public void postTask(Task task, @Nullable ListenRequest<Task> listener) {
        apiSchoolHelper.postTask(task, listener);
    }

    @Override
    public void putTask(Task task, @Nullable ListenRequest<Task> listener) {
        apiSchoolHelper.putTask(task, listener);
    }

    @Override
    public void deleteTask(Task task, @Nullable ListenRequest<Task> listener) {
        apiSchoolHelper.deleteTask(task, listener);
    }

    @Override
    public void deleteTask(int taskId, @Nullable ListenRequest<Task> listener) {
        apiSchoolHelper.deleteTask(taskId, listener);
    }

    @Override
    public String getToken() {
        return preferencesHelper.getToken();
    }

    @Override
    public void setToken(String token) {
        preferencesHelper.setToken(token);
    }

    @Override
    public void testApiSchool(ListenRequest<String> listener) {
        apiSchoolHelper.testApiSchool(listener);
    }

    @Override
    public ProviderContainer getContentProvider() {
        return providerHelper.getContentProvider();
    }

    @Override
    public Uri insertProviderTask(List<Task> tasks) {
        return providerHelper.insertProviderTask(tasks);
    }

    @Override
    public void deleteProviderTask(int id) {
        providerHelper.deleteProviderTask(id);
    }

    @Override
    public ProviderLoader getLoaderData(ProviderLoader.Callback callback) {
        return providerHelper.getLoaderData(callback);
    }
}
