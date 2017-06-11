package com.exercises.sart1991.evaluacionfinal8p.ui.activity.main;

import android.view.View;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/7/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void welcome();

    void initComponents(View view);

    void clickDrawerMenu(int itemId);

    void clickTaskOptionsMenu(int itemId, Task task);

    void clickFab();

    void clickPositiveDialogTask(int method, int id, String name,
                                 int studentId, int courseId, String grade);

    void clickCancelDialogTask();

    void clickConfirmDeleteTask(Task task);

    boolean clickSignOut(int itemId);

}
