package com.autosquad.autosquadforandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CajaAdapter extends RecyclerView.Adapter<CajaAdapter.ViewHolder> {

    List<Caja> lista;

    public CajaAdapter(List<Caja> lista) {
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView juego, requisitos, jugadores;

        public ViewHolder(View v) {
            super(v);
            juego = v.findViewById(R.id.textJuego);
            requisitos = v.findViewById(R.id.textViewRequisitos);
            jugadores = v.findViewById(R.id.textViewJugadores);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_caja, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Caja c = lista.get(position);
        holder.juego.setText(c.getJuego());
        holder.requisitos.setText(c.getRequisitos());
        holder.jugadores.setText("Jugadores: " + c.getJugadores());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
