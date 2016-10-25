package com.exercise.nextu.codificacionadapterslistviewl3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent();
    }

    public void onAlarmaButtonClicked(View view) {
        intent.setClass(this, AlarmaActivity.class);
        startActivity(intent);
    }
}
