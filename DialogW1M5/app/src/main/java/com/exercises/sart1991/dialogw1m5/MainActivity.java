package com.exercises.sart1991.dialogw1m5;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.colorpicker.ColorPickerDialog;
import com.android.colorpicker.ColorPickerSwatch;

public class MainActivity extends AppCompatActivity implements CommunicationInterface.DialogCustomCommunication{

    private Button button;
    private View viewColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(Dialogo.themeID);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.custom_btn_main);
        viewColor = findViewById(R.id.color_picker_view);
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

    public void onClickDialogColorPicker(final View view) {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.picker_colors);
        int[] colors = new int[typedArray.length()];

        for (int i = 0; i < colors.length; i++) {
            int idColor = typedArray.getResourceId(i, -1);
            colors[i] = ContextCompat.getColor(this, idColor);
        }

        ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
        colorPickerDialog.initialize(R.string.dialog_color_picker_title, colors, 0, 3, ColorPickerDialog.SIZE_SMALL);
        colorPickerDialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                viewColor.setBackgroundColor(color);
            }
        });
        colorPickerDialog.show(getFragmentManager(), "TAG");
        typedArray.recycle();
    }
}
