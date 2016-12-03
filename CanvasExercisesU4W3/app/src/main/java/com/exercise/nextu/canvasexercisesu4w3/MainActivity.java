package com.exercise.nextu.canvasexercisesu4w3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paper paper = new Paper(this);
        setContentView(paper);
    }
}

class Paper extends View {

    private Context context;
    private float x;
    private float y;
    private int id;
    private Path dPath = new Path();
    private Bitmap bitmap;


    public Paper(Context context) {
        super(context);
        this.context = context;
        bitmap =  BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawPaint(paint);

        paint.setColor(Color.WHITE);

        canvas.drawOval(0, 0, canvasWidth, canvasHeight, paint);

        Toast.makeText(context, "Canvas", Toast.LENGTH_LONG).show();

        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(10, 10);
        path.lineTo(canvasWidth, 1);

        float[] patron = {10, 10};
        DashPathEffect dashPathEffect = new DashPathEffect(patron, 0);

        paint.setPathEffect(dashPathEffect);
        path.offset(0, 40);

        canvas.drawPath(path, paint);

        paint.setStrokeWidth(4);
        paint.setColor(Color.RED);


        canvas.drawBitmap(bitmap, 0, 0, paint);
        if (id == 1) {
            dPath.moveTo(x, y);
        } else if (id == 2) {
            dPath.lineTo(x, y);
        }
        canvas.drawPath(dPath, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                id = 1;
                break;
            case MotionEvent.ACTION_MOVE:
                id = 2;
                break;
        }
        invalidate();
        return true;
    }
}
