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

import java.io.Serializable;

/**
 * Created by SergioAlejandro on 4/12/2016.
 */

public class Figure extends View implements Serializable{

    private Form form;
    private Paint paint;
    private Path path;
    private Path pathShapes;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private float x3;
    private float y3;
    private float xs;
    private float ys;
    private float xy1;
    private boolean firstRun;

    public Figure(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        path = new Path();
        pathShapes = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (firstRun) {drawThumbnail(canvas);paint.setStyle(Paint.Style.STROKE);}
        switch (form) {
            case CIRCLE:
                pathShapes.addCircle(x1, y1, xy1, Path.Direction.CCW);
                break;
            case SQUARE:
                pathShapes.addRect(x1, y1, xs, ys, Path.Direction.CCW);
                break;
            case OVAL:
                pathShapes.addOval(x1, y1, x2, y2, Path.Direction.CCW);
                break;
            case RECTANGLE:
                pathShapes.addRect(x1, y1, x2, y2, Path.Direction.CCW);
                break;
        }
        if (form == Form.FREE) {
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, paint);
        } else {
            canvas.drawPath(pathShapes, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                xy1 = 100;
                xs = x1 + 200;
                ys = y1 + 200;
                x2 = x1 + 200;
                y2 = y1 + 120;
                path.moveTo(x1, y1);
                break;
            case MotionEvent.ACTION_MOVE:
                x3 = event.getX();
                y3 = event.getY();
                path.lineTo(x3, y3);
                break;
        }
        invalidate();
        return true;
    }

    public void setForm(Form form, boolean firstRun) {
        this.form = form;
        this.firstRun = firstRun;
    }

    public Form getForm() {
        return this.form;
    }

    private void drawThumbnail(Canvas canvas) {
        float canvasWidth = canvas.getWidth();
        float canvasHeight = canvas.getHeight();
        switch (form) {
            case CIRCLE:
                x1 = canvasWidth / 2;
                y1 = canvasHeight / 2;
                xy1 = canvasWidth / 4;
                break;
            case SQUARE:
                x1 = canvasWidth / 3;
                y1 = canvasHeight / 3;
                xs = x1 * 2;
                ys = x1 * 2;
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

    public enum Form implements Serializable{
        CIRCLE, OVAL, SQUARE, RECTANGLE, FREE
    }
}
