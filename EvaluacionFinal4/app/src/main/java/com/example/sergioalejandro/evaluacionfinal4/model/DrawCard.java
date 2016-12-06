package com.example.sergioalejandro.evaluacionfinal4.model;

import com.example.sergioalejandro.evaluacionfinal4.views.custom.Figure;

/**
 * Created by Admin on 12/6/2016.
 */

public class DrawCard {

    private Figure.Form form;
    private String title;
    private String subtitle;

    public DrawCard(Figure.Form form, String title, String subtitle) {
        this.form = form;
        this.title = title;
        this.subtitle = subtitle;
    }

    public Figure.Form getForm() {
        return form;
    }

    public void setForm(Figure.Form form) {
        this.form = form;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
