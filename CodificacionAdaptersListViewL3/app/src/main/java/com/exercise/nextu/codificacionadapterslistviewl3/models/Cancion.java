package com.exercise.nextu.codificacionadapterslistviewl3.models;

import java.util.ArrayList;

/**
 * Created by SergioAlejandro on 24/10/2016.
 */

public class Cancion {

    String nombre;
    String artista;

    public Cancion(String nombre, String artista) {
        this.nombre = nombre;
        this.artista = artista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public static ArrayList<Cancion> createArrayList(String[] nombresCanciones, String[] artistasCanciones) {
        ArrayList<Cancion> arrayList = new ArrayList<>();

        for (int i = 0; i < nombresCanciones.length; i++) {
            arrayList.add(new Cancion(nombresCanciones[i], artistasCanciones[i]));
        }

        return arrayList;
    }
}
