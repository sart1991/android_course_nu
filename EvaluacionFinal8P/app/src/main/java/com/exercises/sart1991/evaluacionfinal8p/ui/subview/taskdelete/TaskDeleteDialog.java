package com.exercises.sart1991.evaluacionfinal8p.ui.subview.taskdelete;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BaseSubView;

/**
 * Created by sart1 on 6/11/2017.
 */

public class TaskDeleteDialog<C extends TaskDeleteDialogMvpSubView.Callback>
        extends BaseSubView<C> implements TaskDeleteDialogMvpSubView<C> {

    private Task task;
    private Dialog dialog;

    @Override
    public void makeDialog() {
        dialog = makeDialogDeleteTask();
    }

    @Override
    public void showDialog(Task task) {
        this.task = task;
        dialog.show();
    }

    private Dialog makeDialogDeleteTask() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getViewContext());
        builder.setTitle(R.string.main_dialogDeleteTaskTitle);
        builder.setMessage(R.string.main_dialogDeleteTaskMessage);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                getCallback().onClickNegativeTaskDelete();
            }
        });
        builder.setNegativeButton(R.string.main_dialogDeleteTaskNegative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(R.string.main_dialogDeleteTaskPositive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getCallback().onClickPositiveTaskDelete(task);
            }
        });
        return builder.create();
    }
}
