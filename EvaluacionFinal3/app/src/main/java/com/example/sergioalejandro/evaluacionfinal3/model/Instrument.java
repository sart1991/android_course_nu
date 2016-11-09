package com.example.sergioalejandro.evaluacionfinal3.model;

import com.example.sergioalejandro.evaluacionfinal3.R;

/**
 * Created by Admin on 11/8/2016.
 */

public class Instrument {

    private String name;
    private InstrumentsClasification clasification;
    private boolean favorite;
    private Integer imageSrc;

    public Instrument(String name, InstrumentsClasification clasification, Integer imageSource) {
        this.name = name;
        this.clasification = clasification;
        this.imageSrc = imageSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstrumentsClasification getClasification() {
        return clasification;
    }

    public void setClasification(InstrumentsClasification clasification) {
        this.clasification = clasification;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Integer getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(Integer imageSrc) {
        this.imageSrc = imageSrc;
    }

    public enum InstrumentsClasification {
        STRING, PERCUSSION, WIND, ELECTRIC;
    }
}
