package com.exercises.sart1991.dialogw1m5;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by sart1 on 1/25/2017.
 */

public class DialogoFragmento extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.ThemeDialogAlert);
        builder.setTitle("Confirmacion");
        builder.setMessage("Deasea guardar?");
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getContext(), "Informacion guardada", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getContext(), "Informacion descartada", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.show();
    }
}
