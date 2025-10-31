package com.tmps.domain;

import com.tmps.domain.model.Player;

import java.util.*;


public class GameState {
    private static GameState instance;
    private Player player;
    private int currentFloor;
    private boolean gameOver;
    private Random random;
    private int maxFloor;
    private boolean victory;

    private GameState() {
        this(10);
    }

    private GameState(int maxFloor) {
        this.currentFloor = 1;
        this.gameOver = false;
        this.random = new Random();
        this.maxFloor = maxFloor;
        this.victory = false;
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
        if (currentFloor >= maxFloor) {
            this.victory = true;
            this.gameOver = true;
        }
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

    public int getMaxFloor() { return maxFloor; }

    public boolean hasWon() { return victory; }
}
