package com.exercises.sart1991.evaluacionfinal8p.data.apischool;

import android.content.Context;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;

import java.util.List;

/**
 * Created by sart1 on 6/7/2017.
 */

public interface ApiSchoolHelper {

    void provideApiSchoolContext(Context context);

    void getCourses(ListenRequest<List<Course>> listener);

    void getStudents(ListenRequest<List<Student>> listener);

    void getTasks(ListenRequest<List<Task>> listener);

    interface ListenRequest<O> {
        void onSuccess(O result);
        void onError();
    }
}
