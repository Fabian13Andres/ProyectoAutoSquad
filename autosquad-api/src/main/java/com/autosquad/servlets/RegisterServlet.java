package com.autosquad.servlets;

import com.autosquad.db.Conexion;

// import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

// este server sirve para el funcionamiento del resgistro de nuevos usuarios en la base de datos
@WebServlet("/register") // url
public class RegisterServlet extends HttpServlet {// el extends permite usar doPost

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");// respuesta sera json
        PrintWriter out = resp.getWriter(); // canal para enviar mensajes

        try {
            // obtenemos los parámetros
            String nombre = req.getParameter("nombre");
            String correo = req.getParameter("correo");
            String contra = req.getParameter("contra");

            if (nombre == null || correo == null || contra == null) {
                // Si algo sale null salimos
                out.print("{\"success\":false,\"error\":\"Faltan campos\"}");

            } else {

                Connection conn = Conexion.getConnection(); // creamos la conexión

                // Usamos PreparedStatement para preaprar la consulta sin inyecciones sql
                PreparedStatement check = conn.prepareStatement(
                        "SELECT id_usuario FROM USUARIO WHERE correo=?");

                check.setString(1, correo); // metemos el dato (correo)

                ResultSet rs = check.executeQuery(); // ejecutamos la consulta y la guardamos

                if (rs.next()) { // si la consulta nos devolvió algo salimos (ya existe)
                    out.print("{\"success\":false,\"error\":\"Usuario ya existe\"}");

                } else {

                    // si no existe metemos nuevo usuario
                    PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO USUARIO (nombre, correo, contra) VALUES (?, ?, ?)");

                    // metemos datos
                    ps.setString(1, nombre);
                    ps.setString(2, correo);
                    ps.setString(3, contra);

                    // ejecutamos
                    ps.executeUpdate();

                    // si se insertó correctamente devolvemos true en el json para el cliente
                    out.print("{\"success\":true}");

                    // liberamos
                    ps.close();
                }

                // liberamos recursos
                rs.close();
                check.close();
                conn.close();
            }

        } catch (Exception e) {
            // error
            System.out.println("ERROR: " + e.getMessage());
            out.print("{\"success\":false,\"error\":\"Error interno\"}"); // json error
        }
    }
}

// TODO: Quizás cambiar a try-with-resources para liberar memoria
// Terminado