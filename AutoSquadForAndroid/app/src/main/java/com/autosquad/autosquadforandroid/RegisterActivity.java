package com.autosquad.autosquadforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnCrear = findViewById(R.id.buttonCrearCuenta);

        EditText pass1 = findViewById(R.id.editTextTextPasswordRegistro);
        EditText pass2 = findViewById(R.id.editTextTextRepetirPasswordRegistro);

        btnCrear.setOnClickListener(v -> {

            if (!pass1.getText().toString().equals(pass2.getText().toString())) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cuenta creada", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }

        });

    }
}