package com.exercise.nextu.canvasexercisesu4w3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public Paper(Context context) {
        super(context);
        this.context = context;
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

    }
}
