package com.example.sergioalejandro.evaluacionfinal4.views.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
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
    private float radius;

    public Figure(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        x2 = 100;
        y2 = 100;
        xy = (x2 + y2) / 2;
        form = Form.CIRCLE;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (form) {
            case CIRCLE:
                canvas.drawCircle(x1, y1, xy, paint);
                break;
            case OVAL:
                canvas.drawOval(x1, y1, x2, y2, paint);
                break;
            case SQUARE:
                canvas.drawRect(x1,y1, x2, x2, paint);
                break;
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
                xy = (x2 + y2) / 2;
                break;
        }
        invalidate();
        return true;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public enum Form {
        CIRCLE, OVAL, SQUARE, RECTANGLE
    }
}
