package com.exercises.sart1991.evaluacionfinal9.ui.fragments.graphics;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by sart1 on 6/28/2017.
 */

public class CustomRenderer implements GLSurfaceView.Renderer {

    private Cube cube;
    private Context context;

    private static float degrees = 0;
    private static float speed = -1.2f;

    public CustomRenderer(Context context) {
        this.context = context;
        cube = new Cube();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        cube.loadTexture(gl, context);
        gl.glEnable(GL10.GL_TEXTURE_2D);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -0.5f);
        gl.glScalef(0.8f, 0.8f, 0.8f);
        gl.glRotatef(degrees, 1.0f, 1.0f, 0.0f);
        cube.draw(gl);
        degrees += speed;
    }
}

