package com.example.sergioalejandro.fragmentactivitycommunication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by SergioAlejandro on 3/11/2016.
 */

public class MensajeFragment extends Fragment {

    private String parameterOne;
    private int parameterTwo;
    private float parameterThree;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        if (arguments != null) {
            parameterOne = arguments.getString("cadena");
            parameterTwo = arguments.getInt("entero");
            parameterThree = arguments.getFloat("flotante");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mensaje, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView)view.findViewById(R.id.txt_parameter_one)).setText(parameterOne);
        ((TextView)view.findViewById(R.id.txt_parameter_two)).setText(String.valueOf(parameterTwo));
        ((TextView)view.findViewById(R.id.txt_parameter_three)).setText(String.valueOf(parameterThree));
    }
}
