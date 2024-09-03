package com.pokedex.pokedex_api.service;

import org.springframework.stereotype.Service;

import com.pokedex.pokedex_api.entities.UserEntity;
import com.pokedex.pokedex_api.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public Iterable<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void createUser(String username, String password){
        UserEntity user = new UserEntity(username,password);
        userRepository.save(user);
    }
}
