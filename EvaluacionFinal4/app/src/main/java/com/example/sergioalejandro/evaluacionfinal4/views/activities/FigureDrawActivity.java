package com.example.sergioalejandro.evaluacionfinal4.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sergioalejandro.evaluacionfinal4.R;


public class FigureDrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure_draw);
        //Log for test
        Log.i("Test", "Second activity tested");
    }
}