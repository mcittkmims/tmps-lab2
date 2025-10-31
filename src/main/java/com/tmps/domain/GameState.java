package com.tmps.domain;

import com.tmps.domain.model.Player;

import java.util.*;


public class GameState {
    private static GameState instance;
    private Player player;
    private int currentFloor;
    private boolean gameOver;
    private Random random;

    private GameState() {
        this.currentFloor = 1;
        this.gameOver = false;
        this.random = new Random();
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void nextFloor() {
        currentFloor++;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Random getRandom() {
        return random;
    }
}
