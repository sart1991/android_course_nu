package com.exercises.sart1991.evaluacionfinal8s.ui.subview.taskcard;

import android.support.v7.widget.RecyclerView;

import com.exercises.sart1991.evaluacionfinal8s.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8s.ui.base.MvpSubView;

import java.util.List;

/**
 * Created by sart1 on 6/11/2017.
 */

public interface TaskCardMvpSubview extends MvpSubView {

    void setTasksList(List<Task> tasks);

    List<Task> getTasksList();

    RecyclerView.Adapter getTasksAdapter();

}
