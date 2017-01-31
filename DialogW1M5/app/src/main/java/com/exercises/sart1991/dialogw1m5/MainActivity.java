package com.exercises.sart1991.dialogw1m5;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements CommunicationInterface.DialogCustomCommunication{

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(Dialogo.themeID);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.custom_btn_main);
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

    public void onClickDialogCustom(View view) {
        CustomDialogFragment customDialogFragment = new CustomDialogFragment();
        customDialogFragment.show(getFragmentManager(), "Escriba su nombre");
    }

    @Override
    public void onClickCustomPositiveButton(String nombre) {
        Snackbar.make(button, "Bienvenido " +  nombre, Snackbar.LENGTH_SHORT).show();
        Log.i("CustomDialog", nombre);
    }
}
