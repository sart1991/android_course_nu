package com.exercises.sart1991.almacenamientodeobjetos;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    private TextView txtContent;
    private final String fileName = "person2.obj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtContent = (TextView) findViewById(R.id.txt_content);
    }

    public void onClickButtonSave(View view) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(openFileOutput(fileName, MODE_PRIVATE));
            oos.writeObject(new Person(123456, "Sergio", "Rojas"));
            oos.close();
        } catch (IOException ioe) {
            txtContent.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
            txtContent.setText("El objeto no pudo ser guardado");
            txtContent.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        }
    }

    public void onClickButtonLoad(View view) {
        try {
            ObjectInputStream ois = new ObjectInputStream(openFileInput(fileName));
            Person person = (Person) ois.readObject();
            ois.close();
            txtContent.setText(person.toString());
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            txtContent.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
            txtContent.setText("El objeto no pudo ser cargado");
            txtContent.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        }
    }
}
