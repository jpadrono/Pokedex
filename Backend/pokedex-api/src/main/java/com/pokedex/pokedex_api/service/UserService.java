package com.pokedex.pokedex_api.service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pokedex.pokedex_api.ApiResponse;
import com.pokedex.pokedex_api.entities.UserEntity;
import com.pokedex.pokedex_api.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // funções para validação do token

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
            return new ApiResponse<>(null, "Usuario já existe");
        }

        // Criptografar a senha antes de armazenar
        String hashedPassword = passwordEncoder.encode(password);
        userRepository.save(new UserEntity(username, hashedPassword));

        return new ApiResponse<>(new UserEntity(username, hashedPassword), "Usuario criado com sucesso");
    }

    // geração de token de autenticação
    public void gererateToken(UserEntity user) {
        user.setAuthToken(UUID.randomUUID().toString());
        user.setAuthTokenExpiration(LocalDateTime.now().plusMinutes(30));
    }

    // Função de login
    public ApiResponse<UserEntity> login(String username, String password) {
        Iterable<UserEntity> userTest = userRepository.findByUsername(username);
        Iterator<UserEntity> iterator = userTest.iterator();

        if (iterator.hasNext()) {

            UserEntity user = iterator.next();

            // Verificar se a senha corresponde ao hash armazenado
            if (!passwordEncoder.matches(password, user.getPassword())) {
                return new ApiResponse<>(null, "Senha incorreta");
            }

            // Gerar token de autenticação
            gererateToken(user);
            userRepository.save(user);
            return new ApiResponse<>(user, "Login realizado com sucesso");

        } else
            return new ApiResponse<>(null, "Usuário não encontrado");

    }

    public ApiResponse<UserEntity> deleteUser(Integer id)
    {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return new ApiResponse<>(null, "Usuario deletado");
        }
        else{
            return new ApiResponse<>(null, "Usuario não encontrado");
        }
    }

    public ApiResponse<UserEntity> criarUserWithBody(UserEntity usuario){
        createUser(usuario.getUsername(), usuario.getPassword());
        return new ApiResponse<>(null,"Usuario criado");
    }
}
