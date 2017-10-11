package com.exercises.sart1991.evaluacionfinal11.utils.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by MonoDesarrollo on 10/11/2017.
 */

public class DisplayToast implements Runnable {

    private final Context mContext;
    String mText;

    public DisplayToast(Context mContext, String text){
        this.mContext = mContext;
        mText = text;
    }

    public void run(){
        Toast.makeText(mContext, mText, Toast.LENGTH_LONG).show();
    }
}
