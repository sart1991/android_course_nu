package com.exercises.sart1991.evaluacionfinal8p.ui.subview.task;

import android.support.v7.widget.RecyclerView;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpSubView;

import java.util.List;

/**
 * Created by sart1 on 6/9/2017.
 */

public interface TaskMvpSubView<C extends TaskMvpSubView.Callback> extends MvpSubView<C> {

    void setTasksList(List<Task> tasks);

    List<Task> getTasksList();

    RecyclerView.Adapter getTasksAdapter();

    interface Callback extends MvpSubView.Callback {

        void onClickTaskOptionsMenu(int itemId, Task task);

    }
}
