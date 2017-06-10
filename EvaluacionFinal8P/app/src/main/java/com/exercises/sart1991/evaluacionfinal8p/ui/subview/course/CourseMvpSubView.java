package com.exercises.sart1991.evaluacionfinal8p.ui.subview.course;

import android.support.v7.widget.RecyclerView;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpSubView;

import java.util.List;

/**
 * Created by sart1 on 6/9/2017.
 */

public interface CourseMvpSubView extends MvpSubView {

    void setCoursesList(List<Course> courses);

    RecyclerView.Adapter getCoursesAdapter();

}
