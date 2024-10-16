package com.pokedex.pokedex_api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedex_api.ApiResponse;
import com.pokedex.pokedex_api.entities.QuizEntity;
import com.pokedex.pokedex_api.repository.QuizRepository;
import com.pokedex.pokedex_api.service.QuizService;

import jakarta.annotation.PostConstruct;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class QuizController {
    @Autowired
    private QuizRepository quizRepository;
    private QuizService quizService;

    @PostConstruct
    public void init() {
        quizService = new QuizService(quizRepository);
    }

    @PostMapping("/quiz/create")
    public ApiResponse<QuizEntity> createQuestion(@RequestParam String questionText, @RequestParam String answer) {
        return quizService.createQuestion(questionText, answer);
    }

    @GetMapping("/quiz/all")
    public Iterable<QuizEntity> getAllQuestions() {
        return quizRepository.findAll();
    }

    @GetMapping("/quiz/{questionText}")
    public ApiResponse<Iterable<QuizEntity>> findQuizByQuestionText(@PathVariable("questionText") String questionText) {
        return quizService.findByQuestionText(questionText);
    }

    @DeleteMapping("/quiz/delete/{id}")
    public ApiResponse<QuizEntity> deleteById(@PathVariable Integer id) {
        return quizService.deleteQuestion(id);
    }

}