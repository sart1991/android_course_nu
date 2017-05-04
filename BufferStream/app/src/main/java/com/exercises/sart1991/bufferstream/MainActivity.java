package com.exercises.sart1991.bufferstream;

import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private final String fileName = "data.bin";
    private EditText editInput;
    private TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editInput = ((TextInputLayout) findViewById(R.id.til_input)).getEditText();
        txtContent = (TextView) findViewById(R.id.txt_content);
        loadFile();
    }

    public void onClickButtonSave(View view) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(openFileOutput(fileName, MODE_APPEND))
            );
            bw.write(" " + editInput.getText());
            bw.newLine();
            bw.close();
        } catch (IOException ioe) {
            showAlertDialog("No se pudo guardar el archivo");
        }
        loadFile();
    }

    public void onClickButtonDelete(View view) {
        deleteFile(fileName);
        txtContent.setText("");
    }

    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void loadFile() {

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(openFileInput(fileName))
            );
            String content = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content += line;
            }
            txtContent.setText(content);
        } catch (IOException ioe) {
            showAlertDialog("No existe un archivo");
        } finally {
            try {
                bufferedReader.close();
            } catch (NullPointerException | IOException npe) {
                npe.printStackTrace();
            }
        }
    }
}
