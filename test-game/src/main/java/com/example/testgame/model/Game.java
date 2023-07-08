package com.example.testgame.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
public class Game {

    @Id
    @GeneratedValue
    private Long id;
    private double win;
    private int playerNumber;
    private double bet;
    private LocalDateTime timeStamp;
    @OneToOne
    private User user;
}
