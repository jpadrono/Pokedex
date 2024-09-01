package com.pokedex.pokedex_api.entities;

import java.util.ArrayList;
import java.util.Map;

public class Quiz {

  private String questionText;
  private final ArrayList<Answer> answerValueDictParsed = new ArrayList<>();

  public Quiz(String questionText, Map<String, String> _answerValueDictFromURL) {
    this.questionText = questionText;
    for (Map.Entry<String, String> ans : _answerValueDictFromURL.entrySet()) {
      Answer answer = new Answer(ans.getKey(), Boolean.valueOf(ans.getValue()));
      this.answerValueDictParsed.add(answer);
    }
  }

  public String getQuestionText() {
    return this.questionText;
  }

  public void setQuestionText(String _questionText) {
    this.questionText = _questionText;
  }

  public ArrayList<Answer> getAnswers() {
    return this.answerValueDictParsed;
  }

  public void addAnswers(String answerText, Boolean answerValue) {
    Answer answer = new Answer(answerText, answerValue);
    this.answerValueDictParsed.add(answer);
  }

  // Criar um remove answers

  // Criar um clear answers
}
