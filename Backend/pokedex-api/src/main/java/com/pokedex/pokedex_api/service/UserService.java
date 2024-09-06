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
        return new ApiResponse<>(usersAll, "200");
    }

    public ApiResponse<Iterable<UserEntity>> findByUsername(String username) {
        Iterable<UserEntity> users = userRepository.findByUsername(username);
        return new ApiResponse<>(users, "200");
    }

    public ApiResponse<UserEntity> createUser(String username, String password) {
        Iterable<UserEntity> userTest = userRepository.findByUsername(username);
        if (userTest.iterator().hasNext()){
            return new ApiResponse<>(null, "Usuario já existe");
        }
        userRepository.save(new UserEntity(username,password));
        return  new ApiResponse<>(new UserEntity(username,password), "Usuario criado com sucesso");
    }
}
