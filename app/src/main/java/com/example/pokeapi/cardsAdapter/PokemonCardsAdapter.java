package com.example.pokeapi.cardsAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokeapi.R;
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
        View view = mInflater.inflate(R.layout.pokemon_detail_card, null);
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
        TextView nombrePokemon, tipo1, tipo2;
        ImageView image;
        ViewHolder(View view){
            super(view);
            nombrePokemon = view.findViewById(R.id.pokemonCardNombre);
            tipo1 = view.findViewById(R.id.cardTipo1);
            tipo2 = view.findViewById(R.id.cardTipo2);
            image = view.findViewById(R.id.imgCard);
        }
        void bindData(final PokemonCard details) throws JSONException {
            nombrePokemon.setText(details.getNombre() + " - " + details.getNumeroPokedex());
            Picasso.get().load(details.getImageUrl()).into(image);
            tipo1.setText(details.getTipo1());
            if(details.tipo2Existe())
                tipo2.setText(details.getTipo2());
            else
                tipo2.setVisibility(View.INVISIBLE);
        }
    }
}
