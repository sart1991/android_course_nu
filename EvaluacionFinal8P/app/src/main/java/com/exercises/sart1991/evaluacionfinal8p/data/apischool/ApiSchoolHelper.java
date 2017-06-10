package com.exercises.sart1991.evaluacionfinal8p.data.apischool;

import android.content.Context;
import android.support.annotation.Nullable;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Professor;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;

import java.util.List;

/**
 * Created by sart1 on 6/7/2017.
 */

public interface ApiSchoolHelper {

    void getTokenProfessor(Professor professor, ListenRequest<String> listener);

    void checkProfessorLogin(ListenRequest<Professor> listener);

    void getCourses(ListenRequest<List<Course>> listener);

    void getStudents(ListenRequest<List<Student>> listener);

    void getTasks(ListenRequest<List<Task>> listener);

    void postTask(Task task, @Nullable ListenRequest<Task> listener);

    void putTask(Task task, @Nullable ListenRequest<Task> listener);

    void deleteTask(Task task, @Nullable ListenRequest<Task> listener);

    void deleteTask(int taskId, @Nullable ListenRequest<Task> listener);

    void testApiSchool(ListenRequest<String> listener);

    interface ListenRequest<O> {
        void onSuccess(O result);
        void onError();
    }
}
