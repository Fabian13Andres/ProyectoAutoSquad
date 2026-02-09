package com.autosquad.autosquadforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String usuario = getIntent().getStringExtra("usuario");

        TextView bienvenida = findViewById(R.id.textViewBienvenida);
        bienvenida.setText("Bienvenido " + usuario);

        Button btnCrear = findViewById(R.id.buttonCrearCaja);
        Button btnBuscar = findViewById(R.id.buttonBuscarCaja);
        Button btnPerfil = findViewById(R.id.buttonPerfil);
        Button btnCerrar = findViewById(R.id.buttonCerrarSesion);

        btnCrear.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, CrearCajaActivity.class));
        });

        btnBuscar.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, BuscarCajaActivity.class));
        });

        btnPerfil.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, PerfilActivity.class));
        });

        btnCerrar.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish(); // opcional, para no volver atrás con el botón
        });
    }
}
