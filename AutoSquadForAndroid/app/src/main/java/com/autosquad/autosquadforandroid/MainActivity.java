package com.autosquad.autosquadforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private final String URL_LOGIN = "http://192.130.0.124:8080/autosquad-api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText correo = findViewById(R.id.editTextTextEmailAddress);
        EditText contra = findViewById(R.id.editTextTextPassword);
        Button btnEntrar = findViewById(R.id.buttonEntrar);
        Button btnRegistro = findViewById(R.id.buttonRegistro);

        btnEntrar.setOnClickListener(v -> {

            String email = correo.getText().toString();
            String pass = contra.getText().toString();

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                HttpURLConnection conn = null;

                try {
                    String data = "correo=" + URLEncoder.encode(email, "UTF-8")
                            + "&contra=" + URLEncoder.encode(pass, "UTF-8");

                    URL url = new URL(URL_LOGIN);
                    conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    // Enviar datos
                    OutputStream os = conn.getOutputStream();
                    os.write(data.getBytes("UTF-8"));
                    os.flush();
                    os.close();

                    int responseCode = conn.getResponseCode();
                    Log.d("HTTP_CODE", String.valueOf(responseCode));

                    BufferedReader reader;

                    if (responseCode >= 200 && responseCode < 300) {
                        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    } else {
                        reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    }

                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();

                    String jsonResponse = response.toString();
                    Log.d("LOGIN_RESPONSE", jsonResponse);

                    if (jsonResponse.isEmpty()) {
                        throw new Exception("Respuesta vacía del servidor");
                    }

                    JSONObject json;
                    try {
                        json = new JSONObject(jsonResponse);
                    } catch (Exception e) {
                        throw new Exception("Respuesta no JSON: " + jsonResponse);
                    }

                    runOnUiThread(() -> {
                        try {
                            if (json.getBoolean("success")) {

                                int id = json.optInt("id", -1);
                                String nombre = json.optString("nombre", "");

                                if (id == -1 || nombre.isEmpty()) {
                                    Toast.makeText(this, "Datos de usuario incompletos", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                UsuarioDB.setUsuarioActual(id, nombre, email);

                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                intent.putExtra("usuario", nombre);
                                startActivity(intent);
                                finish();

                            } else {
                                String error = json.optString("error", "Error desconocido");
                                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(this, "Error procesando JSON", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception e) {
                    runOnUiThread(() ->
                            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show()
                    );
                    e.printStackTrace();
                } finally {
                    if (conn != null) conn.disconnect();
                }

            }).start();
        });

        btnRegistro.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RegisterActivity.class))
        );
    }
}

// hecho