package com.exercises.sart1991.evaluacionfinal8p.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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

import java.util.List;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final MainMvpPresenter<MainMvpView> presenter = new MainPresenter<>();

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private CourseCard courseCard;
    private StudentCard studentCard;
    private RecyclerView recycler;
    private TaskCard<TaskMvpSubView.Callback> taskCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.onAttach(this);
        presenter.welcome();
        setupComponents();
        courseCard.onAttach(this);
        studentCard.onAttach(this);
        taskCard.onAttach(this);
        presenter.loadCourseCards();
    }

    @Override
    protected void setupComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawerOpen, R.string.drawerClose
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
        navigationView.setCheckedItem(0);

        recycler = (RecyclerView) findViewById(R.id.recycler_main_cardsContainer);
        LinearLayoutManager manager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false
        );
        recycler.setLayoutManager(manager);

        courseCard = new CourseCard();
        studentCard = new StudentCard();
        taskCard = new TaskCard<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.clickSignOut(item.getItemId());
    }

    private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Log.i(TAG, "onNavigationItemSelected: navitemselected");
                    presenter.clickDrawerMenu(item.getItemId());
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            };

    @Override
    public void onClickTaskOptionsMenu(int itemId, int taskId) {
        presenter.clickTaskOptionsMenu(itemId, taskId);
    }

    @Override
    public void showCourseCards(List<Course> courses) {
        Log.i(TAG, "showCourseCards: ");
        courseCard.setCoursesList(courses);
        recycler.setAdapter(courseCard.getCoursesAdapter());
    }

    @Override
    public void showStudentCards(List<Student> students) {
        studentCard.setStudentsList(students);
        recycler.setAdapter(studentCard.getStudentsAdapter());
    }

    @Override
    public void showTaskCards(List<Task> tasks) {
        taskCard.setTasksList(tasks);
        recycler.setAdapter(taskCard.getTasksAdapter());
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
        presenter.onDetach();
        taskCard.onDetach();
    }
}
