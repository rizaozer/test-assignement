package com.example.testgame.controller;

import com.example.testgame.model.Bet;
import com.example.testgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3003")
@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping(value = "/play", consumes = {"application/json"})
    public double play (@RequestBody Bet bet) {
        return gameService.playGame(bet);
    }
}
