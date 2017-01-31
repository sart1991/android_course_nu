package com.exercises.sart1991.dialogw1m5;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Admin on 1/31/2017.
 */

public class CustomDialogFragment extends DialogFragment {

    CommunicationInterface.DialogCustomCommunication customCommunication;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            customCommunication = (CommunicationInterface.DialogCustomCommunication) activity;
            Log.i("CustomDialog", "Passed");
        } catch (ClassCastException cce) {
            Log.i("CustomDialog", cce.getMessage());
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog, null);
        builder.setView(view);
        final EditText editTextName = (EditText) view.findViewById(R.id.edit_text_nombre);
        Button buttonPositive = (Button) view.findViewById(R.id.dialog_fragment_button_postive);
        buttonPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customCommunication.onClickCustomPositiveButton(editTextName.getText().toString());
                Log.i("CustomDialog", editTextName.getText().toString());
                dismiss();
            }
        });
        return builder.create();
    }
}
