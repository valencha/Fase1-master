package com.example.estudiante.fase1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CitaList extends ArrayAdapter<Cita> {

    private Activity context;
    private List<Cita> citaList;

    public CitaList(Activity context,List<Cita>citaList){

        super(context,R.layout.list,citaList);
        this.context=context;
        this.citaList = citaList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list,null,true);
        TextView tv_name= listViewItem.findViewById(R.id.tv_titulo);
        TextView tv_importancia= listViewItem.findViewById(R.id.tv_importancia);

        Cita cita  = citaList.get(position);

        tv_name.setText(cita.getCitaTitulo());
        tv_importancia.setText(cita.getImportancia());

        return listViewItem;

    }
}
