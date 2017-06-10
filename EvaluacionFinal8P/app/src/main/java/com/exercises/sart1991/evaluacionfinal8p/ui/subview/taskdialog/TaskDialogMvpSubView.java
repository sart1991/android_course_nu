package com.exercises.sart1991.evaluacionfinal8p.ui.subview.taskdialog;

import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpSubView;

/**
 * Created by sart1 on 6/10/2017.
 */

public interface TaskDialogMvpSubView<C extends TaskDialogMvpSubView.Callback> extends MvpSubView<C> {

    void showDialog(int positiveRes);

    void fillDialog(String name, int position, String grade);

    void cleanDialog();

    interface Callback extends MvpSubView.Callback {
        void onPositiveClick(String name, int courseId, String grade);
        void onCancel();
    }
}
