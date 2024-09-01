package com.pokedex.pokedex_api.entities;

public class Answer {
    private String answerText;
    private Boolean answerValue;

    public Answer(String answerText, Boolean answerValue) {
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
}