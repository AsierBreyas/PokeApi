package com.example.pokeapi.clases;

public class PokemonCard {
    public String nombre;

    public PokemonCard(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
