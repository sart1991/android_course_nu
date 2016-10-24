package com.exercise.nextu.activitiescodificiacion2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextBase;
    private EditText editTextHeight;
    public static final String BASE_KEY = "number_base";
    public static final String HEIGHT_KEY = "number_height";
    private static String TAG = "Activity_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextBase = (EditText) findViewById(R.id.edit_txt_base);
        editTextHeight = (EditText) findViewById(R.id.edit_txt_height);
    }

    public void onSiguienteClicked (View view) {
        Intent intent = new Intent(this, AreaActivity.class);
        int base = Integer.parseInt(editTextBase.getText().toString());
        int height = Integer.parseInt(editTextHeight.getText().toString());
        intent.putExtra(BASE_KEY, base);
        intent.putExtra(HEIGHT_KEY, height);
        startActivity(intent);
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
}
