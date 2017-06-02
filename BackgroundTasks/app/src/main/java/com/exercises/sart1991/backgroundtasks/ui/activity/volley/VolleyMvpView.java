package com.exercises.sart1991.backgroundtasks.ui.activity.volley;

import com.exercises.sart1991.backgroundtasks.ui.base.MvpView;

/**
 * Created by sart1 on 6/1/2017.
 */

public interface VolleyMvpView extends MvpView {

    void showResult(String result);
    String getId();
    String getUsername();
    String getEmail();
}
