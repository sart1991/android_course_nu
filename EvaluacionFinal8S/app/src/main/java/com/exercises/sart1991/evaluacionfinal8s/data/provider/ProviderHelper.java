package com.exercises.sart1991.evaluacionfinal8s.data.provider;

/**
 * Created by sart1 on 6/11/2017.
 */

public interface ProviderHelper {

    void provideStudentId(int studentId);

    ProviderLoader getLoaderData(ProviderLoader.Callback callback);

}
