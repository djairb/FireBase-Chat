package com.example.djairb.chatfirebaseproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PessoasAdapter extends ArrayAdapter<Usuario> {
    private final ArrayList<Usuario> usuarioList;
    private final Context context;


    public PessoasAdapter(Context context, ArrayList<Usuario> usuarioList){

        super(context, R.layout.linha_usuario, usuarioList);
        this.context = context;
        this.usuarioList = usuarioList;

    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_usuario, parent, false);
        TextView nome = rowView.findViewById(R.id.nomeUsuarioLinha);

        nome.setText(usuarioList.get(position).getNome());

        return rowView;

    }
}
