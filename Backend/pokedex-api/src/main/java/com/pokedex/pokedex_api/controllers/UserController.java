package com.pokedex.pokedex_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedex_api.ApiResponse;
import com.pokedex.pokedex_api.entities.UserEntity;
import com.pokedex.pokedex_api.repository.UserRepository;
import com.pokedex.pokedex_api.service.UserService;

import jakarta.annotation.PostConstruct;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private UserService userService;

    @PostConstruct
    public void init() {
        userService = new UserService(userRepository);
    }

    @PostMapping("/user/create")
    public ApiResponse<UserEntity> createUser(@RequestParam String username,
            @RequestParam(defaultValue = "") String password) {
        return userService.createUser(username, password);
    }

    @GetMapping("/user/all")
    public Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{username}")
    public ApiResponse<Iterable<UserEntity>> findUserByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/user/login")
    public ApiResponse<String> login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    @DeleteMapping("/user/delete/{id}")
    public ApiResponse<UserEntity> deleteById(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
