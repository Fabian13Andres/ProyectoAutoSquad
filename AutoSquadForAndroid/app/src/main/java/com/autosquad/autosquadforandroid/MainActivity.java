package com.autosquad.autosquadforandroid;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // ---- Mi código:

        EditText correo = findViewById(R.id.editTextTextEmailAddress);
        EditText contra = findViewById(R.id.editTextTextPassword);
        Button btnEntrar = findViewById(R.id.buttonEntrar);

        Button btnRegistro = findViewById(R.id.buttonRegistro);

        btnEntrar.setOnClickListener(v -> {

            if (correo.getText().toString().isEmpty() ||
                    contra.getText().toString().isEmpty()) {

                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            UsuarioFake.nombre = "UsuarioDemo";
            UsuarioFake.correo = correo.getText().toString();

            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        });


        btnRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });


        // ---- Fin de mi código.

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}