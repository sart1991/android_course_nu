package com.example.sergioalejandro.evaluacionfinal3;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergioalejandro.evaluacionfinal3.communication.ICommunication;

/**
 * Created by Admin on 11/8/2016.
 */

public class DetailsClasificationFragment extends Fragment {

    private ICommunication.IInstrumentsDetails instrumentsDetails;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            instrumentsDetails = (ICommunication.IInstrumentsDetails)context;
        } catch (ClassCastException castException) {
            throw new ClassCastException(context.getClass() + " should implement IINstrumnetsDetails");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            instrumentsDetails = (ICommunication.IInstrumentsDetails)activity;
        } catch (ClassCastException castException) {
            throw new ClassCastException(activity.getClass() + " should implement IINstrumnetsDetails");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_clasification, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instrumentsDetails.onDetailsFragmentViewCreated(view);
    }
}
