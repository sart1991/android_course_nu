package com.exercises.sart1991.backgroundtasks.ui.activity.httpurl;

import com.exercises.sart1991.backgroundtasks.ui.base.MvpView;

/**
 * Created by sart1 on 5/30/2017.
 */

public interface HttpMvpView extends MvpView {
    void showProgressDialogForGetPeople();
    void dismissDialogForGetPeople();
    boolean dialogForGetPeopleIsShowing();
    void showResult(String result);
}
