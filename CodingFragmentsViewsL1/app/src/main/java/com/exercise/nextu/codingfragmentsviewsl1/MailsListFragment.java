package com.exercise.nextu.codingfragmentsviewsl1;

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

public class MailsListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mails_list, container, false);
        String[] listOfMails = getResources().getStringArray(R.array.list_of_mails);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listOfMails);
        ListView listViewMails = (ListView) view.findViewById(R.id.list_view_mails);
        listViewMails.setAdapter(arrayAdapter);
        return view;
    }
}
