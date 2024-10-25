package com.pokedex.pokedex_api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedex_api.entities.QuizEntity;
import com.pokedex.pokedex_api.repository.QuizRepository;





@RestController

public class QuizController {
    
    private final QuizRepository quizRepository;

    @Autowired
    public QuizController(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    // Retorna uma pergunta aleatória do quiz
    @GetMapping("/quiz/pokemon")
    public ResponseEntity<QuizEntity> getRandomQuiz() {
        long count = quizRepository.count(); // Número total de quizzes

        // Verifica se há perguntas no banco de dados
        if (count == 0) {
            return ResponseEntity.noContent().build(); // Nenhum conteúdo disponível
        }

        // Seleciona uma pergunta aleatória
        int randomId = (int) (Math.random() * count);
        Optional<QuizEntity> quizOptional = quizRepository.findById(randomId);

        // Se o quiz for encontrado, retorna a pergunta
        if (quizOptional.isPresent()) {
            return ResponseEntity.ok(quizOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Retorna uma pergunta de quiz por ID específico
    @GetMapping("/quiz/pokemon/{id}")
    public ResponseEntity<QuizEntity> getQuizById(@PathVariable int id) {
        Optional<QuizEntity> quizOptional = quizRepository.findById(id);

        // Se o quiz for encontrado, retorna a pergunta
        if (quizOptional.isPresent()) {
            return ResponseEntity.ok(quizOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Retorna todas as perguntas do quiz
    @GetMapping("/quiz/pokemons")
    public ResponseEntity<List<QuizEntity>> getAllQuizzes() {
        List<QuizEntity> quizzes = quizRepository.findAll();

        if (quizzes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(quizzes);
    }
}
