package com.exercise.nextu.activitiescodificiacion2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AreaActivity extends AppCompatActivity {

    private TextView textArea;
    private int area, base, height;
    private Intent intent;
    private final String TAG = "Activity_2";
    private final String AREA_KEY = "area_result_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        textArea = (TextView) findViewById(R.id.text_area);
        intent = getIntent();
        base = intent.getIntExtra(MainActivity.BASE_KEY, 0);
        height = intent.getIntExtra(MainActivity.HEIGHT_KEY, 0);
    }


    public void onTriangleAreaClicked(View view) {

        area = (base * height) / 2;
        displayAreaResult(area);
    }

    public void onSquareAreaClicked(View view) {
        area = (base * height);
        displayAreaResult(area);
    }

    private void displayAreaResult(int areaResult) {
        textArea.setText("The area is: " + areaResult);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.wtf(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.wtf(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.wtf(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.wtf(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.wtf(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.wtf(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(AREA_KEY, textArea.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textArea.setText(savedInstanceState.getString(AREA_KEY));
    }
}
