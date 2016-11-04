package com.example.sergioalejandro.fragmentactivitycommunication;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MensajeFragment mensajeFragment = new MensajeFragment();

        Bundle arguments = new Bundle();
        arguments.putString("cadena", "Bienvenido a Next University");
        arguments.putInt("entero", 2016);
        arguments.putFloat("flotante", 3.1416F);

        mensajeFragment.setArguments(arguments);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.activity_main, mensajeFragment);
        fragmentTransaction.commit();
    }
}
