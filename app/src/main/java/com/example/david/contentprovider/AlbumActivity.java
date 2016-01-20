package com.example.david.contentprovider;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.david.contentprovider.POJO.Cancion;
import com.example.david.contentprovider.POJO.Disco;
import com.example.david.contentprovider.POJO.Interprete;

public class AlbumActivity extends AppCompatActivity {

    private AdaptadorCursorDisco ad;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        lv = (ListView) this.findViewById(R.id.listView);

        Proveedor proveedor= new Proveedor();

        Cursor c=proveedor.query(Contrato.TablaDisco.CONTENT_URI, null, null, null, null);
        c.moveToFirst();

        ad= new AdaptadorCursorDisco(this, c);

        lv.setAdapter(ad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i= new Intent(AlbumActivity.this, CancionesActivity.class);
                Bundle bun= new Bundle();
                bun.putLong("id", id);
                i.putExtras(bun);
                startActivity(i);

            }
        });
    }
}
