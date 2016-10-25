package com.exercise.nextu.codificacionadapterslistviewl3.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.exercise.nextu.codificacionadapterslistviewl3.R;
import com.exercise.nextu.codificacionadapterslistviewl3.models.Alarma;

import java.util.List;

/**
 * Created by Admin on 10/24/2016.
 */

public class AlarmasAdapter extends ArrayAdapter<Alarma> {

    public AlarmasAdapter(Context context, List<Alarma> objetos) {
        super(context, R.layout.list_alarmas_template, objetos);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_alarmas_template, null);

        TextView txtHora = (TextView) item.findViewById(R.id.txt_hora_alarma);
        final TextView txtEstado = (TextView) item.findViewById(R.id.txt_estado_alarma);
        Switch theSwitch = (Switch) item.findViewById(R.id.alarma_switch);

        theSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                txtEstado.setText(getItem(position).switchEstado(isChecked));
            }
        });

        txtHora.setText(getItem(position).getHora());
        txtEstado.setText(getItem(position).getEstado());

        return item;
    }
}
