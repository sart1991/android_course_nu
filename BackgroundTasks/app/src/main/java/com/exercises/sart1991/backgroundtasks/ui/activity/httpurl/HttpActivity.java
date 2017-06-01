package com.exercises.sart1991.backgroundtasks.ui.activity.httpurl;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.ui.base.BaseActivity;

public class HttpActivity extends BaseActivity implements HttpMvpView {

    private static final HttpMvpPresenter<HttpMvpView> PRESENTER = new HttpPresenter<>();

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Dialog dialogProgress;
    private TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        initializeComponents();
        PRESENTER.onAttach(this);
    }

    @Override
    protected void initializeComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        txtContent = (TextView) findViewById(R.id.textView_http_content);
    }

    @Override
    public void showProgressDialogForNetwork(int message) {
        dialogProgress = makeProgressDialog(getString(message));
        dialogProgress.show();
    }

    @Override
    public boolean dialogForNetworkIsShowing() {
        return dialogProgress.isShowing();
    }

    @Override
    public void dismissDialogForNetwork() {
        dialogProgress.dismiss();
    }

    private Dialog makeProgressDialog(String message) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(message);
        return dialog;
    }

    @Override
    public void showResult(String result) {
        txtContent.setText(result);
    }

    public void onClickFab(View view) {
        PRESENTER.addUser();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    public void onClickButtonLoad(View view) {
        PRESENTER.getUsers();
    }
}
