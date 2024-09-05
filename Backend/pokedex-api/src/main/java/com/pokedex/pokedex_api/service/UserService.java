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
        if (!usersAll.iterator().hasNext()) {
            ApiResponse<Iterable<UserEntity>> response = new ApiResponse<>(usersAll, "404");
            return response;
        } else {
            ApiResponse<Iterable<UserEntity>> response = new ApiResponse<>(usersAll, "200");
            return response;
        }
    }

    public ApiResponse<UserEntity> findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            ApiResponse<UserEntity> response = new ApiResponse<>(user, "404");
            return response;
        } else {
            ApiResponse<UserEntity> response = new ApiResponse<>(user, "200");
            return response;
        }
    }

    public void createUser(String username, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }
}
