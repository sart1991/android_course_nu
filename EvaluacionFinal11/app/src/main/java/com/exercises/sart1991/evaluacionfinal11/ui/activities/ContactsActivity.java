package com.exercises.sart1991.evaluacionfinal11.ui.activities;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.exercises.sart1991.evaluacionfinal11.R;
import com.exercises.sart1991.evaluacionfinal11.repository.model.LittleContact;
import com.exercises.sart1991.evaluacionfinal11.ui.adapters.ContactCardAdapter;

import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recycler;
    private ContactsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.exercises.sart1991.evaluacionfinal11.R.layout.activity_contacts);
        viewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        setUp();
    }

    private void setUp() {
        bindViews();
        requestPermissions();
        setUpViews();
    }

    private void bindViews() {
        fab = findViewById(R.id.fab);
        recycler = findViewById(R.id.recycler_contactsActivity_contacts);
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

    private void setUpViews() {
        fab.setOnClickListener(fabListener);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        initializeData();
    }

    private void initializeData() {
        viewModel.getContacts(this).observe(this, new Observer<List<LittleContact>>() {
            @Override
            public void onChanged(@Nullable List<LittleContact> contacts) {
                recycler.setAdapter(new ContactCardAdapter(contacts));
            }
        });
    }

    private FloatingActionButton.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        showProgressDialog();
        gotoContentActivity();
        }
    };

    private void showProgressDialog() {
        final Dialog dialog = ProgressDialog.show(this, "", "Guardando contactos", true, false);

        dialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        }, 2000);
    }

    private void gotoContentActivity() {
        startActivity(new Intent(this, ContentActivity.class));
    }
}
