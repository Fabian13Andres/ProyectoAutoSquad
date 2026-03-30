package com.autosquad.autosquadforandroid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscarCajaActivity extends AppCompatActivity {

    // URL del endpoint que devuelve la lista de cajas desde el servidor
    private final String URL_LISTAR = "http://192.130.0.124:8080/autosquad-api/caja/list";
    private RecyclerView recycler;         // RecyclerView donde se mostrarán las cajas
    private CajaAdapter adapter;           // Adaptador encargado de pintar cada caja
    private List<Caja> cajas = new ArrayList<>();  // Lista local de cajas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_caja);

        // Referencias a los elementos del layout
        Button btnBuscar = findViewById(R.id.buttonBuscarEnBuscarCaja);
        Spinner spinner = findViewById(R.id.spinnerJuegoBuscarCaja);

        recycler = findViewById(R.id.recyclerViewBuscarCaja);

        // Se define que el RecyclerView tendrá un layout vertical tipo lista
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // Se inicializa el adaptador con la lista vacía
        adapter = new CajaAdapter(this, cajas);
        recycler.setAdapter(adapter);

        // Acción al pulsar el botón buscar
        btnBuscar.setOnClickListener(v -> {

            // Obtener el juego seleccionado en el spinner
            String juegoSeleccionado = spinner.getSelectedItem().toString();

            // Se ejecuta la petición en un hilo secundario (no se puede hacer red en el hilo principal)
            new Thread(() -> {
                try {
                    // Se construye la URL enviando el juego como parámetro GET
                    URL url = new URL(URL_LISTAR + "?juego=" + juegoSeleccionado);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    // Se lee la respuesta del servidor
                    Scanner s = new Scanner(conn.getInputStream()).useDelimiter("\\A");
                    String response = s.hasNext() ? s.next() : "";
                    s.close();
                    conn.disconnect();

                    // Se convierte la respuesta en objeto JSON
                    JSONObject json = new JSONObject(response);

                    // Volvemos al hilo principal para actualizar la interfaz
                    runOnUiThread(() -> {
                        try {
                            if (json.getBoolean("success")) {

                                // Obtenemos el array de cajas recibido desde el servidor
                                JSONArray array = json.getJSONArray("cajas");
                                List<Caja> lista = new ArrayList<>();

                                long ahora = System.currentTimeMillis(); // Hora actual en milisegundos

                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject c = array.getJSONObject(i);

                                    // La hora viene en segundos desde PHP, la convertimos a milisegundos
                                    long horaCreacion = c.getLong("hora_creacion") * 1000;

                                    // Solo mostrar cajas que tengan menos de 1 hora de antigüedad
                                    if (ahora - horaCreacion > 3600 * 1000) continue;

                                    // Crear objeto Caja con los datos recibidos
                                    Caja caja = new Caja(
                                            c.getInt("id_caja"),
                                            c.getInt("id_creador"),
                                            c.getString("creador_nombre"),
                                            c.getInt("id_juego"),
                                            c.getString("nombre_juego"),
                                            c.getString("requisitos"),
                                            c.getInt("jugadores_buscados"),
                                            horaCreacion
                                    );

                                    lista.add(caja);
                                }

                                // Se actualiza el adaptador con la nueva lista filtrada
                                adapter.setLista(lista);

                            } else {
                                Toast.makeText(this, "Error al obtener cajas", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(this, "Error JSON", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    });

                } catch (Exception e) {
                    // En caso de error de red, se muestra en pantalla
                    runOnUiThread(() ->
                            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                    e.printStackTrace();
                }
            }).start();
        });
    }
}

// hecho