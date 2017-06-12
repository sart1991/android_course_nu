package com.exercises.sart1991.evaluacionfinal8s.data.provider;

import android.content.Context;

/**
 * Created by sart1 on 6/11/2017.
 */

public class AppProvider implements ProviderHelper {

    private ProviderLoader providerLoader;

    public AppProvider(Context context) {
             providerLoader = new ProviderLoader(context);
    }

    @Override
    public void provideStudentId(int studentId) {
        providerLoader.setStudentId(studentId);
    }

    @Override
    public ProviderLoader getLoaderData(ProviderLoader.Callback callback) {
        providerLoader.provideCallback(callback);
        return providerLoader;
    }
}
