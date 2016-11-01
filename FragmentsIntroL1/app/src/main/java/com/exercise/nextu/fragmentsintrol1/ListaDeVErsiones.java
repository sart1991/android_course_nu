package com.exercise.nextu.fragmentsintrol1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Admin on 10/28/2016.
 */

public class ListaDeVErsiones extends Fragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_de_versiones, container, false);

//        ArrayList<String> arrayList = (ArrayList<String>) (Arrays.asList(getResources().getStringArray(R.array.array_android_versions)));
        String[] strings = getResources().getStringArray(R.array.array_android_versions);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strings);

        listView = (ListView)view.findViewById(R.id.list_versions_fragment);

        listView.setAdapter(arrayAdapter);

        return view;
    }
}
