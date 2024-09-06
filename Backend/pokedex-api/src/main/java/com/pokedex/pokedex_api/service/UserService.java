package com.pokedex.pokedex_api.service;

import org.springframework.stereotype.Service;

import com.pokedex.pokedex_api.ApiResponse;
import com.pokedex.pokedex_api.entities.UserEntity;
import com.pokedex.pokedex_api.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse<Iterable<UserEntity>> findAll() {
        Iterable<UserEntity> usersAll = userRepository.findAll();
        ApiResponse<Iterable<UserEntity>> response = new ApiResponse<>(usersAll, "200");
        return response;
    }

    public ApiResponse<Iterable<UserEntity>> findByUsername(String username) {
        Iterable<UserEntity> users = userRepository.findByUsername(username);
        ApiResponse<Iterable<UserEntity>> response = new ApiResponse<>(users, "200");
        return response;
    }

    public ApiResponse<UserEntity> createUser(String username, String password) {
        Iterable<UserEntity> userTest = userRepository.findByUsername(username);
        if (userTest.iterator().hasNext()){
            ApiResponse<UserEntity> response = new ApiResponse<>(null, "Usuario já existe");
            return response;
        }
        userRepository.save(new UserEntity(username,password));
        ApiResponse<UserEntity> response = new ApiResponse<>(new UserEntity(username,password), "Usuario criado com sucesso");
        return response;
    }
}
