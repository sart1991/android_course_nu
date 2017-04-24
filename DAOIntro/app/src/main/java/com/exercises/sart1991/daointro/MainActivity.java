package com.exercises.sart1991.daointro;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.exercises.sart1991.daointro.dao.DaoPerson;
import com.exercises.sart1991.daointro.models.Person;

public class MainActivity extends AppCompatActivity implements DaoPerson{

    private static final String TAG = MainActivity.class.getName();
    private EditText editName;
    private EditText editLastname;
    private SharedPreferences preferenceWithManager;
//    private SharedPreferences preferenceWithName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = (EditText) findViewById(R.id.edit_name);
        editLastname = (EditText) findViewById(R.id.edit_lastname);
        preferenceWithManager = PreferenceManager.getDefaultSharedPreferences(this);
//        preferenceWithName = getSharedPreferences("MyPreference", MODE_PRIVATE);
    }

    public void onClickSubmit(View view) {
        String name = String.valueOf(editName.getText());
        String lastname = String.valueOf(editLastname.getText());
        try {
            insert(new Person(name, lastname));
            Toast.makeText(this, "Se ha enviado la informacion", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            Toast.makeText(this, "Envio fallido", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void insert(Person person) throws Exception {
        preferenceWithManager.edit().putString("NAME", person.getName())
                .putString("LASTNAME", person.getLastname())
                .apply();
    }

    @Override
    public void modify(Person person) throws Exception {

    }

    @Override
    public void delete(Person person) throws Exception {

    }

    @Override
    public void getAll() throws Exception {

    }
}
