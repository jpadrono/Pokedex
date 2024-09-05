package com.pokedex.pokedex_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedex_api.ApiResponse;
import com.pokedex.pokedex_api.entities.UserEntity;
import com.pokedex.pokedex_api.repository.UserRepository;
import com.pokedex.pokedex_api.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private UserService userService;

    @PostMapping("/user/create")
    public ApiResponse<UserEntity> createUser(@RequestParam String username, @RequestParam(defaultValue = "") String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        ApiResponse<UserEntity> response = new ApiResponse<>(user, "Usuario Criado");
        return response;
    }

    @GetMapping("/user/all")
    public ApiResponse<Iterable<UserEntity>> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/user/{username}")
    public ApiResponse<UserEntity> findUserByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

}
