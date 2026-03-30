package com.autosquad.autosquadforandroid;

// Clase para almacenar los datos del usuario actualmente logueado
public class UsuarioDB {

    // Variables estáticas para mantener la sesión del usuario mientras la app está abierta
    private static int idActual;
    private static String nombreActual;
    private static String correoActual;
    private static String misJuegosActual;

    // Método para establecer los datos principales del usuario al iniciar sesión
    public static void setUsuarioActual(int id, String nombre, String correo) {
        idActual = id;
        nombreActual = nombre;
        correoActual = correo;
    }

    // Getters para obtener la información del usuario actual
    public static int getIdActual() {
        return idActual;
    }

    public static String getNombreActual() {
        return nombreActual;
    }

    public static String getCorreoActual() {
        return correoActual;
    }

    public static String getMisJuegosActual() {
        return misJuegosActual;
    }

    // Setters para actualizar los datos del usuario dentro de la app
    public static void setNombreActual(String nombre) {
        nombreActual = nombre;
    }

    public static void setCorreoActual(String correo) {
        correoActual = correo;
    }

    public static void setMisJuegosActual(String juegos) {
        misJuegosActual = juegos;
    }
}

// hecho