package com.example.sergioalejandro.evaluacionfinal4.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sergioalejandro.evaluacionfinal4.R;
import com.example.sergioalejandro.evaluacionfinal4.views.custom.Figure;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(new Figure(this, Figure.Form.SQUARE));
    }
}
