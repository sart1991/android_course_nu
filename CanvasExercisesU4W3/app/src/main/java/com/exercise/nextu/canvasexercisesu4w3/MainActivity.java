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

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawPaint(paint);

        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();

        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        paint.setAntiAlias(true);

        canvas.drawText("Este es un text escrito en canvas", 50f, 150f, paint);
        canvas.drawLine(0,0, 640,640, paint);

        Toast.makeText(context, "Canvas", Toast.LENGTH_LONG).show();

    }
}
