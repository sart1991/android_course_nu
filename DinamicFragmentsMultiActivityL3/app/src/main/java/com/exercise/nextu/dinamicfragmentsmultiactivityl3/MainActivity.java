package com.exercise.nextu.dinamicfragmentsmultiactivityl3;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private DiasProgramacion diasProgramacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        diasProgramacion = new DiasProgramacion(getSupportFragmentManager());
        viewPager.setAdapter(diasProgramacion);
    }


}
