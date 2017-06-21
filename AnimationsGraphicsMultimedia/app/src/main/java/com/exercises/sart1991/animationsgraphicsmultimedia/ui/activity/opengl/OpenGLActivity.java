package com.exercises.sart1991.animationsgraphicsmultimedia.ui.activity.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.exercises.sart1991.animationsgraphicsmultimedia.R;
import com.exercises.sart1991.animationsgraphicsmultimedia.ui.base.BaseActivity;
import com.exercises.sart1991.animationsgraphicsmultimedia.ui.subview.oglview.GLSubView;
import com.exercises.sart1991.animationsgraphicsmultimedia.ui.subview.oglview.GlMvpSubView;

public class OpenGLActivity extends BaseActivity implements OpenGLMvpView {

    private static final OpenGLMvpPresenter<OpenGLMvpView> PRESENTER = new OpenGLPresenter<>();

    private GLSurfaceView mGlView;
    private GLSubView<GlMvpSubView.Callback> mGlSubView = new GLSubView<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlSubView.onAttach(this);
        mGlView = mGlSubView.getCustomGlSurfaceView();
        setContentView(mGlView);
        PRESENTER.onAttach(this);
        setUp();
    }

    @Override
    public void setUp() {

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
