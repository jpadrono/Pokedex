package com.pokedex.pokedex_api.repository;

import org.springframework.data.repository.CrudRepository;

import com.pokedex.pokedex_api.entities.QuizEntity;

public interface QuizRepository extends CrudRepository<QuizEntity, Integer> {
    Iterable<QuizEntity> findByQuestionText(String questionText);
}