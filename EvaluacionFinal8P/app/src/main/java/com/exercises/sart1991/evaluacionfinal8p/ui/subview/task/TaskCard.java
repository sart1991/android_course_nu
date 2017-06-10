package com.exercises.sart1991.evaluacionfinal8p.ui.subview.task;

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

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BaseSubView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 6/9/2017.
 */

public class TaskCard<C extends TaskMvpSubView.Callback>
        extends BaseSubView<C> implements TaskMvpSubView<C> {

    private List<Task> tasks = new ArrayList<>();
    private TaskAdapter adapter = new TaskAdapter();

    @Override
    public void setTasksList(List<Task> tasks) {
        this.tasks = tasks;
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
            holder.txtStudentName.setText(tasks.get(position).getStudentName());
            holder.txtCourseName.setText(tasks.get(position).getCourseName());
            holder.txtGrade.setText(String.valueOf(tasks.get(position).getGradePoint()));
        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView txtName;
            TextView txtStudentName;
            TextView txtCourseName;
            TextView txtGrade;
            ImageButton buttonDots;
            private MenuPopupHelper popupHelper;

            ViewHolder(View itemView) {
                super(itemView);
                txtName = (TextView) itemView.findViewById(R.id.textView_cardTask_name);
                txtStudentName = (TextView) itemView.findViewById(R.id.textView_cardTask_studentName);
                txtCourseName = (TextView) itemView.findViewById(R.id.textView_cardTask_courseName);
                txtGrade = (TextView) itemView.findViewById(R.id.textView_cardTask_grade);
                buttonDots = (ImageButton) itemView.findViewById(R.id.imageButton_cardTask_menu);
                buttonDots.setOnClickListener(clickListener);
                MenuBuilder builder = new MenuBuilder(getViewContext());
                builder.setCallback(callback);
                MenuInflater inflater = new MenuInflater(getViewContext());
                inflater.inflate(R.menu.cardtask_options, builder);
                popupHelper = new MenuPopupHelper(getViewContext(), builder, buttonDots);
                popupHelper.setForceShowIcon(true);
            }

            private MenuBuilder.Callback callback = new MenuBuilder.Callback() {
                @Override
                public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                    getCallback().onClickTaskOptionsMenu(
                            item.getItemId(), tasks.get(getAdapterPosition()).getId()
                    );
                    return true;
                }

                @Override
                public void onMenuModeChange(MenuBuilder menu) {

                }
            };

            private View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupHelper.show();
                }
            };
        }
    }
}
