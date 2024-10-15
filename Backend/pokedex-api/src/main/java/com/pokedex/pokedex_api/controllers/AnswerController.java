package com.pokedex.pokedex_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedex_api.ApiResponse;
import com.pokedex.pokedex_api.entities.AnswerEntity;
import com.pokedex.pokedex_api.repository.AnswerRepository;
import com.pokedex.pokedex_api.service.AnswerService;

import jakarta.annotation.PostConstruct;

@RestController
public class AnswerController {
    
    @Autowired
    private AnswerRepository answerRepository;
    
    private AnswerService answerService;

    @PostConstruct
    public void init() {
        answerService = new AnswerService(answerRepository);
    }

    @PostMapping("/answer/create")
    public ApiResponse<AnswerEntity> createAnswer(@RequestParam String answerText) {
        return answerService.createAnswer(answerText);
    }

    @GetMapping("/answer/all")
    public Iterable<AnswerEntity> getAllAnswers() {
        return answerRepository.findAll();
    }

    @GetMapping("/answer/{answerText}")
    public ApiResponse<Iterable<AnswerEntity>> findAnswerByText(@PathVariable("answerText") String answerText) {
        return answerService.findByAnswerText(answerText);
    }

    @DeleteMapping("/answer/delete/{id}")
    public ApiResponse<AnswerEntity> deleteById(@PathVariable Integer id) {
        return answerService.deleteAnswer(id);
    }
}
