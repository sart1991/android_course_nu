package com.exercises.sart1991.evaluacionfinal8p.ui.activity.main;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.ApiSchoolHelper;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Professor;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 6/7/2017.
 */

public class MainPresenter<V extends MainMvpView>
        extends BasePresenter<V> implements MainMvpPresenter<V> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private List<Course> courses = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private int persistentCard;
    private View view;

    @Override
    public void welcome() {

        getDataManager().checkProfessorLogin(listenLogin);
        getDataManager().getContentProvider();
    }

    @Override
    public void initComponents(View view) {
        this.view = view;
        if (courses.size() <= 0) getDataManager().getCourses(listenCourses);
        if (students.size() <= 0) getDataManager().getStudents(listenStudents);
        if (tasks.size() <= 0) getDataManager().getTasks(listenTasks);
        loadCardSelection(persistentCard);
    }

    @Override
    public void clickDrawerMenu(int itemId) {
        Log.i(TAG, "clickDrawerMenu: ");
        loadCardSelection(itemId);
    }

    private void loadCardSelection(int itemId) {
        switch (itemId) {
            case R.id.item_mainDrawer_courses:
                loadCourseCards();
                getMvpView().setFabVisibility(View.GONE);
                break;
            case R.id.item_mainDrawer_students:
                loadStudentCards();
                getMvpView().setFabVisibility(View.GONE);
                break;
            case R.id.item_mainDrawer_tasks:
                loadTaskCards();
                getMvpView().makeDialogTaskForProfessor(students, courses);
                getMvpView().setFabVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void clickTaskOptionsMenu(int itemId, Task task) {
        switch (itemId) {
            case R.id.item_cardTaskOptions_edit:
                getMvpView().fillDialogTask(
                        task.getId(),
                        task.getName(), task.getStudentName(),
                        task.getCourseName(), task.getGradePoint()
                );
                getMvpView().showDialogEditTask();
                break;
            case R.id.item_cardTaskOptions_delete:
                getMvpView().showDialogDeleteTask(task);
                break;
        }
    }

    private void loadCourseCards() {
        getMvpView().setCourseList(courses);
        getMvpView().showCourseCards();
        persistentCard = R.id.item_mainDrawer_courses;
    }

    private void loadStudentCards() {
        getMvpView().setStudentList(students);
        getMvpView().showStudentCards();
        persistentCard = R.id.item_mainDrawer_students;
    }

    private void loadTaskCards() {
        getMvpView().setTaskList(tasks);
        getMvpView().showTaskCards();
        persistentCard = R.id.item_mainDrawer_tasks;
    }

    @Override
    public void clickFab() {
        getMvpView().showDialogNewTask();
    }

    @Override
    public void clickPositiveDialogTask(int method, int id, String name,
                                        int studentId, int courseId, String grade) {

        double gradeNumber = 0;
        if (grade != null && !"".equals(grade)) gradeNumber = Double.valueOf(grade);

        Task task = new Task(id, name, gradeNumber, studentId, courseId);
        Log.i(TAG, "clickPositiveDialogTask: task: " + task);
        switch (method) {
            case R.string.dialogTask_positiveCreate:
                Log.i(TAG, "clickPositiveDialogTask: listenUpdateTasks: " + listenUpdateTasks);
                getDataManager().postTask(task, listenUpdateTasks);
                getDataManager().insertProviderTask(task);
                break;
            case R.string.dialogTask_posititveEdit:
                Log.i(TAG, "clickPositiveDialogTask: listenUpdateTasks: " + listenUpdateTasks);
                getDataManager().putTask(task, listenUpdateTasks);
                getDataManager().updateProviderTasks(task);
                break;
        }
        getMvpView().cleanDialogTask();
    }

    @Override
    public void clickCancelDialogTask() {
        getMvpView().onNotify(R.string.main_notifyDialogCancel, view);
    }

    @Override
    public void clickConfirmDeleteTask(Task task) {
        Log.i(TAG, "clickConfirmDeleteTask: listenUpdateTasks: " + listenUpdateTasks);
        getDataManager().deleteTask(task, listenUpdateTasks);
        getDataManager().deleteProviderTask(task.getId());
    }

    @Override
    public boolean clickSignOut(int itemId) {
        switch (itemId) {
            case R.id.item_mainOptions_signOut:
                getDataManager().setToken("");
                getMvpView().gotoLogin();
                return true;
        }
        return false;
    }

    private ApiSchoolHelper.ListenRequest<Professor> listenLogin = new ApiSchoolHelper.ListenRequest<Professor>() {
        @Override
        public void onSuccess(Professor result) {
            Log.i(TAG, "onSuccess: login: " + result);
            getMvpView().setNavViewProfessorData(result.getName(), result.getEmail());
        }

        @Override
        public void onError() {
            Log.i(TAG, "onError: error");
            if (getDataManager().getToken() == null || "".equals(getDataManager().getToken())) {
                getMvpView().gotoLogin();
                return;
            }
            if(!getMvpView().checkInternetConnection()) {
                getMvpView().onError(R.string.error_noInterentConnection, view);
                return;
            } else {
                getMvpView().onError(R.string.error_serverError, view);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getMvpView().gotoLogin();
                    }
                }, 2000);
                return;
            }
        }
    };

    private ApiSchoolHelper.ListenRequest<List<Course>> listenCourses =  new ApiSchoolHelper.ListenRequest<List<Course>>() {
        @Override
        public void onSuccess(List<Course> result) {
            courses = result;
            loadCourseCards();
        }

        @Override
        public void onError() {
            Log.i(TAG, "onError: listenCourses");
        }
    };

    private ApiSchoolHelper.ListenRequest<List<Student>> listenStudents = new ApiSchoolHelper.ListenRequest<List<Student>>() {
        @Override
        public void onSuccess(List<Student> result) {
            students = result;
        }

        @Override
        public void onError() {

        }
    };

    private ApiSchoolHelper.ListenRequest<List<Task>> listenTasks =  new ApiSchoolHelper.ListenRequest<List<Task>>() {
        @Override
        public void onSuccess(List<Task> result) {
            Log.i(TAG, "onSuccess: getTasks: " + result);
            tasks = result;
            getMvpView().setTaskList(tasks);
            getDataManager().insertProviderTask(tasks);
        }

        @Override
        public void onError() {

        }
    };

    private ApiSchoolHelper.ListenRequest<Task> listenUpdateTasks = new ApiSchoolHelper.ListenRequest<Task>() {
        @Override
        public void onSuccess(Task result) {
            Log.i(TAG, "onSuccess: listenUpdateTasks: " + result);
            getDataManager().getTasks(listenTasks);
        }

        @Override
        public void onError() {
            getDataManager().getTasks(listenTasks);
        }
    };
}
