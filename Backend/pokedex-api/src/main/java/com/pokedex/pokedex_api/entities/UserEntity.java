package com.pokedex.pokedex_api.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String password;
    private String authToken;
    private String imgUrl;
    private LocalDateTime authTokenExpiration;

    private String listaFavoritos; // Armazenar IDs dos Pokémon favoritos como uma string

    public String getImgUrl() {
        return imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDateTime getAuthTokenExpiration() {
        return authTokenExpiration;
    }

    public void setAuthTokenExpiration(LocalDateTime authTokenExpiration) {
        this.authTokenExpiration = authTokenExpiration;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.listaFavoritos = ""; // Inicializar como string vazia
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getListaFavoritos() {
        return listaFavoritos; // Getter para a lista de favoritos
    }

    public void setListaFavoritos(String listaFavoritos) {
        this.listaFavoritos = listaFavoritos; // Setter para a lista de favoritos
    }

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", username=" + username + ", password=" + password + ", listaFavoritos=" + listaFavoritos + "]";
    }

    public UserEntity() {
    }
}


