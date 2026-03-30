package com.autosquad.servlets;

import com.autosquad.db.Conexion;

// import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

// ESta clse recibe un POST para una nueva cajay la guarda en la BD (devuelve json indicando si funciona o si no)
@WebServlet("/caja/create") // Modificamos la URL
public class CajaCreateServlet extends HttpServlet { // Heredamos de un servlet que hereda de http (para doPost)

    @Override // Para no crear un metodo parecido a doPost lo sobrescribimos
    // doPost: se ejecuta cuando llega un Post
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Protected: para que no todo el mundo pueda acceder innecesariamente
        // req: datos enviados por el cliente
        // resp: devuelve la respuesta al cliente
        // throws IOException: java nos obliga a indicar que lanzamos esa excepción

        resp.setContentType("application/json"); // indicamos que la respuesta será json
        PrintWriter out = resp.getWriter(); // le pasas al objeto PrintWriter el receptor de la respuesta
        String respuesta;

        // Usamos la clase conexion antes creada para conectarnos
        try (Connection conn = Conexion.getConnection();
                // Creamos una consulta y la guardamos en ps usando Connection
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO CAJA (id_creador, id_juego, visibilidad, requisitos, jugadores_buscados) VALUES (?, ?, 1, ?, ?)");) {

            // OJO: debemos usar el try-with-resources porque necesitamos que se cierren si
            // salta error para ahorrar memoria

            // Obtenemos creador, juego, requisitos y nº de jugadores (y parseamos)
            int id_creador = Integer.parseInt(req.getParameter("id_creador")); // obtenemos parametros
            int id_juego = Integer.parseInt(req.getParameter("id_juego"));
            String requisitos = req.getParameter("requisitos");
            int jugadores = Integer.parseInt(req.getParameter("jugadores"));

            // Asignamos los parámetros de la caja (cada uno irá a un signo "?")
            ps.setInt(1, id_creador);
            ps.setInt(2, id_juego);
            ps.setString(3, requisitos);
            ps.setInt(4, jugadores);

            // Ejecutamos la consulta (creamos la caja de chat)
            ps.executeUpdate();

            // Si todo va bien:
            respuesta = "{\"success\":true}";

        } catch (Exception e) {
            // Mostramos excepción y pasamos el error al cliente
            System.out.println("ERROR: " + e.getMessage());
            respuesta = "{\"success\":false,\"error\":\"Error interno\"}";
        }
        // Mandamos la respuesta
        out.print(respuesta);
    }
}

// Terminado