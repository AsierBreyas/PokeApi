package com.example.pokeapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokeapi.cardsAdapter.PokemonCardsAdapter;
import com.example.pokeapi.clases.PokemonCard;
import com.example.pokeapi.clases.PokemonDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    public String listadoTodosPokesUrl = "https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0";
    public String currentUrl =  listadoTodosPokesUrl;
    public List<PokemonCard> listaPokemonCard = new ArrayList<>();
    public List<String> listaSpinner = new ArrayList<>();
    public Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        llamarListadoPokemon();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void llamarListadoPokemon(){
        StringRequest postResquest = new StringRequest(Request.Method.GET, currentUrl, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objectListadoPokemon = new JSONObject(response);
                    JSONArray arrayListadoPokemon = objectListadoPokemon.getJSONArray("results");
                    listaPokemonCard.clear();
                    for (int i = 0; i < arrayListadoPokemon.length(); i++){
                        String nombre = arrayListadoPokemon.getJSONObject(i).getString("name");
                        listaPokemonCard.add(new PokemonCard(nombre));
                    }
                    llamarAdaptador();
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
    public void llamarAdaptador(){
        PokemonCardsAdapter pokemonCardsAdapter = new PokemonCardsAdapter(listaPokemonCard, this);
        RecyclerView recyclerView = findViewById(R.id.pokemonCardList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(pokemonCardsAdapter);
    }
    public void llamarListadoDeTipos(){
        StringRequest postResquest = new StringRequest(Request.Method.GET, currentUrl, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objectListadoPokemon = new JSONObject(response);
                    JSONArray arrayListadoPokemon = objectListadoPokemon.getJSONArray("pokemon");
                    listaPokemonCard.clear();
                    for (int i = 0; i < arrayListadoPokemon.length(); i++){
                        listaPokemonCard.add( new PokemonCard(arrayListadoPokemon.getJSONObject(i).getJSONObject("pokemon").getString("name")));
                    }
                    llamarAdaptador();
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
    public void llamarListadoDePokedex(){
        StringRequest postResquest = new StringRequest(Request.Method.GET, currentUrl, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objectListadoPokemon = new JSONObject(response);
                    JSONArray arrayListadoPokemon = objectListadoPokemon.getJSONArray("pokemon_entries");
                    listaPokemonCard.clear();
                    for (int i = 0; i < arrayListadoPokemon.length(); i++){
                        listaPokemonCard.add( new PokemonCard(arrayListadoPokemon.getJSONObject(i).getJSONObject("pokemon_species").getString("name")));
                    }
                    llamarAdaptador();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
            case R.id.search:
                startActivity( new Intent(SearchActivity.this, SearchActivity.class));
            case R.id.revert:
                finish();
        }
        return (super.onOptionsItemSelected(item));
    }
    public void cambioFiltro(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.activacionPokedex:
                if (checked) {
                    StringRequest postResquest = new StringRequest(Request.Method.GET, "https://pokeapi.co/api/v2/pokedex", new Response.Listener<String>(){

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject objectListadoPokedex = new JSONObject(response);
                                listaSpinner.clear();
                                spinner  = (Spinner) findViewById(R.id.spinnerFiltros);
                                JSONArray arrayPokedex = objectListadoPokedex.getJSONArray("results");
                                for(int i = 0; i < arrayPokedex.length(); i++){
                                    listaSpinner.add(arrayPokedex.getJSONObject(i).getString("name"));
                                }
                                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_item, listaSpinner);
                                spinner.setAdapter(spinnerAdapter);
                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        currentUrl = "https://pokeapi.co/api/v2/pokedex/" + adapterView.getItemAtPosition(i).toString();
                                        System.out.println(currentUrl);
                                        llamarListadoDePokedex();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
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
                break;
            case R.id.activacionTipos:
                if (checked) {
                    StringRequest postResquest = new StringRequest(Request.Method.GET, "https://pokeapi.co/api/v2/type", new Response.Listener<String>(){

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject objectListadoTipos = new JSONObject(response);
                                listaSpinner.clear();
                                spinner  = (Spinner) findViewById(R.id.spinnerFiltros);
                                JSONArray arrayTipos = objectListadoTipos.getJSONArray("results");
                                for(int i = 0; i < arrayTipos.length(); i++){
                                    listaSpinner.add(arrayTipos.getJSONObject(i).getString("name"));
                                }
                                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_item, listaSpinner);
                                spinner.setAdapter(spinnerAdapter);
                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        currentUrl = "https://pokeapi.co/api/v2/type/" + adapterView.getItemAtPosition(i).toString();
                                        System.out.println(currentUrl);
                                        llamarListadoDeTipos();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
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
                break;
        }
    }
    public void llamarDetails(Bundle bundle){

    }
}
