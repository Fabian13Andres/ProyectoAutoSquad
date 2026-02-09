package com.autosquad.autosquadforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Button btnJuegos = findViewById(R.id.buttonMisJuegos);

        TextView nombre = findViewById(R.id.textViewNombreUsuario);
        TextView correo = findViewById(R.id.textViewCorreo);

        nombre.setText(UsuarioFake.nombre);
        correo.setText(UsuarioFake.correo);


        btnJuegos.setOnClickListener(v -> {
            startActivity(new Intent(PerfilActivity.this, HomeActivity.class));
        });
    }
}
