package com.example.david.contentprovider.POJO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.david.contentprovider.Ayudante;
import com.example.david.contentprovider.Contrato;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 18/01/2016.
 */
public class GestorInterprete {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorInterprete(Context c){
        abd= new Ayudante(c);
    }

    public void open(){bd= abd.getWritableDatabase();}
    public void openRead(){bd=abd.getReadableDatabase();}
    public void close(){
        abd.close();
    }

    public long insert(Interprete c){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaInterpretes.NOMBRE, c.getNombre());
        long id= bd.insert(Contrato.TablaInterpretes.TABLA, null, valores);
        return id;
    }

    public int deleteId(long id){
        String condicion = Contrato.TablaInterpretes._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaInterpretes.TABLA, condicion, argumentos);
        return cuenta;
    }

    public Interprete getRow(Cursor c) {

        Interprete ag = new Interprete();
        ag.setIdInterprete((int) c.getLong(0));
        ag.setNombre(c.getString(1));

        return ag;
    }

    public Cursor getCursor(String condicion, String[] params) {
        Cursor cursor = bd.query(
                Contrato.TablaInterpretes.TABLA, null, condicion, params, null, null,
                Contrato.TablaInterpretes.NOMBRE);
        return cursor;
    }

    public List<Interprete> select(String cond, String[] params){
        List<Interprete> la= new ArrayList<>();
        Cursor cursor= getCursor(cond, params);
        Interprete p;
        while(cursor.moveToNext()){
            p=getRow(cursor);
            la.add(p);
        }
        return la;
    }

    public Interprete getRow(int id) {
        String[] proyeccion = { Contrato.TablaInterpretes._ID,
                Contrato.TablaInterpretes.NOMBRE};
        String where = Contrato.TablaInterpretes._ID + " = ?";
        String[] parametros = new String[] { id+"" };
        String groupby = null; String having = null;
        String orderby = Contrato.TablaInterpretes.NOMBRE + " ASC";
        Cursor c = bd.query(Contrato.TablaInterpretes.TABLA, proyeccion,
                where, parametros, groupby, having, orderby);
        c.moveToFirst();
        Interprete ag = getRow(c);
        c.close();
        return ag;
    }

}


