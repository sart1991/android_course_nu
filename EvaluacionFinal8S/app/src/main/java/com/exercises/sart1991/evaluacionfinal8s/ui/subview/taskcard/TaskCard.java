package com.exercises.sart1991.evaluacionfinal8s.ui.subview.taskcard;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.exercises.sart1991.evaluacionfinal8s.R;
import com.exercises.sart1991.evaluacionfinal8s.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8s.ui.base.BaseSubView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 6/11/2017.
 */

public class TaskCard extends BaseSubView implements TaskCardMvpSubview {

    private List<Task> tasks = new ArrayList<>();
    private TaskAdapter adapter = new TaskAdapter();

    @Override
    public void setTasksList(List<Task> tasks) {
        this.tasks = tasks;
        adapter.notifyDataSetChanged();
    }

    @Override
    public List<Task> getTasksList() {
        return tasks;
    }

    @Override
    public RecyclerView.Adapter getTasksAdapter() {
        return adapter;
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getViewContext()).inflate(
                    R.layout.card_task, parent, false
            );
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.txtName.setText(tasks.get(position).getName());
            holder.txtCourseName.setText(tasks.get(position).getCourseName());
            holder.txtGrade.setText(String.valueOf(tasks.get(position).getGradePoint()));
        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView txtName;
            TextView txtCourseName;
            TextView txtGrade;
            ImageButton buttonDots;
            private MenuPopupHelper popupHelper;

            ViewHolder(View itemView) {
                super(itemView);
                txtName = (TextView) itemView.findViewById(R.id.textView_cardTask_name);
                txtCourseName = (TextView) itemView.findViewById(R.id.textView_cardTask_courseName);
                txtGrade = (TextView) itemView.findViewById(R.id.textView_cardTask_grade);
            }
        }
    }
}
