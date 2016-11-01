package com.exercise.nextu.adicionandofragmentosdinamicos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Admin on 11/1/2016.
 */

public class Email extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.email, container, false);
        String[] emails = getResources().getStringArray(R.array.contactos_email);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, emails);

        ListView listView = (ListView) view.findViewById(R.id.list_view_emails);

        listView.setAdapter(arrayAdapter);

        return view;
    }
}
