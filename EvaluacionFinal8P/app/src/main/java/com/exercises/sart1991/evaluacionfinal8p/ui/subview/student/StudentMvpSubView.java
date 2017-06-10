package com.exercises.sart1991.evaluacionfinal8p.ui.subview.student;

import android.support.v7.widget.RecyclerView;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpSubView;

import java.util.List;

/**
 * Created by sart1 on 6/9/2017.
 */

public interface StudentMvpSubView extends MvpSubView {

    void setStudentsList(List<Student> students);

    RecyclerView.Adapter getStudentsAdapter();
}
