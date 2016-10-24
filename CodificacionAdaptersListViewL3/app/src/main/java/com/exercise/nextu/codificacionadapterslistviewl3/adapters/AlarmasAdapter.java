package com.exercise.nextu.codificacionadapterslistviewl3.adapters;

import android.content.Context;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.exercise.nextu.codificacionadapterslistviewl3.R;
import com.exercise.nextu.codificacionadapterslistviewl3.model.Alarma;

import java.util.List;

/**
 * Created by Admin on 10/24/2016.
 */

public class AlarmasAdapter extends ArrayAdapter {

    public AlarmasAdapter(Context context, List<Alarma> listAlarmas) {
        super(context, R.layout.list_alarmas_template, listAlarmas);
    }


}
