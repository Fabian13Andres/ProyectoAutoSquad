package com.autosquad.servlets;

import com.autosquad.db.Conexion;

// import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

// mostramos al usuario todos los mensajes de la caja que necesite ordenados por tiempo
@WebServlet("/mensaje/list") // url
public class MensajeListServlet extends HttpServlet {// el extends permite usar doGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");// respuesta sera json
        PrintWriter out = resp.getWriter(); // canal para enviar mensajes

        try {
            // id caja
            int id_caja = Integer.parseInt(req.getParameter("id_caja"));

            // abrimos conexion
            Connection conn = Conexion.getConnection();

            // preparamos la consulta
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT M.id_mensaje, M.id_usuario, U.nombre, M.texto, M.fecha_hora " +
                            "FROM MENSAJE M " +
                            "JOIN USUARIO U ON M.id_usuario = U.id_usuario " +
                            "WHERE M.id_caja = ? ORDER BY M.fecha_hora ASC");

            // metemos datos evitando inyección
            ps.setInt(1, id_caja);
            // ejecutamos y recogemos resultado
            ResultSet rs = ps.executeQuery();

            // contructor para formatear el string
            StringBuilder json = new StringBuilder();
            json.append("{\"success\":true,\"mensajes\":["); // inicio del json

            // si es el primero no hay coma ","
            boolean first = true;

            while (rs.next()) {
                if (!first) {
                    json.append(",");
                }

                first = false; // para que ponga comas

                // metemos el resto de mensajes
                json.append("{")
                        .append("\"id_mensaje\":").append(rs.getInt("id_mensaje")).append(",")
                        .append("\"id_usuario\":").append(rs.getInt("id_usuario")).append(",")
                        .append("\"nombre_usuario\":\"").append(rs.getString("nombre")).append("\",")
                        .append("\"texto\":\"").append(rs.getString("texto")).append("\",")
                        .append("\"fecha_hora\":\"").append(rs.getString("fecha_hora")).append("\"")
                        .append("}");
            }
            // cerramos
            json.append("]}");

            // se lo mandamos al usuario
            out.print(json.toString());

            // liberamos recursos
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            // error
            System.out.println("ERROR: " + e.getMessage());
            out.print("{\"success\":false}"); // json error
        }
    }
}

// TODO: Quizás cambiar a try-with-resources para liberar memoria
// Terminado