package com.exercises.sart1991.dialogw1m5;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by sart1 on 1/25/2017.
 */

public class Dialogo {

    private static Snackbar snackbar;

    public static Dialog dialogAlert(Activity activity, final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.ThemeDialogAlert);

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

    public static Dialog dialogConfirmacion(Activity activity, final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.ThemeDialogAlert);

        builder.setTitle("Confirmacion:");
        builder.setMessage("Estas a punto de salir\n¿Deseas guardar?");

        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Snackbar.make(view, "Informacion guardada", Snackbar.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("No guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Snackbar.make(view, "Informacion descartada", Snackbar.LENGTH_LONG).show();
            }
        });
        builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    public static Dialog dialogList(Activity activity, final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Seleccione un pais");
        builder.setSingleChoiceItems(new String[]{"Cero", "Uno", "Dos"}, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialogo.snackbar = Snackbar.make(view, "Seleccionó " + which, Snackbar.LENGTH_LONG);
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                if (snackbar == null) {
                    snackbar = Snackbar.make(view, "No selecciono ninguna opcion", Snackbar.LENGTH_LONG);
                }
                snackbar.show();
            }
        });

        return builder.create();
    }
}
