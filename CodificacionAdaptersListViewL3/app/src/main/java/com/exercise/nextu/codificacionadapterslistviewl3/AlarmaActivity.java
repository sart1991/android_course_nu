package com.exercise.nextu.codificacionadapterslistviewl3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.exercise.nextu.codificacionadapterslistviewl3.adapters.AlarmasAdapter;
import com.exercise.nextu.codificacionadapterslistviewl3.models.Alarma;

public class AlarmaActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        String[] arrayOfHorarios = getResources().getStringArray(R.array.horarios_alarmas);

        AlarmasAdapter adapter = new AlarmasAdapter(this, Alarma.createArrayList(arrayOfHorarios));

        listView = (ListView) findViewById(R.id.list_alarmas);

        listView.setAdapter(adapter);
    }
}
