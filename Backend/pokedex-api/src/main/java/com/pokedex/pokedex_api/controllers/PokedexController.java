package com.pokedex.pokedex_api.controllers;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import com.pokedex.pokedex_api.entities.PokemonEntity;
import com.pokedex.pokedex_api.repository.PokemonRepository;
import com.pokedex.pokedex_api.service.PokemonService;

public class PokedexController {

    private ArrayList<PokemonEntity> pokedex = new ArrayList<>();
    private PokemonRepository pokemonRepository;
    private PokemonService pokemonService;
    
    @Autowired
    public PokedexController(PokemonRepository pokemonRepository, PokemonService pokemonService) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonService = pokemonService;

        //Pego os Pokemons da pokeAPI e coloco no banco de dados
        for(int i=1; i<152; i++){
            pokemonService= new PokemonService(pokemonRepository);
            pokemonService.createPokemon(i);
        }
    }
}
