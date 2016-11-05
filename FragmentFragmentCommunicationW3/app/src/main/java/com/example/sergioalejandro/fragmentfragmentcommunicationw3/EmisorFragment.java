package com.example.sergioalejandro.fragmentfragmentcommunicationw3;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by SergioAlejandro on 4/11/2016.
 */

public class EmisorFragment extends Fragment {

    private EditText editText;
    private Button buttonEnviar;
    private Communicator communicator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Communicator) {
            this.communicator = (Communicator) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_emisor, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = (EditText) view.findViewById(R.id.edit_emisor);
        buttonEnviar = (Button) view.findViewById(R.id.btn_enviar);
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                communicator.onClickBtnEnviar(editText.getText().toString());
            }
        });
    }
}
