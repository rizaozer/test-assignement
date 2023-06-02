package com.example.testgame.controller;

import com.example.testgame.model.Bet;
import com.example.testgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
@Controller
public class GameController {
    @Autowired
    private GameService gameService;
    @MessageMapping("/game")
    @SendTo("/games/play")
    public double playGame(@RequestBody @Valid Bet bet) throws Exception {
        Thread.sleep(1000);
        return gameService.playGame(bet);
    }
}
