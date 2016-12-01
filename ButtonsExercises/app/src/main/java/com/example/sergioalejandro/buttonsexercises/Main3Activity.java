package com.example.sergioalejandro.buttonsexercises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sergioalejandro.buttonsexercises.recyclers.RecyclerViewAdapter;

public class Main3Activity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recylcler_view);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
