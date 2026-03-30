package com.autosquad.autosquadforandroid;

// Clase que representa un mensaje en el chat
public class Mensaje {

    // ID del mensaje y del usuario que lo envió
    private int idMensaje, idUsuario;

    // Nombre del usuario que envió el mensaje
    private String nombreUsuario;

    // Contenido del mensaje y fecha/hora de envío
    private String texto, fechaHora;

    // Constructor de la clase
    public Mensaje(int idMensaje, int idUsuario, String nombreUsuario, String texto, String fechaHora) {
        this.idMensaje = idMensaje;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.texto = texto;
        this.fechaHora = fechaHora;
    }

    // Getters para acceder a los datos del mensaje

    public int getIdMensaje() { return idMensaje; }

    public int getIdUsuario() { return idUsuario; }

    public String getNombreUsuario() { return nombreUsuario; }

    public String getTexto() { return texto; }

    public String getFechaHora() { return fechaHora; }
}

// hecho