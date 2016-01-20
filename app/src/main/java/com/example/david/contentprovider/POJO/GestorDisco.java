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
public class GestorDisco {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorDisco(Context c){
        abd= new Ayudante(c);
    }

    public void open(){bd= abd.getWritableDatabase();}
    public void openRead(){bd=abd.getReadableDatabase();}
    public void close(){
        abd.close();
    }

    public void insert(Disco c){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaDisco.NOMBRE, c.getTitulo());
        valores.put(Contrato.TablaDisco.IDINTERPRETE, c.getIdInterprete());
        bd.insert(Contrato.TablaDisco.TABLA, null, valores);
        //return id;
    }

    public int deleteId(long id){
        String condicion = Contrato.TablaDisco._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaDisco.TABLA, condicion, argumentos);
        return cuenta;
    }

    public Disco getRow(Cursor c) {

        Disco ag = new Disco();
        ag.setIdDisco((int) c.getLong(0));
        ag.setTitulo(c.getString(1));
        ag.setIdInterprete(c.getInt(2));
        return ag;
    }

    public Cursor getCursor(String condicion, String[] params) {
        Cursor cursor = bd.query(
                Contrato.TablaDisco.TABLA, null, condicion, params, null, null,
                Contrato.TablaDisco.NOMBRE);
        return cursor;
    }

    public List<Disco> select(String cond, String[] params){
        List<Disco> la= new ArrayList<>();
        Cursor cursor= getCursor(cond, params);
        Disco p;
        while(cursor.moveToNext()){
            p=getRow(cursor);
            la.add(p);
        }
        return la;
    }


    public Disco getRow(int id) {
        String[] proyeccion = { Contrato.TablaDisco._ID,
                Contrato.TablaDisco.NOMBRE,
                Contrato.TablaDisco.IDINTERPRETE};
        String where = Contrato.TablaDisco._ID + " = ?";
        String[] parametros = new String[] { id+"" };
        String groupby = null; String having = null;
        String orderby = Contrato.TablaDisco.NOMBRE + " ASC";
        Cursor c = bd.query(Contrato.TablaDisco.TABLA, proyeccion,
                where, parametros, groupby, having, orderby);
        c.moveToFirst();
        Disco ag = getRow(c);
        c.close();
        return ag;
    }

}


