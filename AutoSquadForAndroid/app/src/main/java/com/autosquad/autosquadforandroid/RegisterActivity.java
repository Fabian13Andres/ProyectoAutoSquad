package com.autosquad.autosquadforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

// Actividad que permite registrar un nuevo usuario en la app
public class RegisterActivity extends AppCompatActivity {

    private EditText editPass1, editPass2, editEmail, editNombre; // Campos de texto del formulario
    private Button btnCrear; // Botón para enviar el registro
    private final String URL_REGISTER = "http://192.130.0.124:8080/autosquad-api/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicialización de los campos de la interfaz
        editPass1 = findViewById(R.id.editTextTextPasswordRegistro);
        editPass2 = findViewById(R.id.editTextTextRepetirPasswordRegistro);
        editEmail = findViewById(R.id.editTextTextEmailAddressRegistro);
        editNombre = findViewById(R.id.editTextNombre);
        btnCrear = findViewById(R.id.buttonCrearCuenta);

        // Listener del botón de crear cuenta
        btnCrear.setOnClickListener(v -> {
            String nombre = editNombre.getText().toString();
            String email = editEmail.getText().toString();
            String pass1 = editPass1.getText().toString();
            String pass2 = editPass2.getText().toString();

            // Validación de campos vacíos
            if (nombre.isEmpty() || email.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validación de contraseñas coincidentes
            if (!pass1.equals(pass2)) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ejecutar el registro en el servidor en un hilo separado
            new Thread(() -> {
                try {
                    // Preparar los datos para enviar por POST
                    String data = "nombre=" + URLEncoder.encode(nombre, "UTF-8")
                            + "&correo=" + URLEncoder.encode(email, "UTF-8")
                            + "&contra=" + URLEncoder.encode(pass1, "UTF-8");

                    // Crear la conexión HTTP
                    URL url = new URL(URL_REGISTER);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true); // Habilitar envío de datos
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    // Enviar los datos al servidor
                    OutputStream os = conn.getOutputStream();
                    os.write(data.getBytes());
                    os.flush();
                    os.close();

                    // Comprobar código de respuesta HTTP
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Leer respuesta del servidor
                        java.util.Scanner s = new java.util.Scanner(conn.getInputStream()).useDelimiter("\\A");
                        String response = s.hasNext() ? s.next() : "";
                        s.close();

                        // Parsear JSON recibido
                        JSONObject json = new JSONObject(response);
                        runOnUiThread(() -> {
                            try {
                                if (json.getBoolean("success")) {
                                    // Registro exitoso: mostrar mensaje y volver al login
                                    Toast.makeText(this, "Cuenta creada", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(this, MainActivity.class));
                                    finish();
                                } else {
                                    // Mostrar error devuelto por el servidor
                                    Toast.makeText(this, json.getString("error"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                // Error al parsear JSON
                                Toast.makeText(this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        // Código HTTP diferente a 200
                        runOnUiThread(() -> Toast.makeText(this, "Error HTTP: " + responseCode, Toast.LENGTH_SHORT).show());
                    }

                    conn.disconnect();
                } catch (Exception e) {
                    // Captura de errores generales de conexión
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                }
            }).start();
        });
    }
}

// hecho