package com.example.testgame.model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class Bet {

  @Min(value = 1)
  @Max(value = 100)
  private int playerNumber;
  private double bet;
}
