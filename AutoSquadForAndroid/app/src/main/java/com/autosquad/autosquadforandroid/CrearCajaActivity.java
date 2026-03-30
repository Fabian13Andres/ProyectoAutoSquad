package com.autosquad.autosquadforandroid;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class CrearCajaActivity extends AppCompatActivity {

    private Spinner spinnerJuego;       // Spinner para seleccionar el juego
    private EditText req, jugadoresET;  // EditTexts para requisitos y cantidad de jugadores
    private Button btnCrear;            // Botón para crear la caja

    private final String URL_CREAR_CAJA = "http://192.130.0.124:8080/autosquad-api/caja/create";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_caja);

        // Vincular vistas con el layout
        spinnerJuego = findViewById(R.id.spinnerJuego);
        req = findViewById(R.id.editTextTextRequisitos);
        jugadoresET = findViewById(R.id.editTextNumberJugadores);
        btnCrear = findViewById(R.id.buttonCrear);

        // Crear lista de juegos para el Spinner
        List<Juego> listaJuegos = new ArrayList<>();
        listaJuegos.add(new Juego(1, "Valorant"));
        listaJuegos.add(new Juego(2, "LoL"));
        listaJuegos.add(new Juego(3, "CS2"));

        // Adaptador para mostrar los juegos en el Spinner
        ArrayAdapter<Juego> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listaJuegos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJuego.setAdapter(adapter);

        // Listener para el botón de crear caja
        btnCrear.setOnClickListener(v -> {
            final int idUsuario = UsuarioDB.getIdActual(); // ID del usuario actual
            final int idJuego = spinnerJuego.getSelectedItemPosition() + 1; // ID del juego seleccionado
            final String requisitos = req.getText().toString(); // Requisitos escritos por el usuario
            final int jugadores;

            // Validar que el número de jugadores sea un entero válido
            try {
                jugadores = Integer.parseInt(jugadoresET.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Número de jugadores inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            // Hilo para hacer la petición POST a la API
            new Thread(() -> {
                try {
                    // Construir los parámetros codificados en URL
                    String data = "id_creador=" + URLEncoder.encode(String.valueOf(idUsuario), "UTF-8")
                            + "&id_juego=" + URLEncoder.encode(String.valueOf(idJuego), "UTF-8")
                            + "&requisitos=" + URLEncoder.encode(requisitos, "UTF-8")
                            + "&jugadores=" + URLEncoder.encode(String.valueOf(jugadores), "UTF-8");

                    // Abrir conexión HTTP
                    URL url = new URL(URL_CREAR_CAJA);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    // Enviar datos
                    OutputStream os = conn.getOutputStream();
                    os.write(data.getBytes());
                    os.flush();
                    os.close();

                    // Obtener respuesta del servidor
                    int responseCode = conn.getResponseCode();
                    java.util.Scanner s = new java.util.Scanner(conn.getInputStream()).useDelimiter("\\A");
                    String response = s.hasNext() ? s.next() : "";
                    s.close();
                    conn.disconnect();

                    // Actualizar UI en el hilo principal
                    runOnUiThread(() -> {
                        try {
                            JSONObject json = new JSONObject(response);
                            if (json.getBoolean("success")) {
                                Toast.makeText(this, "Caja creada", Toast.LENGTH_SHORT).show();
                                finish(); // Cerrar la actividad después de crear la caja
                            } else {
                                Toast.makeText(this, json.getString("error"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                }
            }).start();
        });
    }

    // Clase interna para representar un juego en el Spinner
    public static class Juego {
        private int id;       // ID del juego
        private String nombre; // Nombre del juego

        public Juego(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() { return id; }

        // Mostrar el nombre en el Spinner
        @Override
        public String toString() { return nombre; }
    }
}

// hecho