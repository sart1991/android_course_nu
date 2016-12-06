package com.example.sergioalejandro.evaluacionfinal4.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sergioalejandro.evaluacionfinal4.R;
import com.example.sergioalejandro.evaluacionfinal4.adapters.MyRecyclerAdapter;
import com.example.sergioalejandro.evaluacionfinal4.services.ManageDrawCards;
import com.example.sergioalejandro.evaluacionfinal4.views.custom.Figure;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler);
        recyclerView.setHasFixedSize(true);
        adapter = new MyRecyclerAdapter(new ManageDrawCards(this));
        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager)layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
