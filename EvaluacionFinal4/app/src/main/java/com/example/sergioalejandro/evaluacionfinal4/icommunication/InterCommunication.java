package com.example.sergioalejandro.evaluacionfinal4.icommunication;

import android.view.View;

import com.example.sergioalejandro.evaluacionfinal4.views.custom.Figure;

/**
 * Created by sart1 on 1/16/2017.
 */

public interface InterCommunication {
    interface MainCommunication {
        void onCardViewFigureClick(Figure figure);
    }
}
