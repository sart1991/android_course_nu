package com.example.sergioalejandro.fragmentlistnotesu3w4l2.model;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by SergioAlejandro on 6/11/2016.
 */

public class Note {

    private static Integer idCount = -1;
    private Integer identifier;
    private String title;
    private String content;
    private Calendar modificationDate;

    public Note(String title, String content) {
        this.identifier = idCount + 1;
        this.title = title;
        this.content = content;
        this.modificationDate = Calendar.getInstance();
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.modificationDate = Calendar.getInstance();
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.modificationDate = Calendar.getInstance();
        this.content = content;
    }

    public String getModificationDate() {
        DateFormat dateFormat = DateFormat.getDateInstance().getDateInstance();
        return dateFormat.format(modificationDate.getTime());
    }
}
