package com.example.david.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 2dam on 21/10/2015.
 */
public class Ayudante extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "musica.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        String sql = "drop table if exists "
                + Contrato.TablaCanciones.TABLA;
        db.execSQL(sql);
        String sql1 = "drop table if exists "
                + Contrato.TablaInterpretes.TABLA;
        db.execSQL(sql1);
        String sql2 = "drop table if exists "
                + Contrato.TablaDisco.TABLA;
        db.execSQL(sql2);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = "create table " + Contrato.TablaCanciones.TABLA +
                " (" + Contrato.TablaCanciones._ID +
                " integer primary key autoincrement, " +
                Contrato.TablaCanciones.TITULO + " text, " +
                Contrato.TablaCanciones.IDDISCO + " integer)";
        db.execSQL(sql);

        sql = "create table " + Contrato.TablaInterpretes.TABLA +
                " (" + Contrato.TablaInterpretes._ID +
                " integer primary key autoincrement, " +
                Contrato.TablaInterpretes.NOMBRE + " text)";
        db.execSQL(sql);

        sql = "create table " + Contrato.TablaDisco.TABLA +
                " (" + Contrato.TablaDisco._ID +
                " integer primary key autoincrement, " +
                Contrato.TablaDisco.NOMBRE + " text, " +
                Contrato.TablaDisco.IDINTERPRETE + " integer)";
        db.execSQL(sql);
    }
}