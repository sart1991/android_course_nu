package com.example.sergioalejandro.evaluacionfinal4.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sergioalejandro.evaluacionfinal4.R;
import com.example.sergioalejandro.evaluacionfinal4.model.DrawCard;
import com.example.sergioalejandro.evaluacionfinal4.services.ManageDrawCards;
import com.example.sergioalejandro.evaluacionfinal4.views.custom.Figure;

/**
 * Created by SergioAlejandro on 5/12/2016.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private static ManageDrawCards manageDrawCards;

    public MyRecyclerAdapter(ManageDrawCards manageDrawCards) {
        this.manageDrawCards = manageDrawCards;
    };

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.draw_cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DrawCard drawCard = manageDrawCards.getDrawCard(position);
        holder.setForm(drawCard.getForm());
        holder.setTitle(drawCard.getTitle());
        holder.setSubtitle(drawCard.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return manageDrawCards.getCount();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private Figure figure;
        private TextView txtCardTitle;
        private TextView txtCardSubtitle;

        public MyViewHolder(View view) {
            super(view);
            figure = (Figure) view.findViewById(R.id.cardview_figure);
            txtCardTitle = (TextView) view.findViewById(R.id.cardview_title);
            txtCardSubtitle = (TextView) view.findViewById(R.id.cardview_subtitle);
        }

        public void setForm(Figure.Form form) {
            figure.setForm(form, true);
        }

        public void setTitle(String title) {
            txtCardTitle.setText(title);
        }

        public void setSubtitle(String subtitle) {
            txtCardSubtitle.setText(subtitle);
        }
    }
}
