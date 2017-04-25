package com.exercises.sart1991.daointro;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.exercises.sart1991.daointro.dao.DaoPerson;
import com.exercises.sart1991.daointro.models.Person;

public class MainActivity extends AppCompatActivity implements DaoPerson{

    public static final String TAG = MainActivity.class.getName();
    private EditText editName;
    private EditText editLastName;
    private SharedPreferences preferenceWithManager;
    private final String KEY_NAME = "NAME";
    private final String KEY_LASTNAME = "LASTNAME";
//    private SharedPreferences preferenceWithName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = (EditText) findViewById(R.id.edit_name);
        editLastName = (EditText) findViewById(R.id.edit_lastname);
        preferenceWithManager = PreferenceManager.getDefaultSharedPreferences(this);
//        preferenceWithName = getSharedPreferences("MyPreference", MODE_PRIVATE);
    }

    public void onClickSubmit(View view) {
        String name = String.valueOf(editName.getText());
        String lastname = String.valueOf(editLastName.getText());
        try {
            insert(new Person(name, lastname));
            Toast.makeText(this,
                    "Se han guardado los datos: \n" +
                            "- Nombre: " + getPerson().getName()
                            + "\n- Last name: " + getPerson().getLastname(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            Toast.makeText(this, "Envio fallido", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void insert(Person person) throws Exception {
        preferenceWithManager.edit().putString(KEY_NAME, person.getName())
                .putString(KEY_LASTNAME, person.getLastname())
                .apply();
    }

    @Override
    public void modify(Person person) throws Exception {

    }

    @Override
    public void delete(Person person) throws Exception {

    }

    @Override
    public Person getPerson() throws Exception {
        String name = preferenceWithManager.getString(KEY_NAME, "fuck");
        String lastName = preferenceWithManager.getString(KEY_LASTNAME, "you");
        return new Person(name, lastName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            startActivity(new Intent(this, Main2Activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}


