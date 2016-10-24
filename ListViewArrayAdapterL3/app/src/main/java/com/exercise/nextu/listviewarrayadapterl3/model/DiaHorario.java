package com.exercise.nextu.listviewarrayadapterl3.model;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Admin on 10/24/2016.
 */

public class DiaHorario {

    private String asignatura;
    private String dia;

    public DiaHorario(String asignatura, String dia) {
        this.asignatura = asignatura;
        this.dia = dia;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public static ArrayList<DiaHorario> createArrayList(String[] asignaturas, String[] dias) {
        ArrayList<DiaHorario> listaDiaHorarioo = new ArrayList<>();
        for (int i = 0; i < asignaturas.length; i++) {
            listaDiaHorarioo.add(new DiaHorario(asignaturas[i], dias[i]));
        }
        return listaDiaHorarioo;
    }
}
