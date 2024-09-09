package com.pokedex.pokedex_api.controllers;

import java.util.ArrayList;

import org.springframework.web.client.RestTemplate;

import com.pokedex.pokedex_api.entities.PokemonEntity;
import com.pokedex.pokedex_api.service.PokeApiService;

public class PokedexController {

    private ArrayList<PokemonEntity> pokedex = new ArrayList<>();
    RestTemplate restTemplate=new RestTemplate();
    PokeApiService pokeApiService= new  PokeApiService(restTemplate);

    public PokedexController() {
        //Pego os Pokemons da pokeAPI e coloco no pokedex
        for(int i=1; i<152; i++){
            PokemonEntity pokemon=pokeApiService.getPokemon(i);
            //pokemonRepository.save(pokemon);
            pokedex.add(pokemon);
        }
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
