package com.pokedex.pokedex_api.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.pokedex.pokedex_api.ApiResponse;
import com.pokedex.pokedex_api.entities.QuizEntity;
import com.pokedex.pokedex_api.repository.QuizRepository;

@Service
public class QuizService {

    final private QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public ApiResponse<Iterable<QuizEntity>> findAll() {
        Iterable<QuizEntity> quizAll = quizRepository.findAll();
        return new ApiResponse<>(quizAll, "200");
    }

    public ApiResponse<Iterable<QuizEntity>> findByQuestionText(String questionText) {
        Iterable<QuizEntity> quiz = quizRepository.findByQuestionText(questionText);
        return new ApiResponse<>(quiz, "200");
    }

    public ApiResponse<QuizEntity> createQuestion(String questionText, HashMap<String, String> answerValueDict) {
        Iterable<QuizEntity> quizTest = quizRepository.findByQuestionText(questionText);
        if (quizTest.iterator().hasNext()) {
            return new ApiResponse<>(null, "Pergunta já existe");
        }

        quizRepository.save(new QuizEntity(questionText, answerValueDict));
        return new ApiResponse<>(new QuizEntity(questionText, answerValueDict), "Pergunta adicionada com sucesso");
    }

    public ApiResponse<QuizEntity> deleteQuestion(Integer id) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
            return new ApiResponse<>(null, "Pergunta Deletada");
        } else {
            return new ApiResponse<>(null, "Usuario não encontrado");
        }
    }
}
