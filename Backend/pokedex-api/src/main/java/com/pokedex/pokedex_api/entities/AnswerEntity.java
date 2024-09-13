package com.pokedex.pokedex_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AnswerEntity {

    private String answerText;
    private Boolean answerValue;
    @Id
    Integer id;

    public AnswerEntity(String answerText, Boolean answerValue) {
        this.answerText = answerText;
        this.answerValue = answerValue;
    }

    public String getAnswerText() {
        return this.answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getValue() {
        return this.answerValue;
    }

    public void setValue(Boolean answerValue) {
        this.answerValue = answerValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
