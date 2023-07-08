package com.example.testgame.service;
import com.example.testgame.model.Bet;
import com.example.testgame.model.Game;
import com.example.testgame.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    private final Random random = new Random();

    public double playGame(Bet player) {
        int randomNumber = random.nextInt(100) + 1;
        int playerNumber = player.getPlayerNumber();
        double win;
        if (playerNumber > randomNumber) {
            win = calculateWin(player.getBet(), player.getPlayerNumber());
        } else {
            win = 0;
        }
        saveGame(player, win);
        return win;
    }

    @Transactional
    void saveGame(Bet bet, double win) {
        Game game = new Game();
        game.setBet(bet.getBet());
        game.setPlayerNumber(bet.getPlayerNumber());
        game.setWin(win);
        game.setTimeStamp(LocalDateTime.now());
        gameRepository.save(game);
    }

    private double calculateWin(double bet, int number) {
        return bet * (99d / (100 - number));
    }
}
