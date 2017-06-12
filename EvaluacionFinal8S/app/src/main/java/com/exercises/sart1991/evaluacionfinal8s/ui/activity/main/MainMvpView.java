package com.exercises.sart1991.evaluacionfinal8s.ui.activity.main;

import com.exercises.sart1991.evaluacionfinal8s.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8s.ui.base.MvpSubView;
import com.exercises.sart1991.evaluacionfinal8s.ui.base.MvpView;

import java.util.List;

/**
 * Created by sart1 on 6/11/2017.
 */

public interface MainMvpView extends MvpView, MvpSubView.Callback {

    void setToolbarSubtitle(String subtitle);

    void callProviderData();

    void setListTasks(List<Task> tasks);

    void gotoLogin();

}
