package com.exercise.nextu.codificacionadapterslistviewl3.model;

import com.exercise.nextu.codificacionadapterslistviewl3.R;

import java.util.ArrayList;

/**
 * Created by Admin on 10/24/2016.
 */

public class Alarma {

    private String hora;
    private String estado;

    public Alarma(String hora, String estado) {
        this.hora = hora;
        this.estado = estado;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Alarma> createArrayList(String[] horarios) {
        ArrayList<Alarma> listAlarmas = new ArrayList<>();

        for (int i = 0; i < horarios.length; i++) {
            listAlarmas.add(new Alarma(horarios[i], "Desactivado"));
        }

        return listAlarmas;
    }
}
