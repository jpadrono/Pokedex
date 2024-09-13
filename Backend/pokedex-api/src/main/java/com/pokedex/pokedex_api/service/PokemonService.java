package com.pokedex.pokedex_api.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pokedex.pokedex_api.ApiResponse;
import com.pokedex.pokedex_api.entities.PokemonEntity;
import com.pokedex.pokedex_api.entities.UserEntity;
import com.pokedex.pokedex_api.repository.PokemonRepository;

@Service
public class PokemonService {
    private PokemonRepository pokemonRepository;
    RestTemplate restTemplate=new RestTemplate();
    PokeApiService pokeApiService= new  PokeApiService(restTemplate);

    public PokemonService(PokemonRepository pokemonRepository){
        this.pokemonRepository= pokemonRepository;
    }

    public void createPokemon(Integer id) {
        pokemonRepository.save(pokeApiService.getPokemon(id));
    }

}
