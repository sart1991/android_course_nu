package com.exercises.sart1991.evaluacionfinal8p.ui.subview.course;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BaseSubView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 6/9/2017.
 */

public class CourseCard extends BaseSubView implements CourseMvpSubView {

    private static final String TAG = CourseCard.class.getSimpleName();

    private List<Course> courses = new ArrayList<>();
    private CourseAdapter adapter = new CourseAdapter();

    @Override
    public void setCoursesList(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public List<Course> getCoursesList() {
        return courses;
    }

    @Override
    public RecyclerView.Adapter getCoursesAdapter() {
        return adapter;
    }

    private class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getViewContext()).inflate(
                    R.layout.card_course, parent, false
            );
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Log.i(TAG, "onBindViewHolder: courses: " + courses.get(position));
            holder.txtName.setText(courses.get(position).getName());
            holder.txtCode.setText(String.valueOf(courses.get(position).getId()));
        }

        @Override
        public int getItemCount() {
            return courses.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView txtName;
            TextView txtCode;

            ViewHolder(View itemView) {
                super(itemView);
                txtName = (TextView) itemView.findViewById(R.id.textView_cardCourse_name);
                txtCode = (TextView) itemView.findViewById(R.id.textView_cardCourse_codeContent);
            }
        }
    }
}
