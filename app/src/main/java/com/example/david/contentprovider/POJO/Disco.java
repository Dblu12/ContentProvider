package com.example.david.contentprovider.POJO;

/**
 * Created by David on 18/01/2016.
 */
public class Disco {

    private String titulo;
    private int idDisco, idInterprete;

    public Disco(String titulo, int idDisco, int idInterprete) {
        this.titulo = titulo;
        this.idDisco = idDisco;
        this.idInterprete = idInterprete;
    }

    public Disco() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public int getIdInterprete() {
        return idInterprete;
    }

    public void setIdInterprete(int idInterprete) {
        this.idInterprete = idInterprete;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "titulo='" + titulo + '\'' +
                ", idDisco=" + idDisco +
                ", idInterprete=" + idInterprete +
                '}';
    }
}
