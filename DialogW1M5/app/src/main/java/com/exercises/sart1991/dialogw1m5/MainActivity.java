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

    public void onClickDialogoAlerta(View view) {
        Dialogo.dialogAlert(this, view).show();
    }

    public void onClickDialogoConfirmacion(View view) {
        Dialogo.dialogConfirmacion(this, view).show();
    }

    public void onClickDialogoFragmento(View view) {
        DialogoFragmento dialogoFragmento = new DialogoFragmento();
        dialogoFragmento.show(getSupportFragmentManager(), "¿Desea Guardar?");
    }

    public void onClickDialogoLista(View view) {
        Dialogo.dialogList(this, view).show();
    }

    public void onClickDialogChecklist(View view) {
        Dialogo.dialogCheckList(this, view).show();
    }
}
