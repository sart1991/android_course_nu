package com.exercises.sart1991.backgroundtasks.ui.activity.provider;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.ui.base.BaseActivity;

public class ProviderActivity extends BaseActivity implements ProviderMvpView {

    private static final ProviderMvpPresenter<ProviderMvpView> PRESENTER = new ProviderPresenter<>();

    private Toolbar toolbar;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        initializeComponents();
        PRESENTER.onAttach(this);
        PRESENTER.generateProvider();
    }

    @Override
    protected void initializeComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtResult = (TextView) findViewById(R.id.textView_provider_result);
    }

    public void onClickButtonInsert(View view) {
        PRESENTER.insertData();
    }

    public void onClickButtonGetData(View view) {
        PRESENTER.getProviderData(getLoaderManager());
    }

    @Override
    public void setResult(String result) {
        txtResult.setText(result);
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
