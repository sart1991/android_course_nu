package com.example.sergioalejandro.evaluacionfinal4.services;

import android.content.Context;

import com.example.sergioalejandro.evaluacionfinal4.R;
import com.example.sergioalejandro.evaluacionfinal4.model.DrawCard;
import com.example.sergioalejandro.evaluacionfinal4.views.custom.Figure;

/**
 * Created by Admin on 12/6/2016.
 */

public class ManageDrawCards {

    private DrawCard[] drawCards = new DrawCard[5];

    public ManageDrawCards(Context context) {
        drawCards[0] = new DrawCard(Figure.Form.CIRCLE,
                                    context.getResources().getString(R.string.circle_title),
                                    context.getResources().getString(R.string.circle_subtitle));
        drawCards[1] = new DrawCard(Figure.Form.SQUARE,
                                    context.getResources().getString(R.string.square_title),
                                    context.getResources().getString(R.string.square_subtitle));
        drawCards[2] = new DrawCard(Figure.Form.OVAL,
                                    context.getResources().getString(R.string.oval_title),
                                    context.getResources().getString(R.string.oval_subtitle));
        drawCards[3] = new DrawCard(Figure.Form.RECTANGLE,
                                    context.getResources().getString(R.string.rectangle_title),
                                    context.getResources().getString(R.string.rectangle_subtitle));
        drawCards[4] = new DrawCard(Figure.Form.FREE,
                                    context.getString(R.string.free_title),
                                    context.getString(R.string.free_subtitle));
    }

    public DrawCard getDrawCard(int position) {
        return drawCards[position];
    }

    public int getCount() {
        return drawCards.length;
    }
}
