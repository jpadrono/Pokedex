package com.pokedex.pokedex_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedex_api.entities.UserEntity;
import com.pokedex.pokedex_api.repository.UserRepository;
import com.pokedex.pokedex_api.service.UserService;


@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    private UserService userService;

    @PostMapping("/user/create")
    public void createUser(@RequestParam String username, @RequestParam String password){
        userService.createUser(username, password);
    }

    @GetMapping("/users/all")
    public Iterable<UserEntity> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/users/{username}")
    public UserEntity findUserByUsername(@PathVariable("username") String username)
    {
        return userService.findByUsername(username);
    }




    
}
