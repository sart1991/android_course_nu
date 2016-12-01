package com.example.sergioalejandro.buttonsexercises.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergioalejandro.buttonsexercises.R;

/**
 * Created by SergioAlejandro on 23/11/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private String[] arrayTitles = {
            "Title 1", "Title 2", "Title 3", "Title 4", "Title 5",
            "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
    };
    private String[] arraySubtitles = {
            "Subtitle 1", "Subtitle 2", "Subtitle 3", "Subtitle 4", "Subtitle 5",
            "Subtitle 6", "Subtitle 7", "Subtitle 8", "Subtitle 9", "Subtitle 10"
    };

    private Context context;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.getTxtTitle().setText(arrayTitles[position]);
        holder.getTxtSubtitle().setText(arraySubtitles[position]);
    }

    @Override
    public int getItemCount() {
        return arrayTitles.length;
    }
}
