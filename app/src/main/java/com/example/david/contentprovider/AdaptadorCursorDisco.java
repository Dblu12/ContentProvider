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

/**
 * Created by David on 20/01/2016.
 */
public class AdaptadorCursorDisco extends CursorAdapter {

    public AdaptadorCursorDisco(Context context, Cursor c) {
        super(context, c, true);


    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());

        View v = i.inflate(R.layout.list_album, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv1 = (TextView) view.findViewById(R.id.tv_album);
        tv1.setText(cursor.getString(1));

    }
}

