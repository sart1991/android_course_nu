package com.exercises.sart1991.animationsgraphicsmultimedia.ui.subview.oglview;

import android.opengl.GLSurfaceView;

import com.exercises.sart1991.animationsgraphicsmultimedia.ui.base.MvpSubView;

/**
 * Created by sart1 on 6/21/2017.
 */

public interface GlMvpSubView<C extends GlMvpSubView.Callback> extends MvpSubView<C> {

    GLSurfaceView getCustomGlSurfaceView();

    interface Callback extends MvpSubView.Callback {

    }
}
