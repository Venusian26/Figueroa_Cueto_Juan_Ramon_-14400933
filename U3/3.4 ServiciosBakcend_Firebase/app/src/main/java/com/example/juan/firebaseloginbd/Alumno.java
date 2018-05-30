package com.example.juan.firebaseloginbd;

public class Alumno {
    private String nombre;
    private String control;


    public Alumno() {
    }

    public Alumno(String nombre, String control) {
        this.nombre = nombre;
        this.control = control;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }
}
