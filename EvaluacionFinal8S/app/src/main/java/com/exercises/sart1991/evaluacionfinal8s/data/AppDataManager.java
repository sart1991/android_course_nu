package com.exercises.sart1991.evaluacionfinal8s.data;

import android.content.Context;

import com.exercises.sart1991.evaluacionfinal8s.data.apischool.ApiSchoolHelper;
import com.exercises.sart1991.evaluacionfinal8s.data.apischool.AppApiSchool;
import com.exercises.sart1991.evaluacionfinal8s.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8s.data.provider.AppProvider;
import com.exercises.sart1991.evaluacionfinal8s.data.provider.ProviderHelper;
import com.exercises.sart1991.evaluacionfinal8s.data.preferences.AppPreferences;
import com.exercises.sart1991.evaluacionfinal8s.data.preferences.PreferencesHelper;
import com.exercises.sart1991.evaluacionfinal8s.data.provider.ProviderLoader;

/**
 * Created by sart1 on 6/11/2017.
 */

public class AppDataManager implements DataManager {

    private ApiSchoolHelper apiSchoolHelper;
    private PreferencesHelper preferencesHelper;
    private ProviderHelper providerHelper;

    public AppDataManager(Context context) {
        apiSchoolHelper = new AppApiSchool(context);
        preferencesHelper = new AppPreferences(context);
        providerHelper = new AppProvider(context);
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
    public void getTokenStudent(Student student, ListenRequest<String> listener) {
        apiSchoolHelper.getTokenStudent(student, listener);
    }

    @Override
    public void checkStudentLogin(ListenRequest<Student> listener) {
        apiSchoolHelper.checkStudentLogin(listener);
    }

    @Override
    public void provideStudentId(int studentId) {
        providerHelper.provideStudentId(studentId);
    }

    @Override
    public ProviderLoader getLoaderData(ProviderLoader.Callback callback) {
        return providerHelper.getLoaderData(callback);
    }
}
