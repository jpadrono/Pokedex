package com.pokedex.pokedex_api.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class QuizEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @ManyToMany
  @JoinTable(
    name = "quiz_pokemon_alternatives",
    joinColumns = @JoinColumn(name = "quiz_id"),
    inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
  private List<PokemonEntity> alternatives;  
    
  private String imgUrl;
  private int correctAnswerId;  

  public QuizEntity(){}

  public QuizEntity(String imgUrl, List<PokemonEntity> alternatives, int correctAnswerId) {
      this.imgUrl = imgUrl;
      this.alternatives = alternatives;
      this.correctAnswerId = correctAnswerId;
  }

  public int getCorrectAnswerId() {
    return this.correctAnswerId;
  }

  public void setCorrectAnswerId(int id) {
    this.correctAnswerId = id;
  }

  public String getimgUrl() {
    return imgUrl;
  }

  public void setId(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public List<PokemonEntity> getAlternatives() {
    return this.alternatives;
  }

  public void setAlternatives(List<PokemonEntity> alternatives) {
    this.alternatives = alternatives;
  }
}