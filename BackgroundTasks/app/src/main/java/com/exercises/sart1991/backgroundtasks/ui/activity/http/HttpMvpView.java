package com.exercises.sart1991.backgroundtasks.ui.activity.http;

import com.exercises.sart1991.backgroundtasks.ui.base.MvpView;

/**
 * Created by sart1 on 5/30/2017.
 */

public interface HttpMvpView extends MvpView {
    void showProgressDialogForNetwork(int resMessage);
    void dismissDialogForNetwork();
    boolean dialogForNetworkIsShowing();
    void showResult(String result);
    String getEditId();
}
