package com.autosquad.autosquadforandroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CajaAdapter extends RecyclerView.Adapter<CajaAdapter.ViewHolder> {

    // Lista de cajas que se van a mostrar en el RecyclerView
    private List<Caja> lista;

    // Contexto necesario para poder lanzar actividades
    private Context context;

    // Constructor que recibe el contexto y la lista inicial
    public CajaAdapter(Context context, List<Caja> lista) {
        this.context = context;
        this.lista = lista;
    }

    // ViewHolder que representa cada elemento visual de una caja
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Referencias a los elementos del layout item_caja
        TextView juego, requisitos, jugadores, creador;
        Button buttonVer;

        public ViewHolder(View v) {
            super(v);

            // Se enlazan los componentes visuales del layout
            juego = v.findViewById(R.id.textJuego);
            requisitos = v.findViewById(R.id.textViewRequisitos);
            jugadores = v.findViewById(R.id.textViewJugadores);
            creador = v.findViewById(R.id.textViewCreador);
            buttonVer = v.findViewById(R.id.buttonVer);
        }
    }

    // Se crea la vista para cada elemento de la lista
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Se infla el layout item_caja para cada fila
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_caja, parent, false);

        return new ViewHolder(v);
    }

    // Se rellenan los datos en cada posición del RecyclerView
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Caja c = lista.get(position);

        // Si la caja no es reciente (más de 1 hora), se oculta visualmente
        if (!c.esReciente()) {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0,0));
            return;
        }

        // Se muestran los datos básicos de la caja
        holder.juego.setText(c.getJuego());
        holder.requisitos.setText(c.getRequisitos());
        holder.jugadores.setText("Jugadores: " + c.getJugadores());

        // Si el usuario actual es el creador, se muestra "Creador"
        // En caso contrario, se muestra el nombre del creador real
        if (UsuarioDB.getIdActual() == c.getIdCreador()) {
            holder.creador.setText("Creador");
        } else {
            holder.creador.setText(c.getCreadorNombre());
        }

        // Al pulsar el botón "Ver", se abre el chat de esa caja
        holder.buttonVer.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatActivity.class);

            // Se envía el id de la caja al chat
            intent.putExtra("id_caja", c.getId());

            context.startActivity(intent);
        });

        // Si se pulsa el nombre del creador y no soy yo,
        // se abre la pantalla del perfil de ese usuario
        holder.creador.setOnClickListener(v -> {
            if (UsuarioDB.getIdActual() != c.getIdCreador()) {

                Intent intent = new Intent(context, PerfilOtroUsuarioActivity.class);

                // Se pasa el nombre del usuario al perfil
                intent.putExtra("nombre", c.getCreadorNombre());

                context.startActivity(intent);
            }
        });
    }

    // Método para actualizar completamente la lista de cajas
    public void setLista(List<Caja> nuevaLista) {
        this.lista = nuevaLista;
        notifyDataSetChanged(); // Notifica al RecyclerView que redibuje
    }

    // Devuelve el número total de elementos
    @Override
    public int getItemCount() {
        return lista.size();
    }
}

// hecho