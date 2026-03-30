package com.autosquad.autosquadforandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<Mensaje> lista; // Lista de mensajes que se mostrará en el RecyclerView

    // Constructor del adapter, recibe la lista de mensajes
    public ChatAdapter(List<Mensaje> lista) {
        this.lista = lista;
    }

    // Clase ViewHolder que contiene los elementos de cada item del RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView texto; // TextView donde se mostrará el mensaje

        public ViewHolder(View v) {
            super(v);
            // Se enlaza el TextView del layout item_mensaje.xml
            texto = v.findViewById(R.id.textViewMensaje);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar el layout de cada mensaje y crear un ViewHolder
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mensaje, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Obtener el mensaje correspondiente a la posición
        Mensaje m = lista.get(position);

        // Mostrar el nombre del usuario y el texto del mensaje
        holder.texto.setText(m.getNombreUsuario() + ": " + m.getTexto());
    }

    @Override
    public int getItemCount() {
        // Retorna la cantidad de mensajes en la lista
        return lista.size();
    }
}

// hecho