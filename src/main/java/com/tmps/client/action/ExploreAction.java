package com.tmps.client.action;

import com.tmps.client.Renderer;
import com.tmps.domain.GameState;
import com.tmps.domain.factory.MonsterSpawner;
import com.tmps.domain.registry.MonsterSpawnerRegistry;
import com.tmps.domain.model.Player;
import com.tmps.domain.model.monster.Monster;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExploreAction implements Action {
    private final Renderer renderer;
    private final Scanner scanner;
    private final Map<String, Runnable> choices = new HashMap<>();
    private final MonsterSpawnerRegistry spawnerRegistry = new MonsterSpawnerRegistry();

    private Player player;
    private Monster monster;
    private GameState gameState;
    private boolean fled;

    public ExploreAction(Renderer renderer, Scanner scanner) {
        this.renderer = renderer;
        this.scanner = scanner;
        choices.put("1", this::onAttack);
        choices.put("2", this::onRun);
    }

    @Override
    public void execute() {
        this.gameState = GameState.getInstance();
        this.player = gameState.getPlayer();
        int floor = gameState.getCurrentFloor();

        MonsterSpawner factory = spawnerRegistry.getRandomSpawner();
        Monster monster = factory.spawnMonster(floor);
        renderer.showMonsterEncounter(monster);

        this.monster = monster;
        this.fled = false;

        while (monster.isAlive() && player.isAlive() && !fled) {
            renderer.showBattleOptions();
            String choice = renderer.readBattleChoice(scanner);
            Runnable r = choices.get(choice);
            if (r != null) r.run();
            else renderer.showInvalidChoice();
        }

        if (!monster.isAlive()) {
            renderer.showVictory(monster, monster.getGoldReward());
            player.addGold(monster.getGoldReward());
            gameState.nextFloor();
        }
    }

    private void onAttack() {
        int playerDamage = player.attack();
        monster.takeDamage(playerDamage);
        renderer.showStatus(gameState, player);
        renderer.showPlayerDealtDamage(playerDamage, monster);
        if (monster.isAlive()) {
            int monsterDamage = monster.attack();
            player.takeDamage(monsterDamage);
            renderer.showMonsterAttack(monster.getAttackMessage(), monsterDamage, player.getHealth());
        }
    }

    private void onRun() {
        renderer.showFled();
        this.fled = true;
    }
}
