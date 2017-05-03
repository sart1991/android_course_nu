package com.exercises.sart1991.almacenamientointerno;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilInput;
    private EditText editInput;
    private Button btnSave, btnLoad;
    private TextView txtChain, txtDirectory;
    private String file = "data.bin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tilInput = (TextInputLayout) findViewById(R.id.til_input);
        editInput = tilInput.getEditText();
        btnLoad = (Button) findViewById(R.id.btn_load);
        btnSave = (Button) findViewById(R.id.btn_save);
        txtChain = (TextView) findViewById(R.id.txt_chain);
        txtDirectory = (TextView) findViewById(R.id.txt_directory);

        txtDirectory.setText(getFilesDir().getParent());
    }

    public void onClickSave(View view) {
        try {
            FileOutputStream fos = openFileOutput(file, MODE_PRIVATE);
            fos.write(editInput.getText().toString().getBytes());
            fos.close();
            showSnackbar("File " + file + " saved");
        } catch (IOException ioe) {
            showErrorAlert("File " + file + "not saved", "Error saving the file");
        }
    }

    public void onClickLoad(View view) {
        try {
            FileInputStream fis = openFileInput(file);
            int character = fis.read();
            String content = "";

            while (character != -1) {
                content += String.valueOf(((char) character));
                character = fis.read();
            }
            showSnackbar("You are seeing the content od the file " + file);
            txtChain.setText(content);
        } catch (IOException ioe) {

        }
    }

    private void showErrorAlert(@Nullable String errorTitle, @NonNull String errorMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(errorTitle);
        builder.setMessage(errorMessage);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    private void showSnackbar(String message) {
        Snackbar.make(txtDirectory, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.it_settings) {
            return true;
        }

        return false;
    }
}
