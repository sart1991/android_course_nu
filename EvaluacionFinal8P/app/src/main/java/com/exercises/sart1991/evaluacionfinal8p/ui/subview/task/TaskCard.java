package com.exercises.sart1991.evaluacionfinal8p.ui.subview.task;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BaseSubView;

/**
 * Created by sart1 on 6/9/2017.
 */

public class TaskCard<C extends TaskMvpSubView.Callback>
        extends BaseSubView<C> implements TaskMvpSubView<C> {



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

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
