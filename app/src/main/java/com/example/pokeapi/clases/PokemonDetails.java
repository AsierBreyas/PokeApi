package com.example.pokeapi.clases;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokeapi.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PokemonDetails {
    private String nombre;
    private String imgBackUrl;
    private String imgBackUrlShiny;
    private String imgFrontUrl;
    private String imgFrontUrlShiny;
    private String tipo1;
    private String tipo2;
    private String descpricion;
    private JSONObject hab1;
    private JSONObject hab2;
    private JSONObject habOculta;
    private int numPokedex;
    private int height;
    private int weight;
    private JSONArray stats;

    public PokemonDetails(String nombre, String imgBackUrl, String imgBackUrlShiny, String imgFrontUrl, String imgFrontUrlShiny, String tipo1, JSONObject hab1, int height, int weight, JSONArray stats) {
        this.nombre = nombre;
        this.imgBackUrl = imgBackUrl;
        this.imgBackUrlShiny = imgBackUrlShiny;
        this.imgFrontUrl = imgFrontUrl;
        this.imgFrontUrlShiny = imgFrontUrlShiny;
        this.tipo1 = tipo1;
        this.hab1 = hab1;
        this.height = height;
        this.weight = weight;
        this.stats = stats;
    }

    public PokemonDetails(String nombre, String imgBackUrl, String imgBackUrlShiny, String imgFrontUrl, String imgFrontUrlShiny, String tipo1, String tipo2, JSONObject hab1, int height, int weight, JSONArray stats) {
        this.nombre = nombre;
        this.imgBackUrl = imgBackUrl;
        this.imgBackUrlShiny = imgBackUrlShiny;
        this.imgFrontUrl = imgFrontUrl;
        this.imgFrontUrlShiny = imgFrontUrlShiny;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.hab1 = hab1;
        this.height = height;
        this.weight = weight;
        this.stats = stats;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImgBackUrl() {
        return imgBackUrl;
    }

    public void setImgBackUrl(String imgBackUrl) {
        this.imgBackUrl = imgBackUrl;
    }

    public String getImgBackUrlShiny() {
        return imgBackUrlShiny;
    }

    public void setImgBackUrlShiny(String imgBackUrlShiny) {
        this.imgBackUrlShiny = imgBackUrlShiny;
    }

    public String getImgFrontUrl() {
        return imgFrontUrl;
    }

    public void setImgFrontUrl(String imgFrontUrl) {
        this.imgFrontUrl = imgFrontUrl;
    }

    public String getImgFrontUrlShiny() {
        return imgFrontUrlShiny;
    }

    public void setImgFrontUrlShiny(String imgFrontUrlShiny) {
        this.imgFrontUrlShiny = imgFrontUrlShiny;
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

    public String getHab1() {
        String hab = "";
        try {
            hab = hab1.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hab;
    }

    public void setHab1(JSONObject hab1) {
        this.hab1 = hab1;
    }

    public String getHab2() {
        String hab = "";
        try {
            hab = hab2.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hab;
    }

    public void setHab2(JSONObject hab2) {
        this.hab2 = hab2;
    }

    public String getHabOculta() {
        String hab = "";
        try {
            hab = habOculta.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hab;
    }

    public void setHabOculta(JSONObject habOculta) {
        this.habOculta = habOculta;
    }

    public String getDescpricion() {
        return descpricion;
    }

    public void setDescpricion(String descpricion) {
        this.descpricion = descpricion;
    }

    public int getNumPokedex() {
        return numPokedex;
    }

    public void setNumPokedex(int numPokedex) {
        this.numPokedex = numPokedex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public JSONArray getStats() {
        return stats;
    }

    public void setStats(JSONArray stats) {
        this.stats = stats;
    }

    public boolean tipo2Existe() {
        if (tipo2 != null)
            return true;
        else
            return false;
    }

    public boolean hab2Existe() {
        if (hab2 != null)
            return true;
        else
            return false;
    }

    public boolean habOcultaExiste() {
        if (habOculta != null)
            return true;
        else
            return false;
    }
}
