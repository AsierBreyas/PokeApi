package com.example.pokeapi;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

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
