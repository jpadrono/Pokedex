package com.pokedex.pokedex_api.entities;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PokemonEntity {
private String name;

    @Id
    Integer id;

    private Integer height;
    private Integer weight;
    private String img;
    private ArrayList<String> types;
    private ArrayList<String> evolves_to;
    private ArrayList<String> abilities;
    private String stats;


    public PokemonEntity(String name, Integer id, Integer height, Integer weight, String img) {
        this.name = name;
        this.id = id;
        this.img = img;
        this.height = height;
        this.weight=weight;
        types = new ArrayList<>();
        evolves_to = new ArrayList<>();
        abilities= new ArrayList<>();
    }
    public PokemonEntity() {
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(String type) {
        types.add(type);
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(String ability) {
        abilities.add(ability);
    }

    public ArrayList<String> getEvolves_to() {
        return evolves_to;
    }

    public void setEvolves_to(String pokemon) {
        evolves_to.add(pokemon);
    }

    public void setStats(Integer hp, Integer attack, Integer defense, Integer special_attack, Integer special_defense, Integer speed) {
        this.stats = "hp: " + hp + ", attack: " + attack + ", defense: " + defense +
                     ", special_attack: " + special_attack + ", special_defense: " + special_defense +
                     ", speed: " + speed;
    }

    public String getStats() {
        return stats;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }
    
    public void setHeight(Integer height) {
        this.height = height;
    }
}
