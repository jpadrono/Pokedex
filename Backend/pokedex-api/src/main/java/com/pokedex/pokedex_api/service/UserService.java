package com.pokedex.pokedex_api.service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.UUID;
import java.util.Optional;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pokedex.pokedex_api.ApiResponse;
import com.pokedex.pokedex_api.entities.UserEntity;
import com.pokedex.pokedex_api.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
        if (userTest.iterator().hasNext()) {
            return new ApiResponse<>(null, "Usuário já existe");
        }

        String hashedPassword = passwordEncoder.encode(password);
        userRepository.save(new UserEntity(username, hashedPassword));

        return new ApiResponse<>(new UserEntity(username, hashedPassword), "Usuário criado com sucesso");
    }

    public void generateToken(UserEntity user) {
        user.setAuthToken(UUID.randomUUID().toString());
        user.setAuthTokenExpiration(LocalDateTime.now().plusMinutes(30)); // Define expiração de 30 minutos
    }

    public ApiResponse<UserEntity> login(String username, String password) {
        Iterable<UserEntity> userTest = userRepository.findByUsername(username);
        Iterator<UserEntity> iterator = userTest.iterator();

        if (iterator.hasNext()) {
            UserEntity user = iterator.next();

            if (!passwordEncoder.matches(password, user.getPassword())) {
                return new ApiResponse<>(null, "Senha incorreta");
            }

            generateToken(user);
            userRepository.save(user);
            return new ApiResponse<>(user, "Login realizado com sucesso. Token: " + user.getAuthToken());
        } else {
            return new ApiResponse<>(null, "Usuário não encontrado");
        }
    }

    // Novo método para validar token
    public UserEntity findByToken(String token) {
        Optional<UserEntity> user = userRepository.findByAuthToken(token);
        return user.orElse(null);  // Retorna null se não encontrar
    }

    public ApiResponse<UserEntity> deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ApiResponse<>(null, "Usuário deletado");
        } else {
            return new ApiResponse<>(null, "Usuário não encontrado");
        }
    }

    public ApiResponse<UserEntity> criarUserWithBody(UserEntity usuario) {
        return createUser(usuario.getUsername(), usuario.getPassword());
    }
}
