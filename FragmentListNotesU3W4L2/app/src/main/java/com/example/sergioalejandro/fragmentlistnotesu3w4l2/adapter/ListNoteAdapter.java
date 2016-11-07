package com.example.sergioalejandro.fragmentlistnotesu3w4l2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sergioalejandro.fragmentlistnotesu3w4l2.R;
import com.example.sergioalejandro.fragmentlistnotesu3w4l2.model.Note;

import java.util.ArrayDeque;
import java.util.List;

/**
 * Created by SergioAlejandro on 6/11/2016.
 */

public class ListNoteAdapter extends BaseAdapter {

    private List<Note> notes;
    private LayoutInflater layoutInflater;

    public ListNoteAdapter(Context context, List<Note> notes) {
        this.notes = notes;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return notes.get(i).getIdentifier();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.note_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textTitle.setText(notes.get(i).getTitle());
        viewHolder.textDate.setText(notes.get(i).getModificationDate());
        return view;
    }

    private static class ViewHolder {

        TextView textTitle;
        TextView textDate;

        ViewHolder(View view) {
            this.textTitle = (TextView) view.findViewById(R.id.title_item_note);
            this.textDate = (TextView) view.findViewById(R.id.subtitle_item_note);
        }
    }


}
