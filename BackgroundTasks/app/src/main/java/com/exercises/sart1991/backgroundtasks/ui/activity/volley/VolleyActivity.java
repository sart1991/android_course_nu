package com.exercises.sart1991.backgroundtasks.ui.activity.volley;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.ui.base.BaseActivity;

public class VolleyActivity extends BaseActivity implements VolleyMvpView {

    private static final String TAG = VolleyActivity.class.getSimpleName();
    private static final VolleyMvpPresenter<VolleyMvpView> PRESENTER = new VolleyPresenter<>();

    private Toolbar toolbar;
    private TextView txtContent;
    private EditText editId, editUsername, editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        initializeComponents();
        PRESENTER.onAttach(this);
    }

    @Override
    protected void initializeComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtContent = (TextView) findViewById(R.id.textView_volley_content);
        editId = (EditText) findViewById(R.id.editText_volley_id);
        editUsername = (EditText) findViewById(R.id.editText_volley_username);
        editEmail = (EditText) findViewById(R.id.editText_volley_email);
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    public void onClickButtonGet(View view) {
        PRESENTER.clickButtonGet();
    }

    public void onClickButtonPost(View view) {
        PRESENTER.clickButtonPost();
    }

    public void onClickButtonPut(View view) {
        PRESENTER.clickButtonPut();
    }

    public void onClickButtonDelete(View view) {
        PRESENTER.clickButtonDelete();
    }

    @Override
    public void showResult(String result) {
        txtContent.setText(result);
    }

    @Override
    public String getId() {
        return editId.getText().toString();
    }

    @Override
    public String getUsername() {
        return editUsername.getText().toString();
    }

    @Override
    public String getEmail() {
        return editEmail.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PRESENTER.onDetach();
    }
}
