package com.exercise.nextu.stylesexercises;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView textView;
    private View viewColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.color_seek_bar);
        textView = (TextView) findViewById(R.id.textView);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        viewColor = findViewById(R.id.color_target_view);
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            int color = Color.HSVToColor(new float[]{(360f*i)/100,1,1});
            textView.setText("Color: " + color);
            viewColor.setBackgroundColor(color);
            getWindow().setStatusBarColor(color);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void onClickBtnTema1(View view) {
        restartActivity(R.style.AppTheme);
    }

    public void onClickBtnTema2(View view) {
        restartActivity(R.style.AppTheme2);
    }

    private void restartActivity(int tema) {
        this.finish();
        startActivity(new Intent(this, MainActivity.class));
        this.setTheme(tema);
    }
}
