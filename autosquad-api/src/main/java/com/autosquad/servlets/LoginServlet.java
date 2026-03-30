package com.autosquad.servlets;

import com.autosquad.db.Conexion;

// import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

@WebServlet("/login") // crea endpoint para nueva url
public class LoginServlet extends HttpServlet { // permite usar doPost

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");// respuesta json
        resp.setCharacterEncoding("UTF-8");// codificación

        PrintWriter out = resp.getWriter(); // canal por donde enviamos la respuesta

        try {
            // obtenemos datos
            String correo = req.getParameter("correo");
            String contra = req.getParameter("contra");

            if (correo == null || contra == null) {

                // error
                out.print("{\"success\":false,\"error\":\"Faltan campos\"}");

            } else {

                Connection conn = Conexion.getConnection(); // creamos conexion

                // preparamos la consulta
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT id_usuario, nombre, contra FROM USUARIO WHERE correo=?");

                // metemos el correo
                ps.setString(1, correo);
                ResultSet rs = ps.executeQuery(); // ejecutamos y recogemos el resultado

                if (!rs.next()) { // si no hay lineas salimos
                    out.print("{\"success\":false,\"error\":\"Usuario no encontrado\"}");

                } else {

                    String contraDB = rs.getString("contra"); // obtenemos contra

                    if (!contra.equals(contraDB)) { // si no hay contra salimos

                        out.print("{\"success\":false,\"error\":\"Contraseña incorrecta\"}");

                    } else {

                        // si hay contraseña devolvemos que todo correcto
                        out.print("{\"success\":true," +
                                "\"id\":" + rs.getInt("id_usuario") + "," +
                                "\"nombre\":\"" + rs.getString("nombre") + "\"}");
                    }

                }

                // cerramos lo usado
                rs.close();
                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            // Error
            System.out.println("ERROR: " + e.getMessage());
            out.print("{\"success\":false,\"error\":\"Error interno\"}"); // devolvemos json error
        }
    }
}

// TODO: Quizás cambiar a try-with-resources para liberar memoria
// Terminado