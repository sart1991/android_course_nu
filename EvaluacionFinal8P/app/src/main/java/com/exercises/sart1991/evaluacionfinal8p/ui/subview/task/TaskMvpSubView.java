package com.exercises.sart1991.evaluacionfinal8p.ui.subview.task;

import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpSubView;

/**
 * Created by sart1 on 6/9/2017.
 */

public interface TaskMvpSubView<C extends TaskMvpSubView.Callback> extends MvpSubView<C> {



    interface Callback extends MvpSubView.Callback {

    }
}
