package com.autosquad.db;

import java.sql.Connection;
import java.sql.DriverManager;

// Esta clase se encarga de abrir una conexión y pasarla a mi api cuando la requiera
public class Conexion {

    // URL ponemos el tipo de BD/ donde está (localhost): puerto MariaDB/ nombre
    private static final String URL = "jdbc:mariadb://localhost:3306/AutoSquad";
    // Usuario
    private static final String USER = "root";
    // Contraseña
    private static final String PASS = "";

    public static Connection getConnection() { // Método que abre la conexión

        // Objeto tipo connection
        Connection con;

        try {
            // Cargamos el driver de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            // Se establece la conexión
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            // Mostramos error
            System.out.println("Error: " + e.getMessage());
            // Si no funciona paso null
            con = null;
        }
        // Devolvemos un objeto conexión o null
        return con;
    }
}

// Terminado