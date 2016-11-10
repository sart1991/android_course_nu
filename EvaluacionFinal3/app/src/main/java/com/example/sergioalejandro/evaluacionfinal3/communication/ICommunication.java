package com.example.sergioalejandro.evaluacionfinal3.communication;

import android.view.View;

/**
 * Created by SergioAlejandro on 7/11/2016.
 */

public interface ICommunication {
    interface IInstrumentsList {
        void onInstrumentSelected(int position);
    }

    interface  IInstrumentsDetails {
        void onDetailsFragmentViewCreated(View v);
    }
}
