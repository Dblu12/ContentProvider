package com.example.david.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;


public class Contrato {
    private Contrato(){
    }
    public static abstract class TablaCanciones implements
            BaseColumns{
        public static final String TABLA = "canciones";
        public static final String TITULO = "titulo";
        public static final String IDDISCO = "iddisco";
        public static final String AUTHORITY = "com.example.david.contentprovider.Proveedor";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public static final String SINGLE_MIME = "vnd.android.cursor.item/vnd" + AUTHORITY + TABLA;
        public static final String MULTIPLE_MIME = "vnd.android.cursor.dir/vnd" + AUTHORITY + TABLA;

    }

    public static abstract class TablaInterpretes implements
            BaseColumns{
        public static final String TABLA = "interpretes";
        public static final String NOMBRE = "nombre";
        public static final String AUTHORITY = "com.example.david.contentprovider.Proveedor";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public static final String SINGLE_MIME = "vnd.android.cursor.item/vnd" + AUTHORITY + TABLA;
        public static final String MULTIPLE_MIME = "vnd.android.cursor.dir/vnd" + AUTHORITY + TABLA;

    }

    public static abstract class TablaDisco implements
            BaseColumns{
        public static final String TABLA = "disco";
        public static final String NOMBRE = "nombre";
        public static final String IDINTERPRETE = "idinterprete";
        public static final String AUTHORITY = "com.example.david.contentprovider.Proveedor";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public static final String SINGLE_MIME = "vnd.android.cursor.item/vnd" + AUTHORITY + TABLA;
        public static final String MULTIPLE_MIME = "vnd.android.cursor.dir/vnd" + AUTHORITY + TABLA;

    }
}