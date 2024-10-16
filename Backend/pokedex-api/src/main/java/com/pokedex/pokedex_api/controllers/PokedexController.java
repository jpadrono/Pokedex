package com.pokedex.pokedex_api.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedex_api.entities.PokemonEntity;
import com.pokedex.pokedex_api.repository.PokemonRepository;
import com.pokedex.pokedex_api.service.PokemonService;

import jakarta.annotation.PostConstruct;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PokedexController {

    @Autowired
    private PokemonRepository pokemonRepository;
    private PokemonService pokemonService;
    
    @PostConstruct
    public void init() {
        pokemonService = new PokemonService(pokemonRepository);
        if (pokemonRepository.count() == 0){
            for(int i=1; i<152;i++){
                pokemonService.createPokemon(i);
            }
        }
    }

    @GetMapping("/pokedex") // http://localhost:8080/pokedex
    public Iterable<PokemonEntity> getAllUsers() {
        return pokemonRepository.findAll();
    }

    @GetMapping("/pokedex/id/{id}") // http://localhost:8080/pokedex/2
    public PokemonEntity findPokemonById(@PathVariable("id") Integer id) {
        return pokemonRepository.findById(id).orElse(null);
    }

    @GetMapping("/pokedex/name/{name}") // http://localhost:8080/pokedex/ivysaur
    public Iterable<PokemonEntity> findPokemonByName(@PathVariable("name") String name) {
        return pokemonRepository.findByName(name);
    }
}