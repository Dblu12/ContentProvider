package com.example.david.contentprovider.POJO;

/**
 * Created by David on 18/01/2016.
 */
public class Interprete {
    private String nombre;
    private int idInterprete;

    public Interprete(String nombre, int idInterprete) {
        this.nombre = nombre;
        this.idInterprete = idInterprete;
    }

    public Interprete() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdInterprete() {
        return idInterprete;
    }

    public void setIdInterprete(int idInterprete) {
        this.idInterprete = idInterprete;
    }

    @Override
    public String toString() {
        return "Interprete{" +
                "nombre='" + nombre + '\'' +
                ", idInterprete=" + idInterprete +
                '}';
    }
}
