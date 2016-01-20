package com.example.david.contentprovider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 18/01/2016.
 */
public class Adaptador extends ArrayAdapter<Musica> {


    private int res;
    private LayoutInflater lInflator;
    private List<Musica> canciones;
    private Context con;

    static class ViewHolder {

        public TextView tv, tv1;


    }


    public Adaptador(Context context, int resource, List<Musica> objects) {

        super(context, resource, objects);

        this.res = resource; // LAYOUT
        this.canciones = objects; // LISTA DE VALORES
        this.con = context; // ACTIVIDAD

        lInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder gv = new ViewHolder();
        if (convertView == null) {
            convertView = lInflator.inflate(res, null);


            TextView tv = (TextView) convertView.findViewById(R.id.tv1_item);
            gv.tv = tv;
            TextView tv1 = (TextView) convertView.findViewById(R.id.tv2_item);
            gv.tv1 = tv1;

            gv.tv1.setTag(position);
            convertView.setTag(gv);
        } else {
            gv = (ViewHolder) convertView.getTag();
        }
       // addlistener(gv.iv1, position, gv, contactos.get(position));

        gv.tv.setText(canciones.get(position).getCancion());
        gv.tv1.setText(canciones.get(position).getAlbum());
        return convertView;
    }

}
