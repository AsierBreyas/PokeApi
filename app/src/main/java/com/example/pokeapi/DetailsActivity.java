package com.example.pokeapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokeapi.cardsAdapter.PokemonDetailsAdapter;
import com.example.pokeapi.clases.PokemonDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {
    public String url;
    PokemonDetails pokemonDetails;
    String pokemonDetialsUrl;
    TextView nombre;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle b = getIntent().getExtras();
        url = b.getString("url");
        llamarPokemonDetails();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity( new Intent(DetailsActivity.this, MainActivity.class));
            case R.id.search:
                startActivity( new Intent(DetailsActivity.this, SearchActivity.class));
            case R.id.revert:
                finish();
        }
        return (super.onOptionsItemSelected(item));
    }
    public void llamarPokemonDetails(){
        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String name = jsonObject.getString("name");
                    setNombre(name);
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
                    JSONArray descripciones = detalles.getJSONArray("flavor_text_entries");
                    boolean inglesEncontrado = false;
                    for (int i = 0; i < descripciones.length(); i++){
                        if(descripciones.getJSONObject(i).getJSONObject("language").getString("name").equals("en")){
                            pokemonDetails.setDescpricion(descripciones.getJSONObject(i).getString("flavor_text"));
                            inglesEncontrado = true;
                        }
                    }
                    if(!inglesEncontrado)
                        pokemonDetails.setDescpricion(descripciones.getJSONObject(0).getString("flavor_text"));
                    pokemonDetails.setNumPokedex(detalles.getJSONArray("pokedex_numbers").getJSONObject(0).getInt("entry_number"));
                    PokemonDetailsAdapter pokemonDetailsAdapter = new PokemonDetailsAdapter(pokemonDetails, getApplicationContext());
                    RecyclerView recyclerView = findViewById(R.id.PokemonDetail);
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
    public void setNombre(String nombrePokemon){
        nombre = findViewById(R.id.PokemonNameDetail);
        nombre.setText(nombre.getText() + " - " + nombrePokemon );
    }
}
