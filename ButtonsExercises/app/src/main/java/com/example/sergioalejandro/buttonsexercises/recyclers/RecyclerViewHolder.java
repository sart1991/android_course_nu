package com.example.sergioalejandro.buttonsexercises.recyclers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sergioalejandro.buttonsexercises.R;

/**
 * Created by SergioAlejandro on 23/11/2016.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView txtTitle, txtSubtitle;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        txtTitle = (TextView)itemView.findViewById(R.id.txt_title);
        txtSubtitle = (TextView)itemView.findViewById(R.id.txt_subtitle);
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public TextView getTxtSubtitle() {
        return txtSubtitle;
    }
}
