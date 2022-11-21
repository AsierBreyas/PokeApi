package com.example.pokeapi.cardsAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokeapi.DetailsActivity;
import com.example.pokeapi.MainActivity;
import com.example.pokeapi.R;
import com.example.pokeapi.SearchActivity;
import com.example.pokeapi.clases.PokemonCard;
import com.example.pokeapi.clases.PokemonDetails;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.List;

public class PokemonCardsAdapter extends RecyclerView.Adapter<PokemonCardsAdapter.ViewHolder>{
    private List<PokemonCard> data;
    private LayoutInflater mInflater;
    private Context context;

    public PokemonCardsAdapter(List<PokemonCard>  data, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public PokemonCardsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.pokemon_card, null);
        return new PokemonCardsAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PokemonCardsAdapter.ViewHolder holder, int position) {
        try {
            holder.bindData(data.get(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void setItems(List<PokemonCard> items){ data = items; }
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        Button nombrePokemon;
        ViewHolder(View view){
            super(view);
            nombrePokemon = view.findViewById(R.id.pokemonCardNombre);
        }
        void bindData(final PokemonCard details) throws JSONException {
            nombrePokemon.setText(details.getNombre());
            nombrePokemon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "https://pokeapi.co/api/v2/pokemon/" + details.getNombre();
                    Bundle bundle = new Bundle();
                    bundle.putString("url", url);
                    Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
