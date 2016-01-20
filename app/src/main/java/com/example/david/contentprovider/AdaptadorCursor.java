package com.example.david.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.contentprovider.POJO.Disco;
import com.example.david.contentprovider.POJO.GestorDisco;

public class AdaptadorCursor extends CursorAdapter {
    private GestorDisco gd;
    public AdaptadorCursor(Context context, Cursor c) {
        super(context, c, true);
        gd= new GestorDisco(context);
        gd.open();

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());

        View v = i.inflate(R.layout.list_item, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv1 = (TextView) view.findViewById(R.id.tv1_item);
        tv1.setText(cursor.getString(1));
        TextView tv2 = (TextView) view.findViewById(R.id.tv2_item);
        Disco d=gd.getRow(Integer.parseInt(cursor.getString(2)));
        tv2.setText(d.getTitulo());
        ImageView iv= (ImageView) view.findViewById(R.id.imageView);

        //gd.close();
    }
}
