package com.exercises.sart1991.backgroundtasks.ui.activity.httpurl;

import com.exercises.sart1991.backgroundtasks.ui.base.MvpPresenter;

/**
 * Created by sart1 on 5/30/2017.
 */

public interface HttpMvpPresenter<V extends HttpMvpView> extends MvpPresenter<V> {

    void getPeople();
}
