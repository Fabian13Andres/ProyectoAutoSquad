package com.autosquad.autosquadforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Asociar layout

        // Obtener nombre de usuario pasado desde la actividad anterior
        String usuario = getIntent().getStringExtra("usuario");

        // Mostrar mensaje de bienvenida
        TextView bienvenida = findViewById(R.id.textViewBienvenida);
        bienvenida.setText("Bienvenido " + usuario);

        // Referencias a los botones del layout
        Button btnCrear = findViewById(R.id.buttonCrearCaja);
        Button btnBuscar = findViewById(R.id.buttonBuscarCaja);
        Button btnPerfil = findViewById(R.id.buttonPerfil);
        Button btnCerrar = findViewById(R.id.buttonCerrarSesion);

        // Abrir actividad para crear una nueva caja
        btnCrear.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, CrearCajaActivity.class));
        });

        // Abrir actividad para buscar cajas
        btnBuscar.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, BuscarCajaActivity.class));
        });

        // Abrir perfil del usuario
        btnPerfil.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, PerfilActivity.class));
        });

        // Cerrar sesión y volver a la pantalla principal (login)
        btnCerrar.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish(); // Evitar que el usuario vuelva a Home con el botón atrás
        });
    }
}

// hecho