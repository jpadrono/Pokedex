package com.pokedex.pokedex_api.controllers;

import java.util.ArrayList;

import org.springframework.web.client.RestTemplate;

import com.pokedex.pokedex_api.entities.PokemonEntity;
import com.pokedex.pokedex_api.service.PokeApiService;

public class PokedexController {
    private ArrayList<PokemonEntity> pokedex = new ArrayList<>();

    public PokedexController() {
        // Essa parte pegamos os dados do pokeApi, por enquanto só vou fazer com 2
        // exemplos
        RestTemplate restTemplate=new RestTemplate();
        PokeApiService pokeApiService= new  PokeApiService(restTemplate);
        PokemonEntity pokemon_1 = new PokemonEntity("Bulbasaur", 1,2,3);
        PokemonEntity pokemon_2 = new PokemonEntity("Ivysaur", 2, 4,5);
        PokemonEntity pokemon_3= pokeApiService.getPokemon(1);
        pokedex.add(pokemon_1);
        pokedex.add(pokemon_2);
        pokedex.add(pokemon_3);
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

    public PokemonEntity getPokemonByName(String name) {

        for (PokemonEntity p : pokedex) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}
