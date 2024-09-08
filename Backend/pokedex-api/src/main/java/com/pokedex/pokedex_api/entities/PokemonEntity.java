package com.pokedex.pokedex_api.entities;

import java.util.ArrayList;
import java.util.HashMap;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PokemonEntity {

    private String name;
    @Id
    Integer id;

    Integer height;
    Integer weight;
    ArrayList<String> types;
    ArrayList<String> evolves_to;
    ArrayList<String> abilities;
    HashMap<String, Integer> stats;

    public PokemonEntity(String name, Integer id, Integer height, Integer weight) {
        this.name = name;
        this.id = id;
        this.height = height;
        this.weight=weight;
        types = new ArrayList<>();
        evolves_to = new ArrayList<>();
        abilities= new ArrayList<>();
        stats = new HashMap<>();
    }

    public String getName() {
        return name;
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

    public HashMap<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Integer hp, Integer attack, Integer defense, Integer special_attack, Integer special_defense, Integer speed) {
        stats.put("hp", hp);
        stats.put("attack", attack);
        stats.put("defense", defense);
        stats.put("special_attack", special_attack);
        stats.put("special_defense", special_defense);
        stats.put("speed", speed);
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
