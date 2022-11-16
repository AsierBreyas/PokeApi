package com.example.pokeapi.clases;

public class PokemonCard {
    public String nombre;
    public String tipo1;
    public String tipo2;
    public String imageUrl;
    public int numeroPokedex;

    public PokemonCard(String nombre, String tipo1, String imageUrl, int numeroPokedex) {
        this.nombre = nombre;
        this.tipo1 = tipo1;
        this.imageUrl = imageUrl;
        this.numeroPokedex = numeroPokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getNumeroPokedex() {
        return numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }
}
