package com.psych.game.controllers;

import com.psych.game.model.*;
import com.psych.game.repositories.GameRepository;
import com.psych.game.repositories.PlayerRepository;
import com.psych.game.repositories.QuestionRepository;
import com.psych.game.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dev-test")
public class DevTestController {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/populate")
    public String populateDB(){

        for(Player player : playerRepository.findAll()){
            player.getGames().clear();
            playerRepository.save(player);
        }
        gameRepository.deleteAll();
        playerRepository.deleteAll();
        questionRepository.deleteAll();

        Player Dhoni = new Player.Builder()
                .alias("Mahendra Singh Dhoni")
                .email("dhoni@csk.com")
                .saltedHashedPassword("worldcup@2011")
                .build();
         playerRepository.save(Dhoni);

         Player Phelps = new Player.Builder()
                 .alias("Michael Phelps")
                 .email("phelps@olympics.com")
                 .saltedHashedPassword("beijing@2008")
                 .build();
        playerRepository.save(Phelps);
        questionRepository.save(new Question("What is the most important Poneglyph ?",
                "Rio Poneglyph", GameMode.IS_THIS_A_FACT ));

        Game game  = new Game();
        game.getPlayers().add(Dhoni);
        game.setGameMode(GameMode.IS_THIS_A_FACT);
        game.setLeader(Dhoni);
        gameRepository.save(game);

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
    @GetMapping("/players")
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @GetMapping("/player/{id}")
    public Player getPlayerById(@PathVariable(name = "id") Long id) {
        return playerRepository.findById(id).orElseThrow();
    }


    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @GetMapping("/games")
    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    @GetMapping("/game/{id}")
    public Game getGameById(@PathVariable(name = "id") Long id) {
        return gameRepository.findById(id).orElseThrow();
    }



}

// localhost:8080/dev-test/