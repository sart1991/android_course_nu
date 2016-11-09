package com.example.sergioalejandro.evaluacionfinal3.model;

/**
 * Created by Admin on 11/8/2016.
 */

public class Instrument {

    private String name;
    private InstrumentsClasification clasification;
    private String sound;
    private boolean favorite;
    private Integer imageSrc;

    public Instrument(String name, InstrumentsClasification clasification, String sound) {
        this.name = name;
        this.clasification = clasification;
        this.sound = sound;
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

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
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
