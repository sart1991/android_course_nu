package com.exercises.sart1991.backgroundtasks.ui.activity.volley;

import com.exercises.sart1991.backgroundtasks.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/1/2017.
 */

public interface VolleyMvpPresenter<V extends VolleyMvpView> extends MvpPresenter<V> {

    void clickButtonGet();
    void clickButtonPost();
    void clickButtonPut();
    void clickButtonDelete();
}
