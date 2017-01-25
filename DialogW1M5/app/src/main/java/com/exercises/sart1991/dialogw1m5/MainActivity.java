package com.exercises.sart1991.dialogw1m5;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickGenerateAlert(View view) {
        dialogAlert(view).show();
    }

    public Dialog dialogAlert(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.ThemeDialogAlert);

        builder.setTitle("Informacion:");
        builder.setMessage("Datos guardados correctamente");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Snackbar.make(view, "OK", Snackbar.LENGTH_LONG).show();
            }
        });
        return builder.create();
    }
}
