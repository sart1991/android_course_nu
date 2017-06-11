package com.exercises.sart1991.evaluacionfinal8p.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8p.ui.activity.login.LoginActivity;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BaseActivity;
import com.exercises.sart1991.evaluacionfinal8p.ui.subview.course.CourseCard;
import com.exercises.sart1991.evaluacionfinal8p.ui.subview.student.StudentCard;
import com.exercises.sart1991.evaluacionfinal8p.ui.subview.task.TaskCard;
import com.exercises.sart1991.evaluacionfinal8p.ui.subview.task.TaskMvpSubView;
import com.exercises.sart1991.evaluacionfinal8p.ui.subview.taskdialog.TaskDialog;
import com.exercises.sart1991.evaluacionfinal8p.ui.subview.taskdialog.TaskDialogMvpSubView;

import java.util.List;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final MainMvpPresenter<MainMvpView> PRESENTER = new MainPresenter<>();

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private CourseCard courseCard;
    private StudentCard studentCard;
    private RecyclerView recycler;
    private NavigationView navigationView;
    private TextView txtProfessorName, txtProfessorEmail;
    private FloatingActionButton fab;
    private TaskCard<TaskMvpSubView.Callback> taskCard;
    private TaskDialog<TaskDialogMvpSubView.Callback> newTaskDialog, editTaskDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PRESENTER.onAttach(this);
        PRESENTER.welcome();
        setupComponents();
        courseCard.onAttach(this);
        studentCard.onAttach(this);
        taskCard.onAttach(this);
        newTaskDialog.onAttach(this);
        editTaskDialog.onAttach(this);
        PRESENTER.initLists();
    }

    @Override
    protected void setupComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawerOpen, R.string.drawerClose
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
        navigationView.setCheckedItem(0);

        View view = navigationView.getHeaderView(0);
        txtProfessorName = (TextView) view.findViewById(R.id.textView_mainHeader_name);
        txtProfessorEmail = (TextView) view.findViewById(R.id.textView_mainHeader_email);

        recycler = (RecyclerView) findViewById(R.id.recycler_main_cardsContainer);
        LinearLayoutManager manager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false
        );
        recycler.setLayoutManager(manager);

        courseCard = new CourseCard();
        studentCard = new StudentCard();
        taskCard = new TaskCard<>();

        newTaskDialog = new TaskDialog<>(R.string.dialogTask_positiveCreate);
        editTaskDialog = new TaskDialog<>(R.string.dialogTask_posititveEdit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return PRESENTER.clickSignOut(item.getItemId());
    }

    private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Log.i(TAG, "onNavigationItemSelected: navitemselected");
                    PRESENTER.clickDrawerMenu(item.getItemId());
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            };

    @Override
    public void setNavViewProfessorData(String name, String email) {
        txtProfessorName.setText(name);
        txtProfessorEmail.setText(email);
    }

    @Override
    public void onClickTaskOptionsMenu(int itemId, Task task) {
        PRESENTER.clickTaskOptionsMenu(itemId, task);
    }

    @Override
    public List<Course> getCourseList() {
        return courseCard.getCoursesList();
    }

    @Override
    public void setCourseList(List<Course> courses) {
        courseCard.setCoursesList(courses);
    }

    @Override
    public void showCourseCards() {
        Log.i(TAG, "showCourseCards: ");
        recycler.setAdapter(courseCard.getCoursesAdapter());
    }

    @Override
    public List<Student> getStudentList() {
        return studentCard.getStudentsList();
    }

    @Override
    public void setStudentList(List<Student> students) {
        studentCard.setStudentsList(students);
    }

    @Override
    public void showStudentCards() {
        recycler.setAdapter(studentCard.getStudentsAdapter());
    }

    @Override
    public List<Task> getTaskList() {
        return taskCard.getTasksList();
    }

    @Override
    public void setTaskList(List<Task> tasks) {
        taskCard.setTasksList(tasks);
    }

    @Override
    public void showTaskCards() {
        recycler.setAdapter(taskCard.getTasksAdapter());
    }

    @Override
    public void makeDialogTaskForProfessor(List<Student> students, List<Course> courses) {
        newTaskDialog.makeDialogTask(students, courses);
        editTaskDialog.makeDialogTask(students, courses);
    }

    @Override
    public void showDialogNewTask() {
        Log.i(TAG, "showDialogNewTask: main");
        newTaskDialog.showDialog();
    }

    @Override
    public void showDialogEditTask() {
        editTaskDialog.showDialog();
    }

    @Override
    public void fillDialogTask(int id, String name, String studentName,
                               String courseName, double grade) {
        editTaskDialog.fillDialog(id, name, studentName, courseName, grade);
    }

    @Override
    public void cleanDialogTask() {
        newTaskDialog.cleanDialog();
    }

    @Override
    public void onPositiveClick(int method, int id, String name,
                                int studentId, int courseId, String grade) {
        PRESENTER.clickPositiveDialogTask(
                method, id, name, studentId, courseId, grade
        );
    }

    @Override
    public void onCancel() {

    }

    public void onClickFab(View view) {
        PRESENTER.clickFab();
    }

    @Override
    public void setFabVisibility(int resVisible) {
        fab.setVisibility(resVisible);
    }

    @Override
    public void gotoLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PRESENTER.onDetach();
        courseCard.onDetach();
        studentCard.onDetach();
        taskCard.onDetach();
        newTaskDialog.onDetach();
        editTaskDialog.onDetach();
    }
}
