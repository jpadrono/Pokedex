package com.pokedex.pokedex_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import  com.pokedex.pokedex_api.entities.PokemonEntity;


@Repository
public interface PokemonRepository extends CrudRepository<PokemonEntity, Integer> {

    Iterable<PokemonEntity> findByName(String name);
}
