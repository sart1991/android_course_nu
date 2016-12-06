package com.example.sergioalejandro.evaluacionfinal4.views.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
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
        x2 = 100;
        y2 = 100;
        form = Form.RECTANGLE;
        firstRun = true;
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
                break;
            case OVAL:
            case RECTANGLE:
                canvas.drawRect(x1, y1, x2, y2, paint);
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
                xy = canvasWidth / 3;
                break;
            case SQUARE:
                x1 = canvasWidth / 3;
                y1 = canvasHeight / 3;
                xy = x1 * 2;
                break;
            case OVAL:
            case RECTANGLE:
                x1 = canvasWidth / 3;
                y1 = canvasHeight /3;
                x2 = x1 * 2;
                y2 = y1 * 2;
                break;
        }
    }

    public enum Form {
        CIRCLE, OVAL, SQUARE, RECTANGLE
    }
}
