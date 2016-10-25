package com.exercise.nextu.codificacionadapterslistviewl3.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.exercise.nextu.codificacionadapterslistviewl3.R;
import com.exercise.nextu.codificacionadapterslistviewl3.models.Cancion;

import java.util.List;

/**
 * Created by SergioAlejandro on 24/10/2016.
 */

public class CancionesAdapter extends ArrayAdapter<Cancion> {

    public CancionesAdapter(Context context, List<Cancion> objetos) {
        super(context, R.layout.list_canciones_template, objetos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_canciones_template, null);

        TextView txtCanncionNombre = (TextView) item.findViewById(R.id.txt_cancion_nombre);
        TextView txtCancionArtista = (TextView) item.findViewById(R.id.txt_cancion_artista);

        txtCanncionNombre.setText(getItem(position).getNombre());
        txtCancionArtista.setText(getItem(position).getArtista());

        return item;
    }
}
