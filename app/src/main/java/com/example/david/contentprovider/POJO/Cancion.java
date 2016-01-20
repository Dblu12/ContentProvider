package com.example.david.contentprovider.POJO;

/**
 * Created by David on 18/01/2016.
 */
public class Cancion {
    private String titulo;
    private int idCancion, idDisco;

    public Cancion(String titulo, int idCancion, int idDisco) {
        this.titulo = titulo;
        this.idCancion = idCancion;
        this.idDisco = idDisco;
    }

    public Cancion() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", idCancion=" + idCancion +
                ", idDisco=" + idDisco +
                '}';
    }
}
