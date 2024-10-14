package com.pokedex.pokedex_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pokedex.pokedex_api.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Iterable<UserEntity> findByUsername(String username);
    UserEntity findUserByUsernameAndPassword(String username, String password);
}
