package com.pokedex.pokedex_api.entities;

import java.util.ArrayList;
import java.util.HashMap;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QuizEntity {

  private String questionText;
  private final ArrayList<AnswerEntity> answerValueDictParsed = new ArrayList<>();
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  public QuizEntity(String questionText, HashMap<String, String> answerValueDictFromURL) {
    this.questionText = questionText;
    for (HashMap.Entry<String, String> ans : answerValueDictFromURL.entrySet()) {
      AnswerEntity answer = new AnswerEntity(ans.getKey(), Boolean.valueOf(ans.getValue()));
      this.answerValueDictParsed.add(answer);
    }
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

  public ArrayList<AnswerEntity> getAnswers() {
    return this.answerValueDictParsed;
  }

  public void addAnswers(String answerText, Boolean answerValue) {
    AnswerEntity answer = new AnswerEntity(answerText, answerValue);
    this.answerValueDictParsed.add(answer);
  }

  // Criar um remove answers

  // Criar um clear answers
}
