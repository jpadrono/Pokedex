package com.pokedex.pokedex_api.repository;

import org.springframework.data.repository.CrudRepository;

import com.pokedex.pokedex_api.entities.AnswerEntity;

public interface AnswerRepository extends CrudRepository<AnswerEntity, Integer> {
    Iterable<AnswerEntity> findByAnswerText(String answerText);
}
