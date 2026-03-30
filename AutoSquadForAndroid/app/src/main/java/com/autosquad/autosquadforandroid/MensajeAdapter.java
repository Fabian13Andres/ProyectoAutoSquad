package com.autosquad.autosquadforandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adaptador para mostrar una lista de mensajes en un RecyclerView
public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.MensajeViewHolder> {

    // Lista de mensajes (en este caso como Strings simples)
    private List<String> listaMensajes;

    // Constructor del adaptador que recibe la lista de mensajes
    public MensajeAdapter(List<String> listaMensajes){
        this.listaMensajes = listaMensajes;
    }

    // Inflar la vista para cada ítem de mensaje
    @NonNull
    @Override
    public MensajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Usamos el layout simple predeterminado de Android
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1,parent,false);
        return new MensajeViewHolder(v);
    }

    // Vincular los datos del mensaje con la vista
    @Override
    public void onBindViewHolder(@NonNull MensajeViewHolder holder, int position) {
        // Mostrar el texto del mensaje en el TextView
        holder.texto.setText(listaMensajes.get(position));
    }

    // Número de mensajes que tiene la lista
    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    // ViewHolder que contiene los elementos de cada ítem de mensaje
    public static class MensajeViewHolder extends RecyclerView.ViewHolder{
        TextView texto; // TextView para mostrar el mensaje

        public MensajeViewHolder(@NonNull View itemView) {
            super(itemView);
            // Asociamos el TextView con el ID predeterminado del layout simple_list_item_1
            texto = itemView.findViewById(android.R.id.text1);
        }
    }
}

// hecho