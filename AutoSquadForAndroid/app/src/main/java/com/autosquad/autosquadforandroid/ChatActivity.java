package com.autosquad.autosquadforandroid;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recycler;          // RecyclerView donde se muestran los mensajes
    private EditText inputMensaje;          // Campo de texto para escribir mensaje
    private Button btnEnviar;               // Botón para enviar mensaje
    private ChatAdapter adapter;            // Adapter para el RecyclerView
    private List<Mensaje> mensajes = new ArrayList<>(); // Lista de mensajes
    private int idCaja;                     // ID de la caja de la que se está mostrando el chat

    private final String URL_LISTAR = "http://192.130.0.124:8080/autosquad-api/mensaje/list";
    private final String URL_ENVIAR = "http://192.130.0.124:8080/autosquad-api/mensaje/create";

    private Handler handler = new Handler(); // Handler para ejecutar tareas periódicas
    private Runnable runnableActualizar;     // Runnable para actualizar mensajes periódicamente

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Se enlazan los componentes visuales
        recycler = findViewById(R.id.recyclerChat);
        inputMensaje = findViewById(R.id.editTextMensaje);
        btnEnviar = findViewById(R.id.buttonEnviar);

        // Se obtiene el ID de la caja desde el Intent
        idCaja = getIntent().getIntExtra("id_caja", -1);

        // Si no se recibe ID válido, se cierra la actividad
        if (idCaja == -1) {
            Toast.makeText(this, "Error caja", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Configuración del RecyclerView
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChatAdapter(mensajes);
        recycler.setAdapter(adapter);

        // Cargar mensajes iniciales
        cargarMensajes();

        // Configurar botón de enviar mensaje
        btnEnviar.setOnClickListener(v -> enviarMensaje());

        // Configurar actualización automática cada 3 segundos
        runnableActualizar = new Runnable() {
            @Override
            public void run() {
                cargarMensajes();           // Se vuelven a cargar los mensajes
                handler.postDelayed(this, 3000); // Se programa la siguiente actualización
            }
        };
        handler.postDelayed(runnableActualizar, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cuando se destruye la actividad, se detiene la actualización periódica
        handler.removeCallbacks(runnableActualizar);
    }

    // Método para enviar un mensaje al servidor
    private void enviarMensaje() {
        String texto = inputMensaje.getText().toString().trim();
        if (texto.isEmpty()) return; // No enviar si está vacío

        new Thread(() -> {
            try {
                // Construir los datos POST codificados en UTF-8
                String data =
                        "id_usuario=" + URLEncoder.encode(String.valueOf(UsuarioDB.getIdActual()), "UTF-8")
                                + "&id_caja=" + URLEncoder.encode(String.valueOf(idCaja), "UTF-8")
                                + "&texto=" + URLEncoder.encode(texto, "UTF-8");

                URL url = new URL(URL_ENVIAR);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Enviar los datos
                OutputStream os = conn.getOutputStream();
                os.write(data.getBytes());
                os.close();

                // Esperar respuesta (no se usa el contenido)
                conn.getResponseCode();
                conn.disconnect();

                // Actualizar UI
                runOnUiThread(() -> {
                    inputMensaje.setText(""); // Limpiar campo
                    cargarMensajes();         // Recargar mensajes inmediatamente
                });

            } catch (Exception e) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Error al enviar", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    // Método para cargar mensajes desde el servidor
    private void cargarMensajes() {
        new Thread(() -> {
            try {
                URL url = new URL(URL_LISTAR + "?id_caja=" + idCaja);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // Leer la respuesta completa
                Scanner s = new Scanner(conn.getInputStream()).useDelimiter("\\A");
                String response = s.hasNext() ? s.next() : "";
                s.close();
                conn.disconnect();

                JSONObject json = new JSONObject(response);

                if (json.getBoolean("success")) {
                    JSONArray array = json.getJSONArray("mensajes");
                    List<Mensaje> lista = new ArrayList<>();

                    // Se recorre cada mensaje del JSON
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject m = array.getJSONObject(i);

                        lista.add(new Mensaje(
                                m.getInt("id_mensaje"),
                                m.getInt("id_usuario"),
                                m.getString("nombre_usuario"),
                                m.getString("texto"),
                                m.getString("fecha_hora")
                        ));
                    }

                    // Actualizar UI con la lista de mensajes
                    runOnUiThread(() -> {
                        mensajes.clear();
                        mensajes.addAll(lista);
                        adapter.notifyDataSetChanged();
                        recycler.scrollToPosition(mensajes.size() - 1); // Scroll al último mensaje
                    });
                }

            } catch (Exception e) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Error cargando mensajes", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}

// hecho