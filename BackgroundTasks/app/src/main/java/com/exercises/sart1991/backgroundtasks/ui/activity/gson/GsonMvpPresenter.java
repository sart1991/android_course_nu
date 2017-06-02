package com.exercises.sart1991.backgroundtasks.ui.activity.gson;

import com.exercises.sart1991.backgroundtasks.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/2/2017.
 */

public interface GsonMvpPresenter<V extends GsonMvpView> extends MvpPresenter<V> {
    void loadUsers();
    void loadUsersFromString();
}
