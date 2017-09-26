package com.exercises.sart1991.evaluacionfinal11;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sart1 on 9/25/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        new Thread(loadRunnable).start();
    }

    private Runnable loadRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gotoMainActivity();
            finish();
        }
    };

    private void gotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
