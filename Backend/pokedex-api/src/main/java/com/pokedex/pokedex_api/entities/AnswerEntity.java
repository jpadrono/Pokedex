package com.pokedex.pokedex_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AnswerEntity {

    private String answerText;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    public AnswerEntity() {
    }

    public AnswerEntity(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswerText() {
        return this.answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
