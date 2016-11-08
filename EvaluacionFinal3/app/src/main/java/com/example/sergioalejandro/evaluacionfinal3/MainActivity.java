package com.example.sergioalejandro.evaluacionfinal3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sergioalejandro.evaluacionfinal3.communication.ICommunication;

public class MainActivity extends AppCompatActivity implements ICommunication.IInstrumentsList{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null) {
            ListInstrumentsFragment fragment = new ListInstrumentsFragment();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    public void onInstrumentSelected(int position) {

    }
}
