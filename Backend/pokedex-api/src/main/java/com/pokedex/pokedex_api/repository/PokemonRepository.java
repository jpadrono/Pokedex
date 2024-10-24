package com.pokedex.pokedex_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import  com.pokedex.pokedex_api.entities.PokemonEntity;


@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Integer> {

    Iterable<PokemonEntity> findByName(String name);

    @Query(value = "SELECT * FROM pokemon_entity ORDER BY RAND() LIMIT 1", nativeQuery = true)
    PokemonEntity findRandomPokemon();

    @Query(value = "SELECT * FROM pokemons ORDER BY RAND() LIMIT ?1", nativeQuery = true)
    List<PokemonEntity> findRandomPokemons(int limit);
}
