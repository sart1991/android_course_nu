package com.exercise.nextu.dinamicfragmentsmultiactivityl3;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Admin on 11/3/2016.
 */

public class ProgramacionAyerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_programacion_ayer, container, false);
        String[] stringArray = getResources().getStringArray(R.array.programacion_ayer);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stringArray);
        ListView listView = (ListView) view.findViewById(R.id.list_view_programacion_ayer);
        listView.setAdapter(arrayAdapter);
        return view;
    }
}
