package com.example.sergioalejandro.fragmentlistnotesu3w4l2;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sergioalejandro.fragmentlistnotesu3w4l2.interfacecommunication.ICommunication;
import com.example.sergioalejandro.fragmentlistnotesu3w4l2.model.Note;
import com.example.sergioalejandro.fragmentlistnotesu3w4l2.service.NoteService;

/**
 * Created by SergioAlejandro on 6/11/2016.
 */

public class NotesDetailsFragment extends Fragment implements View.OnClickListener{

    private TextView textHeaderDetails;

    private EditText editTitle;
    private EditText editContent;

    private TextView textDateDetails;
    private Button btnSave;
    private Button btnDelete;

    private int identifier = -1;
    private static final String KEY_IDENTIFIER = "IDENTIFIER";

    private Note note;

    public static NotesDetailsFragment getInstance(int identifier) {
        NotesDetailsFragment notesDetailsFragment = new NotesDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(KEY_IDENTIFIER, identifier);
        notesDetailsFragment.setArguments(bundle);
        return notesDetailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            this.identifier = savedInstanceState.getInt(KEY_IDENTIFIER);
        }

        return inflater.inflate(R.layout.fragment_notes_details, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_IDENTIFIER, identifier);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textHeaderDetails = (TextView) view.findViewById(R.id.text_header_details);

        editTitle = (EditText) view.findViewById(R.id.edit_details_title);
        editContent = (EditText) view.findViewById(R.id.edit_details_content);

        btnSave = (Button) view.findViewById(R.id.btn_save);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);

        textDateDetails = (TextView) view.findViewById(R.id.text_date_details);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle arguments = getArguments();
        if(arguments != null) {
            updateView(arguments.getInt(KEY_IDENTIFIER));
        } else if (identifier > -1) {
            updateView(identifier);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
                if (note != null) {
                    NoteService.getInstance().deleteNote(note);
                    getActivity().onBackPressed();
                }
                break;
            case R.id.btn_save:
                NoteService noteService = NoteService.getInstance();
                String title = editTitle.getText().toString();
                String content = editContent.getText().toString();
                if (note == null) {
                    note = new Note(title, content);
                } else {
                    note.setTitle(title);
                    note.setContent(content);
                }
                noteService.putNote(note);

                getActivity().onBackPressed();
                break;
        }
    }

    public void updateView(int identifier) {
        if(identifier > -1) {
            note = NoteService.getInstance().getNote(identifier);
            editTitle.setText(note.getTitle());
            editContent.setText(note.getContent());

            textHeaderDetails.setText("Identifier: " + note.getIdentifier());
            textDateDetails.setText(note.getModificationDate());
        }
    }
}
