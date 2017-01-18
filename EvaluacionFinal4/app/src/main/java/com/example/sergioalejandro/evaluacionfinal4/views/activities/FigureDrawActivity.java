package com.example.sergioalejandro.evaluacionfinal4.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sergioalejandro.evaluacionfinal4.R;
import com.example.sergioalejandro.evaluacionfinal4.views.custom.Figure;


public class FigureDrawActivity extends AppCompatActivity {

    private Figure.Form form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure_draw);

        form = (Figure.Form) getIntent().getExtras().getSerializable(MainActivity.FIGURE_ACTIVITY_KEY);
        Figure figureView = (Figure)findViewById(R.id.view_figure);
        figureView.setForm(form, false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String message = "";
        switch (form) {
            case CIRCLE:
                message = getResources().getString(R.string.circle_subtitle);
                break;
            case SQUARE:
                message = getResources().getString(R.string.square_subtitle);
                break;
            case OVAL:
                message = getResources().getString(R.string.oval_subtitle);
                break;
            case RECTANGLE:
                message = getResources().getString(R.string.rectangle_subtitle);
                break;
            case FREE:
                message = getResources().getString(R.string.free_subtitle);
                break;
        }
        message += " " + getResources().getString(R.string.subtitle_complement);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
