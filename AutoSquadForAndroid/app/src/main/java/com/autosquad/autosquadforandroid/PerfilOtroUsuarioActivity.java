package com.autosquad.autosquadforandroid;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// Actividad que muestra el perfil de otro usuario
public class PerfilOtroUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_otro_usuario);

        // Referencia al TextView donde se mostrará el nombre del otro usuario
        TextView nombre = findViewById(R.id.textViewNombreOtro);

        // Recibir el nombre del usuario pasado desde la actividad anterior a través del Intent
        String nombreUsuario = getIntent().getStringExtra("nombre");

        // Mostrar el nombre en el TextView
        nombre.setText(nombreUsuario);
    }
}

// hecho