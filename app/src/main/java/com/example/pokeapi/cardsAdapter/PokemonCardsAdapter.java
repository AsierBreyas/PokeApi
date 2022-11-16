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

public class PokemonCardsAdapter extends RecyclerView.Adapter<PokemonDetailsAdapter.ViewHolder>{
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
    public PokemonDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.pokemon_detail_card, null);
        return new PokemonDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonDetailsAdapter.ViewHolder holder, int position) {
        try {
            holder.bindData(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public int getItemCount() {
        return 1;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombrePokemon, tipo1, tipo2, descripcion, hab1, hab2, habOculta, pokedex, altura, peso, ps, atq, def, spA, spD, spe;
        ImageView imageBack, imageFront;
        ViewHolder(View view){
            super(view);
            nombrePokemon = view.findViewById(R.id.pokemonNombre);
            tipo1 = view.findViewById(R.id.tipo1);
            tipo2 = view.findViewById(R.id.tipo2);
            imageBack = view.findViewById(R.id.imgBack);
            imageFront = view.findViewById(R.id.imgFront);
            descripcion = view.findViewById(R.id.descPokemon);
            hab1 = view.findViewById(R.id.hab1);
            hab2 = view.findViewById(R.id.hab2);
            habOculta = view.findViewById(R.id.habOculta);
            pokedex = view.findViewById(R.id.pokedex);
            altura = view.findViewById(R.id.pokemonAltura);
            peso = view.findViewById(R.id.pokemonPeso);
            ps = view.findViewById(R.id.ValuePS);
            atq = view.findViewById(R.id.ValueAtk);
            def = view.findViewById(R.id.ValueDef);
            spA = view.findViewById(R.id.ValueSpA);
            spD = view.findViewById(R.id.ValueSpD);
            spe = view.findViewById(R.id.ValueSpe);
        }
        void bindData(final PokemonDetails details) throws JSONException {
            nombrePokemon.setText(details.getNombre());
            Picasso.get().load(details.getImgBackUrl()).into(imageBack);
            Picasso.get().load(details.getImgFrontUrl()).into(imageFront);
            tipo1.setText(details.getTipo1());
            if(details.tipo2Existe())
                tipo2.setText(details.getTipo2());
            else
                tipo2.setVisibility(View.INVISIBLE);
            descripcion.setText(details.getDescpricion());
            pokedex.setText(pokedex.getText() + Integer.toString(details.getNumPokedex()));
            hab1.setText(hab1.getText() + details.getHab1());
            if (details.hab2Existe()){
                hab2.setText(hab2.getText() + details.getHab2());
            }else{
                hab2.setText("");
            }
            if (details.habOcultaExiste()){
                habOculta.setText(habOculta.getText() + details.getHabOculta());
            }else{
                habOculta.setText("");
            }
            altura.setText(altura.getText() + Integer.toString(details.getHeight()) + " m");
            peso.setText(peso.getText() + Integer.toString(details.getWeight()) + " kg");
            ps.setText(Integer.toString(details.getStats().getJSONObject(0).getInt("base_stat")));
            atq.setText(Integer.toString(details.getStats().getJSONObject(1).getInt("base_stat")));
            def.setText(Integer.toString(details.getStats().getJSONObject(2).getInt("base_stat")));
            spA.setText(Integer.toString(details.getStats().getJSONObject(3).getInt("base_stat")));
            spD.setText(Integer.toString(details.getStats().getJSONObject(4).getInt("base_stat")));
            spe.setText(Integer.toString(details.getStats().getJSONObject(5).getInt("base_stat")));

        }
    }
}
