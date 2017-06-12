package com.exercises.sart1991.evaluacionfinal8s.data.apischool;

import com.exercises.sart1991.evaluacionfinal8s.data.apischool.model.Student;

/**
 * Created by sart1 on 6/11/2017.
 */

public interface ApiSchoolHelper {

    void getTokenStudent(Student student, ListenRequest<String> listener);

    void checkStudentLogin(ListenRequest<Student> listener);

    interface ListenRequest<O> {
        void onSuccess(O result);
        void onError();
    }

}
