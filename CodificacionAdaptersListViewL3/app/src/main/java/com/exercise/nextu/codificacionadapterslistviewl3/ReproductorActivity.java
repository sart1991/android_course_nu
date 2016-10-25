package com.exercise.nextu.codificacionadapterslistviewl3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.exercise.nextu.codificacionadapterslistviewl3.adapters.CancionesAdapter;
import com.exercise.nextu.codificacionadapterslistviewl3.models.Cancion;

import java.util.ArrayList;

public class ReproductorActivity extends AppCompatActivity {

    ListView listViewCanciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        ArrayList<Cancion> = Cancion.createArrayList(getResources().getStringArray())

        CancionesAdapter adapter = new CancionesAdapter(this, Cancion.createArrayList());

        listViewCanciones = (ListView) findViewById(R.id.list_canciones);
    }
}
