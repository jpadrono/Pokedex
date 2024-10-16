package com.pokedex.pokedex_api.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QuizEntity {

  private String questionText;
  private String answer;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  public QuizEntity() {
  }

  public QuizEntity(String questionText, String answer) {
    this.questionText = questionText;
    this.answer = answer;
  }

  public String getQuestionText() {
    return this.questionText;
  }

  public void setQuestionText(String _questionText) {
    this.questionText = _questionText;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAnswer() {
    return this.answer;
  }

  public void setAnswer(String _answer) {
    this.answer = _answer;
  }
}