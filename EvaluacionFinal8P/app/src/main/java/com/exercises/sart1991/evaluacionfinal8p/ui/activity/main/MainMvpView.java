package com.exercises.sart1991.evaluacionfinal8p.ui.activity.main;

import android.support.design.widget.NavigationView;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpView;
import com.exercises.sart1991.evaluacionfinal8p.ui.subview.task.TaskMvpSubView;
import com.exercises.sart1991.evaluacionfinal8p.ui.subview.taskdialog.TaskDialogMvpSubView;

import java.util.List;

/**
 * Created by sart1 on 6/7/2017.
 */

public interface MainMvpView extends MvpView, TaskMvpSubView.Callback,
                                     TaskDialogMvpSubView.Callback {

    List<Course> getCourseList();
    void setCourseList(List<Course> courses);
    void showCourseCards();

    List<Student> getStudentList();
    void setStudentList(List<Student> students);
    void showStudentCards();

    List<Task> getTaskList();
    void setTaskList(List<Task> tasks);
    void showTaskCards();

    void setNavViewProfessorData(String name, String email);

    void makeDialogTaskForProfessor(List<Student> students, List<Course> courses);
    void cleanDialogTask();
    void fillDialogTask(int id, String name, String studentName, String courseName, double grade);

    void showDialogNewTask();
    void showDialogEditTask();

    void setFabVisibility(int resVisible);

    void gotoLogin();

}
