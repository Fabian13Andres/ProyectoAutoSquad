package com.autosquad.autosquadforandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class CrearCajaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_caja);

        // List<Caja> cajas = new ArrayList<>();
        // cajas.add(new Caja("Valorant", "Alyfa13", "Ser bueno5", 5));
        // cajas.add(new Caja("LoL", "Player24", "Mayor de edad", 2));


        Button btnCrear = findViewById(R.id.buttonCrear);

        btnCrear.setOnClickListener(v -> {

            BaseDatosFake.cajas.add(
                    new Caja("NuevoJuego", "Usuario", "Sin requisitos", 1)
            );

            Toast.makeText(this, "Caja creada", Toast.LENGTH_SHORT).show();
            finish();
        });


    }
}
