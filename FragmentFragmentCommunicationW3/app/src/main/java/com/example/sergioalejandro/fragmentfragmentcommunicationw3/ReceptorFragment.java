package com.example.sergioalejandro.fragmentfragmentcommunicationw3;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by SergioAlejandro on 4/11/2016.
 */

public class ReceptorFragment extends Fragment {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_receptor, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.text_receptor);
    }

    public void setFragmentReceptorText(String string) {
        textView.setText(string);
    }
}
