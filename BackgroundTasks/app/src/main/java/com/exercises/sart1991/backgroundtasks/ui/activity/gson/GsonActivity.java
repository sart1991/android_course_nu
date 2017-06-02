package com.exercises.sart1991.backgroundtasks.ui.activity.gson;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;
import com.exercises.sart1991.backgroundtasks.ui.base.BaseActivity;
import com.exercises.sart1991.backgroundtasks.ui.subview.usercard.UserCardMvpSubView;
import com.exercises.sart1991.backgroundtasks.ui.subview.usercard.UserCardSubView;

import java.util.List;

public class GsonActivity extends BaseActivity implements GsonMvpView {

    private static final String TAG = GsonActivity.class.getSimpleName();
    private static final GsonMvpPresenter<GsonMvpView> PRESENTER = new GsonPresenter<>();

    private Toolbar toolbar;
    private RecyclerView recyclerUserCards;
    private UserCardMvpSubView<UserCardMvpSubView.Callback> userCardMvpSubView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        initializeComponents();
        PRESENTER.onAttach(this);
        userCardMvpSubView.onAttach(this);
        recyclerUserCards = (RecyclerView) findViewById(R.id.recycler_gson_content);

        //bind with elements
        LinearLayoutManager manager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false
        );

        recyclerUserCards.setLayoutManager(manager);
        recyclerUserCards.setAdapter(userCardMvpSubView.getUserCardAdapter());
    }

    @Override
    protected void initializeComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userCardMvpSubView = new UserCardSubView<>();

    }

    public void onClickButtonGet(View view) {
        PRESENTER.loadUsers();
    }

    public void onClickButtonGetFromString(View view) {
        PRESENTER.loadUsersFromString();
    }

    @Override
    public void setUsers(List<User> users) {
        userCardMvpSubView.setUsers(users);
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
