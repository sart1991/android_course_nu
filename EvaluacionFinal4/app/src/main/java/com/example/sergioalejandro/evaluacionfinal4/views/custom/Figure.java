package com.example.sergioalejandro.evaluacionfinal4.views.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergioalejandro.evaluacionfinal4.R;

/**
 * Created by SergioAlejandro on 4/12/2016.
 */

public class Figure extends View {

    private Form form;
    private Paint paint;
    private Path path;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private float xy;
    private boolean firstRun;

    public Figure(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (firstRun) {drawThumbnail(canvas);}
        switch (form) {
            case CIRCLE:
                canvas.drawCircle(x1, y1, xy, paint);
                break;
            case SQUARE:
                canvas.drawRect(x1,y1, xy, xy, paint);
//                Log.i("Figure.class", "XY: " + xy);
                break;
            case OVAL:
                canvas.drawOval(x1, y1, x2, y2, paint);
                break;
            case RECTANGLE:
                canvas.drawRect(x1, y1, x2, y2, paint);
                break;
            case FREE:
                canvas.drawPath(path, paint);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                x2 = event.getX();
                y2 = event.getY();
                xy = (x2 + y2) / 3;
                break;
        }
        invalidate();
        return true;
    }

    public void setForm(Form form, boolean firstRun) {
        this.form = form;
        this.firstRun = firstRun;
    }

    private void drawThumbnail(Canvas canvas) {
        float canvasWidth = canvas.getWidth();
        float canvasHeight = canvas.getHeight();
        switch (form) {
            case CIRCLE:
                x1 = canvasWidth / 2;
                y1 = canvasHeight / 2;
                xy = canvasWidth / 4;
                break;
            case SQUARE:
                x1 = canvasWidth / 3;
                y1 = canvasHeight / 3;
                xy = x1 * 2;
//                Log.i("Figure.class", "XY: " + xy);
                break;
            case OVAL:
            case RECTANGLE:
                x1 = canvasWidth / 4;
                y1 = canvasHeight / 3;
                x2 = x1 * 3;
                y2 = y1 * 2;
                break;
            case FREE:
                path.moveTo(canvasWidth / 2, canvasHeight / 4);
                path.lineTo(canvasWidth * 3 / 4, canvasHeight * 2 / 3);
                path.lineTo(canvasWidth / 6, canvasHeight / 2);
                path.lineTo(canvasWidth / 2, canvasHeight / 4);
                break;
        }
    }

    public enum Form {
        CIRCLE, OVAL, SQUARE, RECTANGLE, FREE
    }
}
