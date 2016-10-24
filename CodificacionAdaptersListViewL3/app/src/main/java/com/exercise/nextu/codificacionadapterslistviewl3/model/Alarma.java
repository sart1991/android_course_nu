package com.exercise.nextu.codificacionadapterslistviewl3.model;

import com.exercise.nextu.codificacionadapterslistviewl3.R;

import java.util.ArrayList;

/**
 * Created by Admin on 10/24/2016.
 */

public class Alarma {

    private String hora;
    private String estado = "Desactivado";

    public Alarma(String hora) {
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void switchEstado(boolean activo) {
        if (activo) {
            estado = "Activado";
        } else {
            estado = "Desactivado";
        }
    }

    public String getEstado() {
        return estado;
    }

    public ArrayList<Alarma> createArrayList(String[] horarios) {
        ArrayList<Alarma> listAlarmas = new ArrayList<>();

        for (int i = 0; i < horarios.length; i++) {
            listAlarmas.add(new Alarma(horarios[i]));
        }

        return listAlarmas;
    }
}
