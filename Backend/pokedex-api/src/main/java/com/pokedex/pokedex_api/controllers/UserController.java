package com.pokedex.pokedex_api.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedex_api.ApiResponse;
import com.pokedex.pokedex_api.ChangePasswordRequest;
import com.pokedex.pokedex_api.entities.UserEntity;
import com.pokedex.pokedex_api.repository.UserRepository;
import com.pokedex.pokedex_api.service.UserService;

import jakarta.annotation.PostConstruct;


@CrossOrigin(origins = "*")
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

    @PostMapping("/user/create1")
    public ApiResponse<UserEntity> cadastrarUsuario(@RequestBody UserEntity usuario) {
        return userService.criarUserWithBody(usuario);
    }
    

    @GetMapping("/user/all")
    public Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{username}")
    public ApiResponse<Iterable<UserEntity>> findUserByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/user/id/{id}")
    public ApiResponse<UserEntity> findById(@PathVariable Integer id) {
        return userService.findById(id);
    }
    

    @PostMapping("/user/login")
    public ApiResponse<UserEntity> login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }
    
    @PostMapping("/user/login1")
    public ApiResponse<UserEntity> postMethodName(@RequestBody UserEntity usuario) {
        return userService.login(usuario.getUsername(),usuario.getPassword());
    }
    

    @DeleteMapping("/user/delete/{id}")
    public ApiResponse<UserEntity> deleteById(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }


    @PutMapping("/user/id/{id}")
    public ApiResponse<UserEntity> updateUser(@PathVariable Integer id, @RequestBody UserEntity user) {
    return userService.updateUser(id, user);
}

    
    @PostMapping("/user/change")
    public ApiResponse<UserEntity> changePassword(@RequestBody ChangePasswordRequest request) {
        return userService.changePassword(request.getId(), request.getNewPassword(), request.getPassword());
    }

    
    @PostMapping("/user/upload-photo")
    public ApiResponse<UserEntity> uploadFotoBase64(@RequestBody Map<String, String> payload) {
        String base64Image = payload.get("fotoBase64");
        Integer userId = Integer.parseInt(payload.get("userId"));

        return userService.uploadPhoto(userId, base64Image);
    }
}