package com.exercises.sart1991.backgroundtasks.ui.activity.json;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;
import com.exercises.sart1991.backgroundtasks.ui.base.BaseActivity;
import com.exercises.sart1991.backgroundtasks.ui.subview.usercard.UserCardSubView;

import java.util.List;

public class JsonActivity extends BaseActivity implements JsonMvpView {

    private static final String TAG = JsonActivity.class.getSimpleName();
    private static final JsonMvpPresenter<JsonMvpView> PRESENTER = new JsonPresenter<>();

    private Toolbar toolbar;
    private UserCardSubView<JsonMvpView> userCardSubView;
    private RecyclerView recyclerUserCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        initializeComponents();
        PRESENTER.onAttach(this);
        userCardSubView.onAttach(this);
    }

    @Override
    protected void initializeComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userCardSubView = new UserCardSubView<>();
        recyclerUserCards = (RecyclerView) findViewById(R.id.recycler_json_content);

        //bind with components
        LinearLayoutManager manager =  new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false
        );
        recyclerUserCards.setLayoutManager(manager);
        recyclerUserCards.setAdapter(userCardSubView.getUserCardAdapter());

    }

    @Override
    public void setUserCards(List<User> users) {
        userCardSubView.setUsers(users);
    }

    public void onClickButtonGet(View view) {
        Log.i(TAG, "onClickButtonGet: ");
        PRESENTER.loadUserCards();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PRESENTER.onDetach();
        userCardSubView.onDetach();
    }
}
