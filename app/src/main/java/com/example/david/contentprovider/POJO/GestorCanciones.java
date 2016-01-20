package com.example.david.contentprovider.POJO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.david.contentprovider.Ayudante;
import com.example.david.contentprovider.Contrato;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 18/01/2016.
 */
public class GestorCanciones {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorCanciones(Context c){
        abd= new Ayudante(c);
    }

    public void open(){bd= abd.getWritableDatabase();}
    public void openRead(){bd=abd.getReadableDatabase();}
    public void close(){
        abd.close();
    }

    public long insert(Cancion c){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaCanciones.TITULO, c.getTitulo());
        Log.v("Prueba", c.getTitulo()+"title");
        valores.put(Contrato.TablaCanciones.IDDISCO, c.getIdDisco());
        long id= bd.insert(Contrato.TablaCanciones.TABLA, null, valores);
        return id;
    }

    public int deleteId(long id){
        String condicion = Contrato.TablaCanciones._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaCanciones.TABLA, condicion, argumentos);
        return cuenta;
    }

    public Cancion getRow(Cursor c) {

        Cancion ag = new Cancion();
        ag.setIdCancion((int) c.getLong(0));
        ag.setTitulo(c.getString(1));
        ag.setIdDisco(c.getInt(2));
        return ag;
    }

    public Cursor getCursor(String condicion, String[] params) {
        Cursor cursor = bd.query(
                Contrato.TablaCanciones.TABLA, null, condicion, params, null, null,
                Contrato.TablaCanciones.TITULO);
        return cursor;
    }

    public List<Cancion> select(String cond, String[] params){
        List<Cancion> la= new ArrayList<>();
        Cursor cursor= getCursor(cond, params);
        Cancion p;
        while(cursor.moveToNext()){
            p=getRow(cursor);
            la.add(p);
        }
        return la;
    }

    public Cancion getRow(int id) {
        String[] proyeccion = { Contrato.TablaCanciones._ID,
                Contrato.TablaCanciones.TITULO,
                Contrato.TablaCanciones.IDDISCO};
        String where = Contrato.TablaCanciones._ID + " = ?";
        String[] parametros = new String[] { id+"" };
        String groupby = null; String having = null;
        String orderby = Contrato.TablaCanciones.TITULO + " ASC";
        Cursor c = bd.query(Contrato.TablaCanciones.TABLA, proyeccion,
                where, parametros, groupby, having, orderby);
        c.moveToFirst();
        Cancion ag = getRow(c);
        c.close();
        return ag;
    }

}
