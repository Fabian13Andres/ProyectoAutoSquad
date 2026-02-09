package com.autosquad.autosquadforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BuscarCajaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_caja);

        if (BaseDatosFake.cajas.isEmpty()) {
            BaseDatosFake.cajas.add(new Caja("Valorant", " Paco", "Diamante+", 2));
            BaseDatosFake.cajas.add(new Caja("LoL", "Fer", "Oro+", 1));
            BaseDatosFake.cajas.add(new Caja("CS2", "Agustín51", "Global Elite", 3));
        }

        Button btnBuscar = findViewById(R.id.buttonBuscarEnBuscarCaja);

        RecyclerView recycler = findViewById(R.id.recyclerViewBuscarCaja);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        List<Caja> cajas = BaseDatosFake.cajas;
        CajaAdapter adapter = new CajaAdapter(cajas);
        recycler.setAdapter(adapter);

        btnBuscar.setOnClickListener(v -> {
            startActivity(new Intent(BuscarCajaActivity.this, ChatActivity.class));
        });
    }
}
