package com.exercises.sart1991.evaluacionfinal8p.ui.subview.taskdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BaseSubView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sart1 on 6/10/2017.
 */

public class TaskDialog<C extends TaskDialogMvpSubView.Callback>
        extends BaseSubView<C> implements TaskDialogMvpSubView<C> {

    private static final String TAG = TaskDialog.class.getSimpleName();

    private int id;
    private List<Course> courses;
    private List<Student> students;
    private AlertDialog dialog;
    private EditText editName, editGrade;
    private Spinner spinnerStudents;
    private Spinner spinnerCourses;
    private ArrayAdapter<String> adapterStudent;
    private ArrayAdapter<String> adapterCourse;
    private int positiveRes;

    public TaskDialog(int positiveRes) {
        this.positiveRes = positiveRes;
    }

    @Override
    public void makeDialogTask(List<Student> students, List<Course> courses) {
        this.students = students;
        this.courses = courses;
        dialog = makeDialog();
    }

    @Override
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void fillDialog(int id, String name, String studentName, String courseName, double grade) {
        this.id = id;
        editName.setText(name);
        spinnerStudents.setSelection(adapterStudent.getPosition(studentName));
        spinnerCourses.setSelection(adapterCourse.getPosition(courseName));
        editGrade.setText(String.valueOf(grade));
    }

    @Override
    public void cleanDialog() {
        editName.setText("");
        editGrade.setText("");
        spinnerCourses.setSelection(0);
    }

    private List<String> getCoursesName() {
        List<String> names = new ArrayList<>();
        for (Course c: courses) {
            names.add(c.getName());
        }
        return names;
    }

    private List<String> getStudentsName() {
        List<String> names = new ArrayList<>();
        for (Student s: students) {
            names.add(s.getName());
        }
        return names;
    }

    private InputFilter inputFilter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            try {
                double input = Double.parseDouble(dest.toString() + source.toString());
                if (isInRange(0, 5, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(double a, double b, double c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    };

    private AlertDialog makeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getViewContext());
        builder.setTitle(R.string.dialogTask_title);
        View view = LayoutInflater.from(getViewContext()).inflate(R.layout.dialog_task, null, false);
        editName = (EditText) view.findViewById(R.id.editText_dialogTask_name);
        editGrade = (EditText) view.findViewById(R.id.editText_dialogTask_grade);
//        InputFilter[] filters = editGrade.getFilters();
//        List<InputFilter> lFilters = Arrays.asList(filters);
//        lFilters.add(inputFilter);
//        editGrade.setFilters(lFilters.toArray(new InputFilter[lFilters.size()]));
        spinnerStudents = (Spinner) view.findViewById(R.id.spinner_dialogTask_student);
        adapterStudent = new ArrayAdapter<>(
                getViewContext(), android.R.layout.simple_spinner_item, getStudentsName()
        );
        adapterStudent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStudents.setAdapter(adapterStudent);
        spinnerCourses = (Spinner) view.findViewById(R.id.spinner_dialogTask_course);
        adapterCourse = new ArrayAdapter<>(
                getViewContext(), android.R.layout.simple_spinner_item, getCoursesName()
        );
        adapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapterCourse);
        builder.setView(view);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                getCallback().onCancel();
            }
        });
        builder.setNegativeButton(R.string.dialogTask_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(positiveRes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick: positive: " + which);
                getCallback().onPositiveClick(
                        positiveRes,
                        id,
                        editName.getText().toString(),
                        students.get(spinnerStudents.getSelectedItemPosition()).getId(),
                        courses.get(spinnerCourses.getSelectedItemPosition()).getId(),
                        editGrade.getText().toString()
                );
            }
        });
        return builder.create();
    }
}
