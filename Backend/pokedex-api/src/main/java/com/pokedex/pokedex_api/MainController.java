package com.pokedex.pokedex_api;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedex_api.entities.Quiz;

@RestController
public class MainController {
  @GetMapping("/Quiz")
  public Quiz getMethodName(@RequestParam String question, @RequestParam Map<String, String> answerValueDict) {
    answerValueDict.remove("question");
    Quiz quiz = new Quiz(question, answerValueDict);
    return quiz;
  }
}