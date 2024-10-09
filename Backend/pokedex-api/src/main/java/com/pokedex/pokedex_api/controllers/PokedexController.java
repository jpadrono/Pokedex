package com.pokedex.pokedex_api.controllers;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokedexController {
/* 
    @Autowired
    private PokemonRepository pokemonRepository;
    private PokemonService pokemonService;
    
    @PostConstruct
    public void init() {
        pokemonService = new PokemonService(pokemonRepository);
        for(int i=1; i<152;i++)
        {
            pokemonService.createPokemon(i);
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
        */
}
