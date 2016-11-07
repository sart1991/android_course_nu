package com.example.sergioalejandro.fragmentlistnotesu3w4l2.service;

import com.example.sergioalejandro.fragmentlistnotesu3w4l2.model.Note;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SergioAlejandro on 6/11/2016.
 */

public class NoteService {

    private static final NoteService INSTANCE = new NoteService();
    private HashMap<Integer, Note> notes;

    private NoteService() {
        this.notes = new HashMap<>();
    }

    public static NoteService getInstance() {
        return INSTANCE;
    }

    public void putNote(Note note) {
        this.notes.put(note.getIdentifier(), note);
    }

    public void deleteNote(Note note) {
        this.notes.remove(note.getIdentifier());
    }

    public List<Note> getList() {
        return new ArrayList<>(notes.values());
    }

    public Note getNote(Integer identifier) {
        return this.notes.get(identifier);
    }
}
