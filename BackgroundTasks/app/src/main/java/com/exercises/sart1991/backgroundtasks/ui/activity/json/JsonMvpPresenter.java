package com.exercises.sart1991.backgroundtasks.ui.activity.json;

import com.exercises.sart1991.backgroundtasks.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/1/2017.
 */

public interface JsonMvpPresenter<V extends JsonMvpView> extends MvpPresenter<V> {

    void loadUserCards();

}
