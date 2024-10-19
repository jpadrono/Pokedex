package com.pokedex.pokedex_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokedex.pokedex_api.entities.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Integer> {}