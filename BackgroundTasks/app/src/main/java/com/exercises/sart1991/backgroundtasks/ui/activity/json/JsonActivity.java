package com.exercises.sart1991.backgroundtasks.ui.activity.json;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.ui.base.BaseActivity;

public class JsonActivity extends BaseActivity implements JsonMvpView {

    private static final JsonMvpPresenter<JsonMvpView> PRESENTER = new JsonPresenter<>();

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        initializeComponents();
        PRESENTER.onAttach(this);
    }

    @Override
    protected void initializeComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
