package com.exercises.sart1991.evaluacionfinal8p.ui.subview.student;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BaseSubView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 6/9/2017.
 */

public class StudentCard extends BaseSubView implements StudentMvpSubView {

    private List<Student> students = new ArrayList<>();
    private StudentAdapter adapter = new StudentAdapter();

    @Override
    public void setStudentsList(List<Student> students) {
        this.students = students;
    }

    @Override
    public RecyclerView.Adapter getStudentsAdapter() {
        return adapter;
    }

    private class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getViewContext()).inflate(
                    R.layout.card_student, parent, false
            );
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.txtName.setText(students.get(position).getName());
            holder.txtEmail.setText(students.get(position).getEmail());
        }

        @Override
        public int getItemCount() {
            return students.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView txtName;
            TextView txtEmail;

            ViewHolder(View itemView) {
                super(itemView);
                txtName = (TextView) itemView.findViewById(R.id.textView_cardStudent_name);
                txtEmail = (TextView) itemView.findViewById(R.id.textView_cardStudent_email);
            }
        }
    }
}
