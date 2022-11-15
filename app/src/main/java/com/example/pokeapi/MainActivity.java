package com.example.pokeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokeapi.cardsAdapter.PokemonDetailsAdapter;
import com.example.pokeapi.clases.PokemonDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    PokemonDetails pokemonDetails;
    String pokemonDetialsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokemonDetailsInicio();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                return true;
            case R.id.search:
                return true;
            case R.id.revert:
                return true;
        }
        return(super.onOptionsItemSelected(item));
    }
    public void pokemonDetailsInicio(){
        int pokeRandom = new Random().nextInt(905)+1;
        String apiRandomString = "https://pokeapi.co/api/v2/pokemon/"+ pokeRandom +"/";

        StringRequest postResquest = new StringRequest(Request.Method.GET, apiRandomString, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String name = jsonObject.getString("name");
                    String imgUrlBack = jsonObject.getJSONObject("sprites").getString("back_default");
                    String imgUrlFront = jsonObject.getJSONObject("sprites").getString("front_default");
                    String imgUrlFrontShiny = jsonObject.getJSONObject("sprites").getString("front_shiny");
                    String imgUrlBackShiny = jsonObject.getJSONObject("sprites").getString("back_shiny");
                    JSONArray tipos = jsonObject.getJSONArray("types");
                    String tipo1 = tipos.getJSONObject(0).getJSONObject("type").getString("name");
                    JSONArray habilidades = jsonObject.getJSONArray("abilities");
                    JSONObject hab1Object = habilidades.getJSONObject(0).getJSONObject("ability");
                    int height = jsonObject.getInt("height");
                    int weight = jsonObject.getInt("weight");
                    pokemonDetialsUrl = jsonObject.getJSONObject("species").getString("url");
                    JSONArray stats = jsonObject.getJSONArray("stats");
                    if(tipos.length() > 1){
                        String tipo2 = tipos.getJSONObject(1).getJSONObject("type").getString("name");
                        pokemonDetails = new PokemonDetails(name, imgUrlBack, imgUrlBackShiny, imgUrlFront, imgUrlFrontShiny, tipo1, tipo2, hab1Object, height, weight, stats);
                    }else{
                        pokemonDetails = new PokemonDetails(name, imgUrlBack, imgUrlBackShiny, imgUrlFront, imgUrlFrontShiny, tipo1, hab1Object, height, weight, stats);
                    }
                    if(habilidades.length() > 1){
                        if(habilidades.getJSONObject(1).getInt("slot") == 2)
                            pokemonDetails.setHab2(habilidades.getJSONObject(1).getJSONObject("ability"));
                        else
                            pokemonDetails.setHabOculta(habilidades.getJSONObject(1).getJSONObject("ability"));
                        if(habilidades.length() > 2)
                            pokemonDetails.setHabOculta(habilidades.getJSONObject(2).getJSONObject("ability"));
                    }
                    pokemonLlamarDetails();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(postResquest);

    }
    public void pokemonLlamarDetails(){
        StringRequest postResquest = new StringRequest(Request.Method.GET, pokemonDetialsUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject detalles = new JSONObject(response);
                    pokemonDetails.setDescpricion(detalles.getJSONArray("flavor_text_entries").getJSONObject(0).getString("flavor_text"));
                    pokemonDetails.setNumPokedex(detalles.getJSONArray("pokedex_numbers").getJSONObject(0).getInt("entry_number"));
                    PokemonDetailsAdapter pokemonDetailsAdapter = new PokemonDetailsAdapter(pokemonDetails, getApplicationContext());
                    RecyclerView recyclerView = findViewById(R.id.pokemonDetailsExample);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(pokemonDetailsAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(postResquest);
    }
}