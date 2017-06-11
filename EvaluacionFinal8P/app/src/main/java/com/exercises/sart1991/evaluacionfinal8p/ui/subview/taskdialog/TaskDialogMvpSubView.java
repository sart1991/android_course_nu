package com.exercises.sart1991.evaluacionfinal8p.ui.subview.taskdialog;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpSubView;

import java.util.List;

/**
 * Created by sart1 on 6/10/2017.
 */

public interface TaskDialogMvpSubView<C extends TaskDialogMvpSubView.Callback> extends MvpSubView<C> {

    void makeDialogTask(List<Student> students, List<Course> courses);

    void showDialog();

    void fillDialog(int id, String name, String studentName, String courseName, double grade);

    void cleanDialog();

    interface Callback extends MvpSubView.Callback {
        void onPositiveClick(int method, int id, String name,
                             int studentId, int courseId, String grade);
        void onCancel();
    }
}
