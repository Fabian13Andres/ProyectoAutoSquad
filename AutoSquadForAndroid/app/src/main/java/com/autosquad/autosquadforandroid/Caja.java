package com.autosquad.autosquadforandroid;

public class Caja {

    String juego;
    String creador;
    String requisitos;
    int jugadores;

    public Caja(String juego, String creador, String requisitos, int jugadores) {
        this.juego = juego;
        this.creador = creador;
        this.requisitos = requisitos;
        this.jugadores = jugadores;
    }

    public String getJuego() {
        return juego;
    }

    public String getCreador() {
        return creador;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public int getJugadores() {
        return jugadores;
    }
}
