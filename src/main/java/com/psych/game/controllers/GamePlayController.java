package com.psych.game.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/play")
public class GamePlayController {
    @GetMapping("/")
    public String play(Authentication authentication){
        return authentication.name();
    }
}
