package com.autosquad.autosquadforandroid;

public class Caja {

    // Identificador único de la caja
    private int id;

    // ID del usuario que creó la caja
    private int idCreador;

    // Nombre del creador (se guarda para no tener que consultarlo aparte)
    private String creadorNombre;

    // ID del juego al que pertenece la caja
    private int idJuego;

    // Nombre del juego
    private String juego;

    // Requisitos que deben cumplir los jugadores que quieran unirse
    private String requisitos;

    // Número de jugadores que se están buscando
    private int jugadores;

    // Momento en el que se creó la caja (timestamp en milisegundos)
    private long horaCreacion;

    // Constructor que inicializa todos los atributos de la caja
    public Caja(int id, int idCreador, String creadorNombre, int idJuego, String juego,
                String requisitos, int jugadores, long horaCreacion) {

        this.id = id;
        this.idCreador = idCreador;
        this.creadorNombre = creadorNombre;
        this.idJuego = idJuego;
        this.juego = juego;
        this.requisitos = requisitos;
        this.jugadores = jugadores;
        this.horaCreacion = horaCreacion;
    }

    // Devuelve el ID de la caja
    public int getId() {
        return id;
    }

    // Devuelve el ID del creador
    public int getIdCreador() {
        return idCreador;
    }

    // Devuelve el nombre del creador
    public String getCreadorNombre() {
        return creadorNombre;
    }

    // Devuelve el nombre del juego
    public String getJuego() {
        return juego;
    }

    // Devuelve los requisitos de la caja
    public String getRequisitos() {
        return requisitos;
    }

    // Devuelve el número de jugadores buscados
    public int getJugadores() {
        return jugadores;
    }

    // Devuelve la hora de creación en milisegundos
    public long getHoraCreacion() {
        return horaCreacion;
    }

    // Comprueba si la caja se creó hace menos de una hora
    public boolean esReciente() {
        long ahora = System.currentTimeMillis(); // Hora actual del sistema
        return (ahora - horaCreacion) <= 3600_000; // 1 hora equivale a 3.600.000 ms
    }
}

// hecho