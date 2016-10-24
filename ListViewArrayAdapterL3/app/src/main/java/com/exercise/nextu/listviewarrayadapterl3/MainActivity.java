package com.exercise.nextu.listviewarrayadapterl3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.exercise.nextu.listviewarrayadapterl3.adapter.DiaHorarioAdapter;
import com.exercise.nextu.listviewarrayadapterl3.model.DiaHorario;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView horario;
    private TextView txtOpcion;
    private String[] asignaturas;
    private String[] dias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOpcion =(TextView)findViewById(R.id.txt_opcion);

        asignaturas = getResources().getStringArray(R.array.horario_clases);
        dias = getResources().getStringArray(R.array.dias_semana);


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, asignaturas);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dias_semana, android.R.layout.simple_expandable_list_item_1);

        DiaHorarioAdapter adapter = new DiaHorarioAdapter(this, DiaHorario.createArrayList(asignaturas, dias));

        horario = (ListView) findViewById(R.id.list_of_items);
        horario.setAdapter(adapter);
        horario.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        txtOpcion.setText("Opcion: " + asignaturas[position]);
    }
}
