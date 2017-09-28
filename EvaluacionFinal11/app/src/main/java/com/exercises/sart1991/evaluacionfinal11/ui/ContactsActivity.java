package com.exercises.sart1991.evaluacionfinal11.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.exercises.sart1991.evaluacionfinal11.R;

public class ContactsActivity extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.exercises.sart1991.evaluacionfinal11.R.layout.activity_contacts);
        setUp();
    }

    private void setUp() {
        bindViews();
        requestPermissions();
        fab.setOnClickListener(fabListener);
    }

    private void bindViews() {
        fab = findViewById(R.id.fab);
    }

    private void requestPermissions() {
        int leer = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (leer != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.READ_CONTACTS},
                    43
                    );
        } else {
            Snackbar.make(
                    fab,
                    R.string.contacts_permission_granted,
                    BaseTransientBottomBar.LENGTH_LONG)
                    .show();
        }
    }

    private FloatingActionButton.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            gotoContentActivity();
        }
    };

    private void gotoContentActivity() {
        startActivity(new Intent(this, ContentActivity.class));
    }
}
