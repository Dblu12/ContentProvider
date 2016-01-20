package com.example.david.contentprovider;

/**
 * Created by David on 18/01/2016.
 */
public class Musica {
    private int idDisco, idCancion, idInterprete;
    private String cancion, album, interprete;

    public Musica(int idDisco, int idCancion, int idInterprete, String cancion, String album, String interprete) {
        this.idDisco = idDisco;
        this.idCancion = idCancion;
        this.idInterprete = idInterprete;
        this.cancion = cancion;
        this.album = album;
        this.interprete = interprete;
    }

    public Musica() {
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public int getIdInterprete() {
        return idInterprete;
    }

    public void setIdInterprete(int idInterprete) {
        this.idInterprete = idInterprete;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getInterprete() {
        return interprete;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "idDisco=" + idDisco +
                ", idCancion=" + idCancion +
                ", idInterprete=" + idInterprete +
                ", cancion='" + cancion + '\'' +
                ", album='" + album + '\'' +
                ", interprete='" + interprete + '\'' +
                '}';
    }
}
