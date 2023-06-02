package com.example.testgame.service;
import com.example.testgame.model.Bet;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class GameService {
    private final Random random = new Random();

    public double playGame(Bet player) {
        int randomNumber = random.nextInt(100) + 1;
        int playerNumber = player.getPlayerNumber();
        if (playerNumber > randomNumber) {
            return calculateWin(player.getBet(), playerNumber);
        } else {
            return 0d;
        }
    }

    private double calculateWin(double bet, int number) {
        if (number == 100) {
            return 0d;
        }
        return bet * (99d / (100 - number));
    }
}
