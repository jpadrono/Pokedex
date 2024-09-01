package com.pokedex.pokedex_api.entities;

import java.util.ArrayList;
import java.util.Map;

public class QuizEntity {

  private String questionText;
  private final ArrayList<AnswerEntity> answerValueDictParsed = new ArrayList<>();

  public QuizEntity(String questionText, Map<String, String> _answerValueDictFromURL) {
    this.questionText = questionText;
    for (Map.Entry<String, String> ans : _answerValueDictFromURL.entrySet()) {
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
