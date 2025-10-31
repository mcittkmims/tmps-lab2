package com.tmps.client;

import com.tmps.client.action.*;
import com.tmps.domain.GameState;
import com.tmps.domain.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameController {
    private final Scanner scanner = new Scanner(System.in);
    private final GameState gameState = GameState.getInstance();
    private final Renderer renderer = new Renderer();
    private final Map<String, Action> actions = new HashMap<>();

    public GameController() {
        actions.put("1", new ExploreAction(renderer, scanner));
        actions.put("2", new InventoryAction(renderer, scanner));
        actions.put("3", new ShopAction(renderer, scanner));
        actions.put("4", new QuitAction());
    }

    public void start() {
        renderer.printBanner();

        String playerName = renderer.readPlayerName(scanner);
        Player player = new Player(playerName);
        gameState.setPlayer(player);

        renderer.showWelcome(playerName);

        while (!gameState.isGameOver() && player.isAlive()) {
            renderer.showStatus(gameState, player);

            renderer.showMainMenu();

            String choice = renderer.readMainMenuChoice(scanner);

            Action action = actions.get(choice);
            if (action != null) {
                action.execute();
            } else {
                renderer.showInvalidChoice();
            }
        }

        if (gameState.hasWon()) {
            renderer.showThanks(gameState.getMaxFloor());
        } else if (player.isAlive()) {
            renderer.showThanks(gameState.getCurrentFloor());
        } else {
            renderer.showGameOver(gameState.getCurrentFloor());
        }
    }
}
