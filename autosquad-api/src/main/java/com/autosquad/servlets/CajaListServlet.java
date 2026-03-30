package com.autosquad.servlets;

import com.autosquad.db.Conexion;

// import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

// Esta clase (o servlet) se usa para listar las clases de la BD
@WebServlet("/caja/list") // Modificación de URL
public class CajaListServlet extends HttpServlet { // Heredamos de un servlet que hereda de http (para doPost)

    @Override // Sobreescribimos de nuevo
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json"); // tipo de respuesta
        PrintWriter out = resp.getWriter(); // permite enviarla al cliente

        try (Connection conn = Conexion.getConnection();) {

            String juegoFiltro = req.getParameter("juego"); // obtenemos juego

            // Hacemos la consultaSql
            String consultaSql = "SELECT c.id_caja, c.id_creador, u.nombre AS creador_nombre, " +
                    "c.id_juego, j.nombre AS nombre_juego, " +
                    "c.requisitos, c.jugadores_buscados, UNIX_TIMESTAMP(c.hora_creacion) AS hora_creacion " +
                    "FROM CAJA c " +
                    "JOIN USUARIO u ON c.id_creador = u.id_usuario " +
                    "JOIN JUEGO j ON c.id_juego = j.id_juego";

            if (juegoFiltro != null && !juegoFiltro.isEmpty()) {// Si juego no es null ni vacío
                consultaSql += " WHERE j.nombre = ?"; // lo filtra por ese nombre
            }

            PreparedStatement ps = conn.prepareStatement(consultaSql); // Evitamos inyección SQL

            if (juegoFiltro != null && !juegoFiltro.isEmpty()) { // insertamos de dorma segura
                ps.setString(1, juegoFiltro);
            }

            ResultSet rs = ps.executeQuery(); // ejecutamos consulta y guardamos resultado

            StringBuilder json = new StringBuilder(); // Constructor de string
            json.append("{\"success\":true,\"cajas\":["); // le metemos json al builder

            boolean first = true; // control de comas

            while (rs.next()) { // mientras queden filas

                // Ponemos coma excepto la primera vez
                if (first == false) {
                    json.append(",");
                }

                first = false; // dejamos la coma inicial

                // Construimos nuestro json pero a mano obteniendo cada elemento
                json.append("{")
                        .append("\"id_caja\":").append(rs.getInt("id_caja")).append(",")
                        .append("\"id_creador\":").append(rs.getInt("id_creador")).append(",")
                        .append("\"creador_nombre\":\"").append(rs.getString("creador_nombre")).append("\",")
                        .append("\"id_juego\":").append(rs.getInt("id_juego")).append(",")
                        .append("\"nombre_juego\":\"").append(rs.getString("nombre_juego")).append("\",")
                        .append("\"requisitos\":\"").append(rs.getString("requisitos")).append("\",")
                        .append("\"jugadores_buscados\":").append(rs.getInt("jugadores_buscados")).append(",")
                        .append("\"hora_creacion\":").append(rs.getLong("hora_creacion"))
                        .append("}");
            }

            json.append("]}"); // final de nuestro stringbuider

            out.print(json.toString()); // pasamos el builder a string y se lo mandamos al cliente

            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            out.print("{\"success\":false,\"error\":\"Error interno\"}");// Mendamos json de error
        }
    }
}

// TODO: Quizás cambiar a try-with-resources para liberar memoria
// Terminado