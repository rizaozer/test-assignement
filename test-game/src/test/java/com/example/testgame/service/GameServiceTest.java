package com.example.testgame.service;

import com.example.testgame.model.Bet;
import lombok.SneakyThrows;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    private GameService gameService;


  @RepeatedTest(10)
  public void testPlayGame() {
    Bet player = new Bet();
    player.setBet(40.5);
    player.setPlayerNumber(50);

    double result = gameService.playGame(player);

    assertThat(result).isIn(0d, 80.19);
  }

  @SneakyThrows
  @Test
  public void testMillionBets() {

      var times = 1_000_000;
      var winTotal = new AtomicReference<>(0d);

      var pool = new ThreadPoolExecutor(24, 24, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(times));
      var countdown = new CountDownLatch(times);

      for (int i = 0; i < times; i++) {
          pool.execute(
                  () -> {
                      var bet = new Bet();
                      bet.setBet(1);
                      bet.setPlayerNumber(50);
                      var win = gameService.playGame(bet);
                      assertThat(win).isIn(0d, getResultByFormula(bet.getPlayerNumber(),bet.getBet()));
                      winTotal.updateAndGet(prev -> prev + win);
                      countdown.countDown();
                  });
      }

      countdown.await();

      System.out.printf("RTP: %d%%\n", (int) (winTotal.get() / 10000));

      assertThat(winTotal.get()).isLessThan(1_000_000);
  }

  private double getResultByFormula(int playerNumber, double bet) {
    return bet * (99d / (100d - playerNumber));
  }
}
