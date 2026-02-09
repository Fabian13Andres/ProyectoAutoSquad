package com.autosquad.autosquadforandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Button btnEnviar = findViewById(R.id.buttonEnviarMensaje);

        btnEnviar.setOnClickListener(v -> {
            Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
        });

    }
}
