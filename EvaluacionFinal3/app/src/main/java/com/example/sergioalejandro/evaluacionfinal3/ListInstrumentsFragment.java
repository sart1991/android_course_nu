package com.example.sergioalejandro.evaluacionfinal3;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sergioalejandro.evaluacionfinal3.communication.ICommunication;

/**
 * Created by SergioAlejandro on 7/11/2016.
 */

public class ListInstrumentsFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ICommunication.IInstrumentsList instrumentsListCommunication;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            instrumentsListCommunication = (ICommunication.IInstrumentsList)context;
        } catch (ClassCastException castException) {
            throw new ClassCastException(context.getClass() + " should implement IIInstrumentsList");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            instrumentsListCommunication = (ICommunication.IInstrumentsList)activity;
        } catch (ClassCastException castException) {
            throw new ClassCastException(activity.getClass() + " should implement IIInstrumentsList");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_instruments, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] arrayInstruments = getResources().getStringArray(R.array.instruments);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayInstruments);
        ListView listInstruments = ((ListView)view.findViewById(R.id.list_instruments));
        listInstruments.setAdapter(arrayAdapter);
        listInstruments.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        instrumentsListCommunication.onInstrumentSelected(i);
    }
}
