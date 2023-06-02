package com.example.testgame.config;

import com.example.testgame.model.Bet;
import com.example.testgame.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
@Service
public class WebSocketGameHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final GameService gameService;

    public WebSocketGameHandler(ObjectMapper objectMapper, GameService gameService) {
        this.objectMapper = objectMapper;
        this.gameService = gameService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Bet bet = objectMapper.readValue(message.getPayload(), Bet.class);
        double win = gameService.playGame(bet);
        session.sendMessage(new TextMessage(String.valueOf(win)));
    }
}