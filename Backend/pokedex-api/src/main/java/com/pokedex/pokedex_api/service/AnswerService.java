package com.pokedex.pokedex_api.service;

import org.springframework.stereotype.Service;

import com.pokedex.pokedex_api.ApiResponse;
import com.pokedex.pokedex_api.entities.AnswerEntity;
import com.pokedex.pokedex_api.repository.AnswerRepository;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public ApiResponse<Iterable<AnswerEntity>> findAll() {
        Iterable<AnswerEntity> answerAll = answerRepository.findAll();
        return new ApiResponse<>(answerAll, "200");
    }

    public ApiResponse<Iterable<AnswerEntity>> findByAnswerText(String answerText) {
        Iterable<AnswerEntity> answers = answerRepository.findByAnswerText(answerText);
        return new ApiResponse<>(answers, "200");
    }

    public ApiResponse<AnswerEntity> createAnswer(String answerText) {
        Iterable<AnswerEntity> existingAnswer = answerRepository.findByAnswerText(answerText);
        if (existingAnswer.iterator().hasNext()) {
            return new ApiResponse<>(null, "Resposta já existe");
        }

        AnswerEntity newAnswer = new AnswerEntity(answerText);
        answerRepository.save(newAnswer);
        return new ApiResponse<>(newAnswer, "Resposta adicionada com sucesso");
    }

    public ApiResponse<AnswerEntity> deleteAnswer(Integer id) {
        if (answerRepository.existsById(id)) {
            answerRepository.deleteById(id);
            return new ApiResponse<>(null, "Resposta Deletada");
        } else {
            return new ApiResponse<>(null, "Resposta não encontrado");
        }
    }
}