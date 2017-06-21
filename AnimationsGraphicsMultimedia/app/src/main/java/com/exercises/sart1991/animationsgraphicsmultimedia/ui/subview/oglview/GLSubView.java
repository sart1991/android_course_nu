package com.exercises.sart1991.animationsgraphicsmultimedia.ui.subview.oglview;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.exercises.sart1991.animationsgraphicsmultimedia.ui.base.BaseSubView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by sart1 on 6/21/2017.
 */

public class GLSubView<C extends GlMvpSubView.Callback>
        extends BaseSubView<C> implements GlMvpSubView<C> {

    private CustomGlView mCustomGlView;

    @Override
    public void onAttach(C callback) {
        super.onAttach(callback);
        mCustomGlView = new CustomGlView(getViewContext());
    }

    @Override
    public GLSurfaceView getCustomGlSurfaceView() {
        return mCustomGlView;
    }

    private class CustomGlView extends GLSurfaceView {

        private final CustomGlRenderer mRenderer;

        public CustomGlView(Context context) {
            super(context);

            //Create OpenGL ES 2.0 context
            setEGLContextClientVersion(2);

            mRenderer = new CustomGlRenderer();

            //Set renderer for drawing on the GlSurfaceView
            setRenderer(mRenderer);

            // render the view only when there is a change in the drawing data
            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        }
    }

    private class CustomGlRenderer implements GLSurfaceView.Renderer {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //set the background frame color
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            //redraw background color
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        }
    }

    private class Triangle {

        private FloatBuffer vertexBuffer;

        //number of coordinates per vertex in this array
        static final int COORDS_PER_VERTEX = 3;
        float triangleCoords[] = {
                0.0f, 0.622008459f, 0.0f,
                -0.5f, -0.311004243f, 0.0f,
                0.5f, -0.311004243f, 0.0f
        };

        // set color with red, green, blue and alpha
        float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

        public  Triangle() {
            //number of coordinate values * 4 bytes per float
            ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);

            //use the device hardware's native byte order
            bb.order(ByteOrder.nativeOrder());

            // create a floating point buffer from the ByteBuffer
            vertexBuffer = bb.asFloatBuffer();
            // add the coordinates to the FloatBuffer
            vertexBuffer.put(triangleCoords);
            // set the buffer to read the first coordinate
            vertexBuffer.position(0);
        }
    }
}
