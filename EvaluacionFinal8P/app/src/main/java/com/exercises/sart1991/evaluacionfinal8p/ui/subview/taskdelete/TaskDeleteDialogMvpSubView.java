package com.exercises.sart1991.evaluacionfinal8p.ui.subview.taskdelete;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpSubView;

/**
 * Created by sart1 on 6/11/2017.
 */

public interface TaskDeleteDialogMvpSubView<C extends TaskDeleteDialogMvpSubView.Callback>
        extends MvpSubView<C> {

    void makeDialog();
    void showDialog(Task task);

    interface Callback extends MvpSubView.Callback {
        void onClickNegativeTaskDelete();
        void onClickPositiveTaskDelete(Task task);
    }
}
