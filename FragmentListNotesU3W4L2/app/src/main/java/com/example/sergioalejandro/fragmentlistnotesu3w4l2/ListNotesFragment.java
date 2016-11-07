package com.example.sergioalejandro.fragmentlistnotesu3w4l2;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.sergioalejandro.fragmentlistnotesu3w4l2.adapter.ListNoteAdapter;
import com.example.sergioalejandro.fragmentlistnotesu3w4l2.interfacecommunication.ICommunication;
import com.example.sergioalejandro.fragmentlistnotesu3w4l2.model.Note;
import com.example.sergioalejandro.fragmentlistnotesu3w4l2.service.NoteService;

/**
 * Created by SergioAlejandro on 6/11/2016.
 */

public class ListNotesFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;

    private Button buttonAdd;

    private Context context;
    private ICommunication.INoteList iNoteList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        try {
            iNoteList = (ICommunication.INoteList) context;
        } catch (ClassCastException castException) {
            throw new ClassCastException(context.toString() + " should implement INoteSelected");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_notes, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.list_view_notes);
        buttonAdd = (Button) view.findViewById(R.id.btn_add_note);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iNoteList.onClickAdd();
            }
        });
        updateAdapter();
        listView.setOnItemClickListener(this);
    }

    private void updateAdapter() {
        ListNoteAdapter listNoteAdapter =
                new ListNoteAdapter(this.context, NoteService.getInstance().getList());
        this.listView.setAdapter(listNoteAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        iNoteList.noteSelected((int)l);
    }
}
