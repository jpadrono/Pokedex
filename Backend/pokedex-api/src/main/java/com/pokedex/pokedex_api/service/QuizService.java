package com.pokedex.pokedex_api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokedex.pokedex_api.entities.PokemonEntity;
import com.pokedex.pokedex_api.entities.QuizEntity;
import com.pokedex.pokedex_api.repository.PokemonRepository;
import com.pokedex.pokedex_api.repository.QuizRepository;

import jakarta.annotation.PostConstruct;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private PokemonRepository pokemonRepository;

    @PostConstruct
    public void initializeQuizData() {
        // Verifica se já existem registros na tabela do Quiz
        if (quizRepository.count() == 0) {
            // Obtém todos os Pokémons do banco de dados
            List<PokemonEntity> allPokemons = pokemonRepository.findAll();

            // Cria perguntas de quiz para cada Pokémon
            for (PokemonEntity correctPokemon : allPokemons) {
                createQuizQuestion(correctPokemon, allPokemons);
            }
        } else {
            // Tabela já está preenchida, não faz nada
            System.out.println("Tabela de quiz já está preenchida.");
        }
    }

    private void createQuizQuestion(PokemonEntity correctPokemon, List<PokemonEntity> allPokemons) {
        
        System.out.println("Criando pergunta para Pokémon: " + correctPokemon.getName());
        // Seleciona 3 Pokémons aleatórios como alternativas
        List<PokemonEntity> alternativePokemons = new ArrayList<>();

        // Adiciona o Pokémon correto como a resposta
        alternativePokemons.add(correctPokemon);
        
        // Seleciona 2 Pokémons aleatórios distintos para completar as alternativas
        while (alternativePokemons.size() < 4) {
            PokemonEntity newAlternative = pokemonRepository.findRandomPokemon();
            if (!alternativePokemons.contains(newAlternative)) {
                alternativePokemons.add(newAlternative);
            }
        }

        // Embaralha as alternativas
        Collections.shuffle(alternativePokemons);

        // Cria e salva a pergunta
        QuizEntity quiz = new QuizEntity(correctPokemon.getImg(), alternativePokemons, correctPokemon.getId());
        quizRepository.save(quiz);
    }
}
