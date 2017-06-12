package com.exercises.sart1991.evaluacionfinal8s.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.exercises.sart1991.evaluacionfinal8s.R;
import com.exercises.sart1991.evaluacionfinal8s.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8s.ui.activity.login.LoginActivity;
import com.exercises.sart1991.evaluacionfinal8s.ui.base.BaseActivity;
import com.exercises.sart1991.evaluacionfinal8s.ui.subview.taskcard.TaskCard;
import com.exercises.sart1991.evaluacionfinal8s.ui.subview.taskcard.TaskCardMvpSubview;

import java.util.List;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final MainMvpPresenter<MainMvpView> PRESENTER = new MainPresenter<>();

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TaskCardMvpSubview taskCardMvpSubview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PRESENTER.onAttach(this);
        PRESENTER.welcome();
        setupComponents();
        taskCardMvpSubview.onAttach(this);
    }

    @Override
    protected void setupComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_main_cardsContainer);
        LinearLayoutManager manager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false
        );
        recyclerView.setLayoutManager(manager);

        taskCardMvpSubview = new TaskCard();
        recyclerView.setAdapter(taskCardMvpSubview.getTasksAdapter());
    }

    @Override
    public void callProviderData() {
        PRESENTER.getProviderData(getLoaderManager());
    }

    @Override
    public void setListTasks(List<Task> tasks) {
        taskCardMvpSubview.setTasksList(tasks);
    }

    @Override
    public void setToolbarSubtitle(String subtitle) {
        toolbar.setSubtitle(subtitle);
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
    }
}
