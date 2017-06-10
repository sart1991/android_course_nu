package com.exercises.sart1991.evaluacionfinal8p.ui.subview.taskdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BaseSubView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 6/10/2017.
 */

public class TaskDialog<C extends TaskDialogMvpSubView.Callback>
        extends BaseSubView<C> implements TaskDialogMvpSubView<C> {

    private List<Course> courses;
    private Dialog dialog;
    private EditText editName, editGrade;
    private Spinner spinnerCourses;
    private int positiveRes;

    public TaskDialog(List<Course> courses) {
        this.courses = courses;
        dialog = makeDialogTask();
    }

    @Override
    public void showDialog(int positiveRes) {
        this.positiveRes = positiveRes;
        dialog.show();
    }

    @Override
    public void fillDialog(String name, int position, String grade) {
        editName.setText(name);
        editGrade.setText(grade);
        spinnerCourses.setSelection(position);
    }

    @Override
    public void cleanDialog() {
        editName.setText("");
        editGrade.setText("");
        spinnerCourses.setSelection(0);
    }

    private List<String> getCourseNames() {
        List<String> names = new ArrayList<>();
        for (Course c: courses) {
            names.add(c.getName());
        }
        return names;
    }

    private Dialog makeDialogTask() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getViewContext());
        builder.setTitle(R.string.dialogTask_title);
        View view = LayoutInflater.from(getViewContext()).inflate(R.layout.dialog_task, null, false);
        editName = (EditText) view.findViewById(R.id.editText_dialogTask_name);
        editGrade = (EditText) view.findViewById(R.id.editText_dialogTask_grade);
        spinnerCourses = (Spinner) view.findViewById(R.id.spinner_dialogTask_course);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getViewContext(), android.R.layout.simple_spinner_item, getCourseNames()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapter);
        builder.setView(view);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                getCallback().onCancel();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(positiveRes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getCallback().onPositiveClick(
                        editName.getText().toString(),
                        courses.get(spinnerCourses.getSelectedItemPosition()).getId(),
                        editGrade.getText().toString()
                );
            }
        });
        return builder.create();
    }
}
