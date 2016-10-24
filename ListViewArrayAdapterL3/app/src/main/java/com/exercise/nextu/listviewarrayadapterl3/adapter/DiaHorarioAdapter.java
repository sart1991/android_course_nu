package com.exercise.nextu.listviewarrayadapterl3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.exercise.nextu.listviewarrayadapterl3.R;
import com.exercise.nextu.listviewarrayadapterl3.model.DiaHorario;

import java.util.List;

/**
 * Created by Admin on 10/24/2016.
 */

public class DiaHorarioAdapter extends ArrayAdapter<DiaHorario> {

    public DiaHorarioAdapter(Context context, List<DiaHorario> objetos) {
        super(context, R.layout.list_template, objetos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_template, null);

        TextView txtTitulo = (TextView) item.findViewById(R.id.list_title);
        TextView txtSubtitle = (TextView) item.findViewById(R.id.list_subtitle);

        txtTitulo.setText(getItem(position).getAsignatura());
        txtSubtitle.setText(getItem(position).getDia());

        return item;
    }
}
