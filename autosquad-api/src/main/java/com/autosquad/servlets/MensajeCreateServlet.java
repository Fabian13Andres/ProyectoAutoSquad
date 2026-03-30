package com.autosquad.servlets;

import com.autosquad.db.Conexion;

// import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

// Guardamos nuevo mensaje en la base de datos
@WebServlet("/mensaje/create") // url
public class MensajeCreateServlet extends HttpServlet { // el extends permite usar doPost

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json"); // respuesta sera json
        PrintWriter out = resp.getWriter(); // canal para enviar mensajes

        try {
            // obtenemos los datos
            int id_usuario = Integer.parseInt(req.getParameter("id_usuario"));
            int id_caja = Integer.parseInt(req.getParameter("id_caja"));
            String texto = req.getParameter("texto");

            // creamos conexion
            Connection conn = Conexion.getConnection();

            // creamos consulta
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO MENSAJE (id_usuario, id_caja, texto) VALUES (?, ?, ?)");

            // introducimos los valores en la consulta donde están las "?"
            ps.setInt(1, id_usuario);
            ps.setInt(2, id_caja);
            ps.setString(3, texto);

            // ejecutamos la consulta
            ps.executeUpdate();

            // devolvemos json que dice true
            out.print("{\"success\":true}");

            // cerramos recursos
            ps.close();
            conn.close();

        } catch (Exception e) {
            // Error
            System.out.println("ERROR: " + e.getMessage());
            out.print("{\"success\":false}"); // json error
        }
    }
}

// TODO: Quizás cambiar a try-with-resources para liberar memoria
// Terminado