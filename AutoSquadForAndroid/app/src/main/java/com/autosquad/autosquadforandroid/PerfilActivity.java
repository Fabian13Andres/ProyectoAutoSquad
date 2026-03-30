package com.autosquad.autosquadforandroid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Actividad que muestra y permite editar los datos del perfil del usuario
public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Obtener referencias a los elementos de la interfaz
        TextView nombre = findViewById(R.id.textViewNombreUsuario);
        TextView correo = findViewById(R.id.textViewCorreo);
        EditText misJuegos = findViewById(R.id.editTextMisJuegos);
        Button btnEditarPerfil = findViewById(R.id.buttonEditarPerfil);

        // Cargar los datos actuales del usuario desde la base de datos local (UsuarioDB)
        nombre.setText(UsuarioDB.getNombreActual());
        correo.setText(UsuarioDB.getCorreoActual());
        misJuegos.setText(UsuarioDB.getMisJuegosActual());

        // Listener para el botón de editar perfil
        btnEditarPerfil.setOnClickListener(v -> {
            // Guardar los cambios en la base de datos local
            UsuarioDB.setNombreActual(nombre.getText().toString());
            UsuarioDB.setCorreoActual(correo.getText().toString());
            UsuarioDB.setMisJuegosActual(misJuegos.getText().toString());

            // Notificar al usuario que se actualizaron los datos
            Toast.makeText(this, "Perfil actualizado", Toast.LENGTH_SHORT).show();
        });
    }
}

// hecho