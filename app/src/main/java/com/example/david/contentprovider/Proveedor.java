package com.example.david.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 2dam on 10/11/2015.
 */
public class Proveedor extends ContentProvider {

    public static final UriMatcher convertUri2Int;
    public static final int CANCIONES = 1;
    public static final int CANCIONES_ID = 2;
    public static final int INTERPRETES = 3;
    public static final int INTERPRETES_ID = 4;
    public static final int DISCOS = 5;
    public static final int DISCOS_ID = 6;

    static {
        convertUri2Int = new UriMatcher(UriMatcher.NO_MATCH);
        convertUri2Int.addURI(Contrato.TablaCanciones.AUTHORITY,Contrato.TablaCanciones.TABLA,CANCIONES);
        convertUri2Int.addURI(Contrato.TablaCanciones.AUTHORITY,Contrato.TablaCanciones.TABLA+"/#",CANCIONES_ID);

        convertUri2Int.addURI(Contrato.TablaInterpretes.AUTHORITY,Contrato.TablaInterpretes.TABLA,INTERPRETES);
        convertUri2Int.addURI(Contrato.TablaInterpretes.AUTHORITY,Contrato.TablaInterpretes.TABLA+"/#",INTERPRETES_ID);

        convertUri2Int.addURI(Contrato.TablaDisco.AUTHORITY,Contrato.TablaDisco.TABLA,DISCOS);
        convertUri2Int.addURI(Contrato.TablaDisco.AUTHORITY,Contrato.TablaDisco.TABLA+"/#",DISCOS_ID);
    }

    private static Ayudante adb;

    @Override
    public boolean onCreate() {
        adb = new Ayudante(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (convertUri2Int.match(uri)){
            case CANCIONES:
                return Contrato.TablaCanciones.MULTIPLE_MIME;
            case CANCIONES_ID:
                return Contrato.TablaCanciones.SINGLE_MIME;
            case INTERPRETES:
                return Contrato.TablaInterpretes.MULTIPLE_MIME;
            case INTERPRETES_ID:
                return Contrato.TablaInterpretes.SINGLE_MIME;
            case DISCOS:
                return Contrato.TablaDisco.MULTIPLE_MIME;
            case DISCOS_ID:
                return Contrato.TablaDisco.SINGLE_MIME;
            default:
                throw new IllegalArgumentException("Tipo de actividad desconocida: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Validar la uri
        if (convertUri2Int.match(uri) != CANCIONES && convertUri2Int.match(uri) != INTERPRETES && convertUri2Int.match(uri) != DISCOS) {
            throw new IllegalArgumentException("URI desconocida : " + uri);
        }

        if (values == null) {
            throw new IllegalArgumentException("Cliente inadecuado.");
        }
        // Inserción de nueva fila
        SQLiteDatabase db = adb.getWritableDatabase();
        if(convertUri2Int.match(uri)==CANCIONES) {
            long rowId = db.insert(Contrato.TablaCanciones.TABLA, null, values);
            if (rowId > 0) {
                Uri uri_actividad = ContentUris.withAppendedId(Contrato.TablaCanciones.CONTENT_URI, rowId);
                //Notificamos que hemos insertado una nueva uri al contentresolver.
                getContext().getContentResolver().notifyChange(uri_actividad, null);
                return uri_actividad;
            }
            throw new SQLException("Error al insertar fila en : " + uri);
        }
        else if(convertUri2Int.match(uri)==INTERPRETES) {
            long rowId = db.insert(Contrato.TablaInterpretes.TABLA, null, values);
            if (rowId > 0) {
                Uri uri_actividad = ContentUris.withAppendedId(Contrato.TablaInterpretes.CONTENT_URI, rowId);
                //Notificamos que hemos insertado una nueva uri al contentresolver.
                getContext().getContentResolver().notifyChange(uri_actividad, null);
                return uri_actividad;
            }
            throw new SQLException("Error al insertar fila en : " + uri);
        }
        else if(convertUri2Int.match(uri)==DISCOS) {
            long rowId = db.insert(Contrato.TablaDisco.TABLA, null, values);
            if (rowId > 0) {
                Uri uri_actividad = ContentUris.withAppendedId(Contrato.TablaDisco.CONTENT_URI, rowId);
                //Notificamos que hemos insertado una nueva uri al contentresolver.
                getContext().getContentResolver().notifyChange(uri_actividad, null);
                return uri_actividad;
            }
            throw new SQLException("Error al insertar fila en : " + uri);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = adb.getWritableDatabase();

        int match = convertUri2Int.match(uri);
        int affected;
        long idActividad;

        switch (match) {
            case CANCIONES:
                affected = db.delete(Contrato.TablaCanciones.TABLA, selection, selectionArgs);
                break;
            case CANCIONES_ID:
                idActividad = ContentUris.parseId(uri);
                affected = db.delete(Contrato.TablaCanciones.TABLA, Contrato.TablaCanciones._ID + "= ?", new String[]{Long.toString(idActividad)});
                // Notificar cambio asociado a la uri
                getContext().getContentResolver().
                        notifyChange(uri, null);
                break;
            case INTERPRETES:
                affected = db.delete(Contrato.TablaInterpretes.TABLA, selection, selectionArgs);
                break;
            case INTERPRETES_ID:
                idActividad = ContentUris.parseId(uri);
                affected = db.delete(Contrato.TablaInterpretes.TABLA, Contrato.TablaInterpretes._ID + "= ?", new String[]{Long.toString(idActividad)});
                // Notificar cambio asociado a la uri
                getContext().getContentResolver().
                        notifyChange(uri, null);
                break;
            case DISCOS:
                affected = db.delete(Contrato.TablaDisco.TABLA, selection, selectionArgs);
                break;
            case DISCOS_ID:
                idActividad = ContentUris.parseId(uri);
                affected = db.delete(Contrato.TablaDisco.TABLA, Contrato.TablaDisco._ID + "= ?", new String[]{Long.toString(idActividad)});
                // Notificar cambio asociado a la uri
                getContext().getContentResolver().
                        notifyChange(uri, null);
                break;
            default:
                throw new IllegalArgumentException("Elemento actividad desconocido: " +uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = adb.getWritableDatabase();
        int affected;
        String idActividad;
        switch (convertUri2Int.match(uri)) {
            case CANCIONES:
                affected = db.update(Contrato.TablaCanciones.TABLA, values, selection, selectionArgs);
                break;
            case CANCIONES_ID:
                idActividad = uri.getPathSegments().get(1);
                affected = db.update(Contrato.TablaCanciones.TABLA, values,Contrato.TablaCanciones._ID + "= ?", new String[]{idActividad});
                break;
            case INTERPRETES:
                affected = db.update(Contrato.TablaInterpretes.TABLA, values, selection, selectionArgs);
                break;
            case INTERPRETES_ID:
                idActividad = uri.getPathSegments().get(1);
                affected = db.update(Contrato.TablaInterpretes.TABLA, values,Contrato.TablaInterpretes._ID + "= ?", new String[]{idActividad});
                break;
            case DISCOS:
                affected = db.update(Contrato.TablaCanciones.TABLA, values, selection, selectionArgs);
                break;
            case DISCOS_ID:
                idActividad = uri.getPathSegments().get(1);
                affected = db.update(Contrato.TablaDisco.TABLA, values,Contrato.TablaDisco._ID + "= ?", new String[]{idActividad});
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Obtener base de datos
        Log.v("prueba",adb.toString());
        SQLiteDatabase db = adb.getWritableDatabase();
        // Comparar Uri
        int match = convertUri2Int.match(uri);
        long idActividad;
        Cursor c;

        switch (match) {
            case CANCIONES:
                // Consultando todos los registros
                c = db.query(Contrato.TablaCanciones.TABLA, projection,selection, selectionArgs,null, null, sortOrder);
                Log.v("prueba","22222"+c.toString());
                break;
            case CANCIONES_ID:
                // Consultando un solo registro basado en el Id del Uri
                idActividad = ContentUris.parseId(uri);
                c = db.query(Contrato.TablaCanciones.TABLA, projection, Contrato.TablaCanciones._ID + " = " + idActividad, selectionArgs, null, null, sortOrder);
                break;
            case INTERPRETES:
                // Consultando todos los registros
                c = db.query(Contrato.TablaInterpretes.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case INTERPRETES_ID:
                // Consultando un solo registro basado en el Id del Uri
                idActividad = ContentUris.parseId(uri);
                c = db.query(Contrato.TablaInterpretes.TABLA, projection, Contrato.TablaInterpretes._ID + " = " + idActividad, selectionArgs, null, null, sortOrder);
                break;
            case DISCOS:
                // Consultando todos los registros
                c = db.query(Contrato.TablaDisco.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case DISCOS_ID:
                // Consultando un solo registro basado en el Id del Uri
                idActividad = ContentUris.parseId(uri);
                c = db.query(Contrato.TablaDisco.TABLA, projection, Contrato.TablaDisco._ID + " = " + idActividad, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        /*
        if(match==CANCIONES){
            c.setNotificationUri(getContext().getContentResolver(),Contrato.TablaCanciones.CONTENT_URI);}
        else if(match==INTERPRETES)
            c.setNotificationUri(getContext().getContentResolver(),Contrato.TablaInterpretes.CONTENT_URI);
        else if(match==DISCOS)
            c.setNotificationUri(getContext().getContentResolver(),Contrato.TablaDisco.CONTENT_URI);
            */
        return c;
    }

    public Cursor query2(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Obtener base de datos
        Log.v("prueba",adb.toString());
        SQLiteDatabase db = adb.getWritableDatabase();
        // Comparar Uri
        int match = convertUri2Int.match(uri);
        long idActividad;
        Cursor c;

        switch (match) {
            case CANCIONES:
                // Consultando todos los registros
                c = db.query(Contrato.TablaCanciones.TABLA, projection,selection, selectionArgs,null, null, sortOrder);
                Log.v("prueba","22222"+c.toString());
                break;
            case CANCIONES_ID:
                // Consultando un solo registro basado en el Id del Uri
                idActividad = ContentUris.parseId(uri);
                c = db.query(Contrato.TablaCanciones.TABLA, projection,Contrato.TablaCanciones._ID + " = " + idActividad,selectionArgs, null, null, sortOrder);
                break;
            case INTERPRETES:
                // Consultando todos los registros
                c = db.query(Contrato.TablaInterpretes.TABLA, projection,selection, selectionArgs,null, null, sortOrder);
                break;
            case INTERPRETES_ID:
                // Consultando un solo registro basado en el Id del Uri
                idActividad = ContentUris.parseId(uri);
                c = db.query(Contrato.TablaInterpretes.TABLA, projection,Contrato.TablaInterpretes._ID + " = " + idActividad,selectionArgs, null, null, sortOrder);
                break;
            case DISCOS:
                // Consultando todos los registros
                c = db.query(Contrato.TablaDisco.TABLA, projection,selection, selectionArgs,null, null, sortOrder);
                break;
            case DISCOS_ID:
                // Consultando un solo registro basado en el Id del Uri
                idActividad = ContentUris.parseId(uri);
                c = db.query(Contrato.TablaDisco.TABLA, projection,Contrato.TablaDisco._ID + " = " + idActividad,selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        /*
        if(match==CANCIONES){
            c.setNotificationUri(getContext().getContentResolver(),Contrato.TablaCanciones.CONTENT_URI);}
        else if(match==INTERPRETES)
            c.setNotificationUri(getContext().getContentResolver(),Contrato.TablaInterpretes.CONTENT_URI);
        else if(match==DISCOS)
            c.setNotificationUri(getContext().getContentResolver(),Contrato.TablaDisco.CONTENT_URI);
            */
        return c;
    }
}
