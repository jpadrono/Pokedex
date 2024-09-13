package com.pokedex.pokedex_api;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pokedex.pokedex_api.entities.PokemonEntity;
import com.pokedex.pokedex_api.entities.QuizEntity;
import com.pokedex.pokedex_api.repository.PokemonRepository;
import com.pokedex.pokedex_api.service.PokemonService;

import jakarta.annotation.PostConstruct;


@RestController
public class MainController {
    @Autowired
    private PokemonRepository pokemonRepository;
    private PokemonService pokemonService;

    @PostConstruct
    public void init() {
        pokemonService = new PokemonService(pokemonRepository);
        }


    @GetMapping("/Quiz") // http://localhost:8080/Quiz
    public QuizEntity getMethodName(@RequestParam String question, @RequestParam Map<String, String> answerValueDict) {
        answerValueDict.remove("question");
        QuizEntity quiz = new QuizEntity(question, answerValueDict);
        return quiz;
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
