package com.example.pokeapi;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokeapi.clases.PokemonCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity {
    public String listadoTodosPokesUrl = "https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0";
    public String currentUrl =  listadoTodosPokesUrl;
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
                    for (int i = 0; i < arrayListadoPokemon.length(); i++){
                        llamarPokemon(arrayListadoPokemon.getJSONObject(i).getString("url"));
                    }
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
    public void llamarPokemon(String url){
        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject pokemon = new JSONObject(response);
                    PokemonCard pokemonCard = new PokemonCard(pokemon.getString("name"), pokemon.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name"),pokemon.getJSONObject("sprites").getJSONObject("other").getJSONObject("official-artwork").getString("front-default"),pokemon.getInt("id"));
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
                return true;
            case R.id.search:
                return true;
            case R.id.revert:
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }
    public void cambioFiltro(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.activacionPokedex:
                if (checked) {

                }
                break;
            case R.id.activacionTipos:
                if (checked) {

                }
                break;
        }
    }
}
