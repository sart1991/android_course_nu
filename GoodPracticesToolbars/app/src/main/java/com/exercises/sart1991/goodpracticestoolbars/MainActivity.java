package com.exercises.sart1991.goodpracticestoolbars;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int teal;
    private int lime;
    private int blue;
    private int primary;
    private int tealDark;
    private int limeDark;
    private int blueDark;
    private int primaryDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        teal = ContextCompat.getColor(getBaseContext(), R.color.teal);
        lime = ContextCompat.getColor(getBaseContext(), R.color.lime);
        blue = ContextCompat.getColor(getBaseContext(), R.color.blue);
        primary = ContextCompat.getColor(getBaseContext(), R.color.colorPrimary);
        tealDark = ContextCompat.getColor(getBaseContext(), R.color.teal_dark);
        limeDark = ContextCompat.getColor(getBaseContext(), R.color.lime_dark);
        blueDark = ContextCompat.getColor(getBaseContext(), R.color.blue_dark);
        primaryDark = ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark);

        Button btnTeal = (Button) findViewById(R.id.btn_teal);
        Button btnLime = (Button) findViewById(R.id.btn_lime);
        Button btnBlue = (Button) findViewById(R.id.btn_blue);
        Button btnDefault = (Button) findViewById(R.id.btn_default);

        Button btnGoSecond = (Button) findViewById(R.id.btn_go_second);

        btnTeal.setOnClickListener(clickListener);
        btnLime.setOnClickListener(clickListener);
        btnBlue.setOnClickListener(clickListener);
        btnDefault.setOnClickListener(clickListener);

        btnGoSecond.setOnClickListener(clickGoSecond);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int color = primary;
            int colorDark = primaryDark;
            switch (v.getId()) {
                case R.id.btn_teal:
                    color = teal;
                    colorDark = tealDark;
                    break;
                case R.id.btn_lime:
                    color = lime;
                    colorDark = limeDark;
                    break;
                case R.id.btn_blue:
                    color = blue;
                    colorDark = blueDark;
                    break;
            }
            modifyThemeColor(color, colorDark);
        }
    };

    private View.OnClickListener clickGoSecond = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    };

    private void modifyThemeColor(int color, int colorDark) {
        Drawable drawable = new ColorDrawable(color);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(drawable);
        }

        if(Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(colorDark);
        }
    }
}
