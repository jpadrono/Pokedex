package com.pokedex.pokedex_api;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedex_api.entities.PokemonEntity;
import com.pokedex.pokedex_api.entities.QuizEntity;

@RestController
public class MainController {
    @GetMapping("/Quiz") // http://localhost:8080/Quiz
    public QuizEntity getMethodName(@RequestParam String question, @RequestParam Map<String, String> answerValueDict) {
      answerValueDict.remove("question");
      QuizEntity quiz = new QuizEntity(question, answerValueDict);
      return quiz;
    }

    @GetMapping("/pokedex") // http://localhost:8080/pokedex
    public ApiResponse<ArrayList<PokemonEntity>> pokemonsAll() {
      PokedexController controller = new PokedexController();
      String message = "List of all Pokemons";
      return new ApiResponse<>(controller.getAll(), message);
    }

    @GetMapping("/pokedex/{id}") // http://localhost:8080/pokedex/2
    public ApiResponse<PokemonEntity> usersById(@PathVariable("id") Integer id) {
      PokedexController controller = new PokedexController();
      PokemonEntity pokemon = controller.getPokemonById(id);
      String message = "Pokemon details";
      if (pokemon == null) {
        message = "Erro!";
      }
      return new ApiResponse<>(pokemon, message);
      }

      @GetMapping("/pokedex/{name}") // http://localhost:8080/pokedex/2
      public ApiResponse<PokemonEntity> usersByName(@PathVariable("name") Integer name) {
        PokedexController controller = new PokedexController();
        PokemonEntity pokemon = controller.getPokemonById(name);
        String message = "Pokemon details";
        if (pokemon == null) {
          message = "Erro!";
        }
        return new ApiResponse<>(pokemon, message);
        }
}