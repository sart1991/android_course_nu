package com.exercises.sart1991.evaluacionfinal8p.ui.activity.main;

import android.os.Handler;
import android.util.Log;

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.ApiSchoolHelper;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Professor;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BasePresenter;

import java.util.List;

/**
 * Created by sart1 on 6/7/2017.
 */

public class MainPresenter<V extends MainMvpView>
        extends BasePresenter<V> implements MainMvpPresenter<V> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Override
    public void welcome() {
        getDataManager().checkProfessorLogin(listenLogin);
    }

    @Override
    public void clickDrawerMenu(int itemId) {
        Log.i(TAG, "clickDrawerMenu: ");
        switch (itemId) {
            case R.id.item_mainDrawer_courses:
                getDataManager().getCourses(listenCourses);
                break;
            case R.id.item_mainDrawer_students:
                getDataManager().getStudents(listenStudents);
                break;
            case R.id.item_mainDrawer_tasks:
                getDataManager().getTasks(listenTasks);
        }
    }

    @Override
    public void clickTaskOptionsMenu(int itemId, int taskId) {
        switch (itemId) {
            case R.id.item_cardTaskOptions_edit:

                break;
            case R.id.item_cardTaskOptions_delete:
                getDataManager().deleteTask(taskId, listenDeleteTask);
                break;
        }
    }

    @Override
    public void loadCourseCards() {
        Log.i(TAG, "loadCourseCards: ");
        getDataManager().getCourses(listenCourses);
    }

    @Override
    public void loadStudentCards() {
        getDataManager().getStudents(listenStudents);
    }

    @Override
    public void loadTaskCards() {
        getDataManager().getTasks(listenTasks);
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
        }

        @Override
        public void onError() {
            Log.i(TAG, "onError: error");
            if (getDataManager().getToken() == null || "".equals(getDataManager().getToken())) {
                getMvpView().gotoLogin();
                return;
            }
            if(!getMvpView().checkInternetConnection()) {
                getMvpView().onError(R.string.error_noInterentConnection, null);
                return;
            } else {
                getMvpView().onError(R.string.error_serverError, null);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getMvpView().gotoLogin();
                    }
                }, 1000);
                return;
            }
        }
    };

    private ApiSchoolHelper.ListenRequest<List<Course>> listenCourses =  new ApiSchoolHelper.ListenRequest<List<Course>>() {
        @Override
        public void onSuccess(List<Course> result) {
            Log.i(TAG, "onSuccess: courseCards: " + result);
            getMvpView().showCourseCards(result);
        }

        @Override
        public void onError() {
            Log.i(TAG, "onError: listenCourses");
        }
    };

    private ApiSchoolHelper.ListenRequest<List<Student>> listenStudents = new ApiSchoolHelper.ListenRequest<List<Student>>() {
        @Override
        public void onSuccess(List<Student> result) {
            getMvpView().showStudentCards(result);
        }

        @Override
        public void onError() {

        }
    };

    private ApiSchoolHelper.ListenRequest<List<Task>> listenTasks =  new ApiSchoolHelper.ListenRequest<List<Task>>() {
        @Override
        public void onSuccess(List<Task> result) {
            getMvpView().showTaskCards(result);
        }

        @Override
        public void onError() {

        }
    };

    private ApiSchoolHelper.ListenRequest<Task> listenUpdateTask = new ApiSchoolHelper.ListenRequest<Task>() {
        @Override
        public void onSuccess(Task result) {

        }

        @Override
        public void onError() {

        }
    };

    private ApiSchoolHelper.ListenRequest<Task> listenDeleteTask = new ApiSchoolHelper.ListenRequest<Task>() {
        @Override
        public void onSuccess(Task result) {

        }

        @Override
        public void onError() {

        }
    };
}
