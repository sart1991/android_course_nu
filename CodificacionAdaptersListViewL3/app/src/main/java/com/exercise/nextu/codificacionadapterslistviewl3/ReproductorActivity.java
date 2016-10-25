package com.exercise.nextu.codificacionadapterslistviewl3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListView;

import com.exercise.nextu.codificacionadapterslistviewl3.adapters.CancionesAdapter;
import com.exercise.nextu.codificacionadapterslistviewl3.models.Cancion;

import java.util.ArrayList;

public class ReproductorActivity extends AppCompatActivity {

    private EditText editCancionNombre;
    private EditText editCancionArtista;
    private ListView listViewCanciones;
    private ArrayList<Cancion> listCanciones;
    private CancionesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        listCanciones = new ArrayList<>();

        adapter = new CancionesAdapter(this, listCanciones);

        listViewCanciones = (ListView) findViewById(R.id.list_canciones);
        editCancionNombre = (EditText) findViewById(R.id.edit_cancion_nombre);
        editCancionArtista = (EditText) findViewById(R.id.edit_cancion_artista);

        listViewCanciones.setAdapter(adapter);
    }


    public void OnAgregarButtonClicked(View view) {
        String nombre = editCancionNombre.getText().toString();
        String artista = editCancionArtista.getText().toString();
        listCanciones.add(new Cancion(nombre, artista));

        adapter.notifyDataSetChanged();
    }
}
