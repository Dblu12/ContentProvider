package com.example.david.contentprovider;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.david.contentprovider.POJO.Cancion;
import com.example.david.contentprovider.POJO.Disco;
import com.example.david.contentprovider.POJO.GestorCanciones;
import com.example.david.contentprovider.POJO.GestorDisco;
import com.example.david.contentprovider.POJO.GestorInterprete;
import com.example.david.contentprovider.POJO.Interprete;

import java.util.ArrayList;

public class CancionesActivity extends AppCompatActivity {

    private android.widget.ListView lv;
    private AdaptadorCursor ad;

    private Cursor c;


    private GestorDisco gestorDisco;
    private GestorInterprete gestorInterprete;
    private GestorCanciones gestorCanciones;

    private Proveedor proveedor;
    private long id=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bun =super.getIntent().getExtras();
        try {
            id = bun.getLong("id");
        }catch (NullPointerException ex){}
        init();
    }

    public void init(){
        this.lv = (ListView) findViewById(R.id.lv);
//        ad= new AdaptadorCursor(this,c);
//        Cursor c = getContentResolver().query(uri,null,null,null,null);
//        lv.setAdapter(ad);


        gestorCanciones= new GestorCanciones(this);
        gestorCanciones.open();
        gestorDisco= new GestorDisco(this);
        gestorDisco.open();
        gestorInterprete= new GestorInterprete(this);
        gestorInterprete.open();

        proveedor= new Proveedor();
        /*
        ContentValues valores= new ContentValues();
        valores.put(Contrato.TablaCanciones.TITULO, "a");

        valores.put(Contrato.TablaCanciones.IDDISCO,300);
        proveedor.update(Contrato.TablaCanciones.CONTENT_URI, valores,null, null);
        */
        Log.v("Prueba", "id: " + id);
        if(id==-1)
            c=proveedor.query(Contrato.TablaCanciones.CONTENT_URI, null, null, null, null);
        else
            c=proveedor.query(Contrato.TablaCanciones.CONTENT_URI,null, Contrato.TablaCanciones.IDDISCO + "= ?",new String[]{id+""},null);
        c.moveToFirst();
        /*
        canciones= leer();
        int idDisco=-1, idCancion =-1, idInterprete =-1;
        for(Musica m: canciones){
            Log.v("Prueba",m.toString());
            Log.v("Prueba","entra1");
            Log.v("Prueba",m.getIdInterprete()+"");
            if(m.getIdCancion()!=idCancion){
                Cancion ca= new Cancion();
                Log.v("Prueba", m.getCancion());
                ca.setTitulo(m.getCancion());
                ca.setIdDisco(m.getIdDisco());
                idCancion= m.getIdCancion();
                gestorCanciones.insert(ca);
            }
            if(m.getIdDisco() != idDisco){
                Log.v("Prueba","entra2");
                Disco d= new Disco();
                d.setTitulo(m.getAlbum());
                d.setIdInterprete(m.getIdInterprete());
                idDisco= m.getIdDisco();
                gestorDisco.insert(d);
            }


            if(m.getIdInterprete() != idInterprete){
                Log.v("Prueba","entra3");
                Interprete i= new Interprete();
                i.setNombre(m.getInterprete());
                idInterprete= m.getIdInterprete();
                gestorInterprete.insert(i);
            }
        }

*/
        ad= new AdaptadorCursor(this, c);
        //ad = new Adaptador(this, R.layout.list_item, canciones);
        final ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(ad);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(CancionesActivity.this);
                alert.setTitle("Cancion");
                LayoutInflater inflater = LayoutInflater.from(CancionesActivity.this);
                final View vista = inflater.inflate(R.layout.detalle, null);

                alert.setView(vista);


                TextView tvCancion= (TextView) vista.findViewById(R.id.tvcancion);
                TextView tvAlbum= (TextView) vista.findViewById(R.id.tvalbum);
                TextView tvInterprete= (TextView) vista.findViewById(R.id.tvinterprete);

                Cancion can= gestorCanciones.getRow((int) id);
                tvCancion.setText(can.getTitulo());
                Disco d= gestorDisco.getRow(can.getIdDisco());
                tvAlbum.setText(d.getTitulo());
                Interprete i= gestorInterprete.getRow(d.getIdInterprete());
                tvInterprete.setText(i.getNombre());

                alert.show();

            }
        });
    }
}
