package com.pokedex.pokedex_api;

import java.util.ArrayList;

import com.pokedex.pokedex_api.entities.PokemonEntity;

public class PokedexController {
    private ArrayList<PokemonEntity> pokedex = new ArrayList<>();

    public PokedexController() {
        // Essa parte pegamos os dados do pokeApi, por enquanto só vou fazer com 2
        // exemplos
        PokemonEntity pokemon_1 = new PokemonEntity("Bulbasaur", 1);
        PokemonEntity pokemon_2 = new PokemonEntity("Ivysaur", 2);
        pokedex.add(pokemon_1);
        pokedex.add(pokemon_2);
    }

    public ArrayList<PokemonEntity> getAll() {
        return pokedex;
    }

    public PokemonEntity getPokemonById(Integer id) {

        for (PokemonEntity p : pokedex) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    int vasco = 6;
}
