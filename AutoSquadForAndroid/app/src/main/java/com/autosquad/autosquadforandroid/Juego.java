package com.autosquad.autosquadforandroid;

// Clase que representa un juego para mostrar en el Spinner
public class Juego {

    private int id;       // ID interno del juego
    private String nombre; // Nombre que se mostrará en el Spinner

    // Constructor
    public Juego(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getter para obtener el ID del juego
    public int getId() {
        return id;
    }

    // Devuelve el nombre del juego, usado automáticamente por el Spinner
    @Override
    public String toString() {
        return nombre; // Esto es lo que se mostrará en el Spinner
    }
}

// hecho