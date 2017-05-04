package com.exercises.sart1991.almacenamientodatosprimitivos;

import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String file = "file.bin";
    private EditText editUTF, editBoolean, editChar, editDecimal, editInteger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUTF = ((TextInputLayout)findViewById(R.id.til_utf)).getEditText();
        editBoolean = ((TextInputLayout)findViewById(R.id.til_boolean)).getEditText();
        editChar = ((TextInputLayout)findViewById(R.id.til_character)).getEditText();
        editDecimal = ((TextInputLayout)findViewById(R.id.til_decimal)).getEditText();
        editInteger = ((TextInputLayout)findViewById(R.id.til_integer)).getEditText();
    }

    public void onClickButtonGenerate(View view) {
        try {
            DataOutputStream dos = new DataOutputStream(openFileOutput(file, MODE_PRIVATE));
            dos.writeUTF("Certificación Android");
            dos.writeBoolean(true);
            dos.writeChar('A');
            dos.writeDouble(3.7);
            dos.writeInt(5);
        } catch (IOException ioe) {
            showAlertDialog("Error al crear archivo");
        }
    }

    public void onClickButtonLoad(View view) {
        try {
            DataInputStream dis = new DataInputStream(openFileInput(file));
            editUTF.setText(dis.readUTF());
            editBoolean.setText(String.valueOf(dis.readBoolean()));
            editChar.setText(String.valueOf(dis.readChar()));
            editDecimal.setText(String.valueOf(dis.readDouble()));
            editInteger.setText(String.valueOf(dis.readInt()));
        } catch (IOException ioe) {
            showAlertDialog("Error al abrir el archivo");
        }
    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }
}
