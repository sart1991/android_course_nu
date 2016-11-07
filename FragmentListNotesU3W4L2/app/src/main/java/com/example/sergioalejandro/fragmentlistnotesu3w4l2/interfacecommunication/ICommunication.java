package com.example.sergioalejandro.fragmentlistnotesu3w4l2.interfacecommunication;

import android.widget.EditText;

/**
 * Created by SergioAlejandro on 6/11/2016.
 */

public interface ICommunication {

    interface INoteList {
        void noteSelected(int position);
        void onClickAdd();
    }

    interface INoteDetails {
        void onClickSaveNote();
        void onClickDeleteNote();
        void onStartFragment(EditText title, EditText content);
    }

}
