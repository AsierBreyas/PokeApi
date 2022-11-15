package com.example.pokeapi;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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
}
