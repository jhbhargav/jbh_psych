package com.psych.game;

import com.psych.game.model.Player;
import com.psych.game.model.Question;
import com.psych.game.repositories.PlayerRepository;
import com.psych.game.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dev-test")
public class HelloWorldController {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/populate")
    public String populateDB(){
       // Player MSDhoni = new Player();
        return "populated";
    }
    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/question/{id}")
    public Question getQuestionById(@PathVariable(name = "id") Long id){
        return questionRepository.findById(id).orElseThrow();
    }
}

// localhost:8080/dev-test/