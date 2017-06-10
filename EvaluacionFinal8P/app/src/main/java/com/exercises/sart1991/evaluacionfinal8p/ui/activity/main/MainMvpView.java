package com.exercises.sart1991.evaluacionfinal8p.ui.activity.main;

import android.support.design.widget.NavigationView;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpView;
import com.exercises.sart1991.evaluacionfinal8p.ui.subview.task.TaskMvpSubView;

import java.util.List;

/**
 * Created by sart1 on 6/7/2017.
 */

public interface MainMvpView extends MvpView, TaskMvpSubView.Callback {

    void showCourseCards(List<Course> courses);

    void showStudentCards(List<Student> students);

    void showTaskCards(List<Task> tasks);

    void gotoLogin();

}
