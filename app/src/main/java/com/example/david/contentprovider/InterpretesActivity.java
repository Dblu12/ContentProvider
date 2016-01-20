package com.example.david.contentprovider;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class InterpretesActivity extends AppCompatActivity {

    private AdaptadorCursorInterprete ad;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        lv = (ListView) this.findViewById(R.id.listView);

        Proveedor proveedor= new Proveedor();

        Cursor c=proveedor.query(Contrato.TablaInterpretes.CONTENT_URI, null, null, null, null);
        c.moveToFirst();

        ad= new AdaptadorCursorInterprete(this, c);

        lv.setAdapter(ad);

    }
}
