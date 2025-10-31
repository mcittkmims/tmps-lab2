package com.tmps.client;

import com.tmps.domain.GameState;
import com.tmps.domain.factory.DragonSpawner;
import com.tmps.domain.factory.GoblinSpawner;
import com.tmps.domain.factory.MonsterSpawner;
import com.tmps.domain.factory.OrcSpawner;
import com.tmps.domain.model.Player;
import com.tmps.domain.model.monster.Monster;
import com.tmps.domain.model.weapon.Sword;
import com.tmps.domain.model.weapon.Weapon;
import com.tmps.domain.registry.WeaponRegistry;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DungeonCrawlerGame {
    private static Scanner scanner = new Scanner(System.in);
    private static GameState gameState = GameState.getInstance();
    private static WeaponRegistry itemRegistry = new WeaponRegistry();

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("DUNGEON CRAWLER - Creational Patterns Demo");
        System.out.println("=".repeat(60));

        System.out.print("\nEnter your hero's name: ");
        String playerName = scanner.nextLine();

        Player player = new Player(playerName);
        gameState.setPlayer(player);

        System.out.println("\nWelcome, " + playerName + "!");
        System.out.println("Your quest: Survive the dungeon and defeat monsters!");

        // Game loop
        while (!gameState.isGameOver() && player.isAlive()) {
            showStatus();

            System.out.println("\n1. Explore deeper (fight monster)");
            System.out.println("2. Use item from inventory");
            System.out.println("3. Visit shop");
            System.out.println("4. Quit");
            System.out.print("Choose action: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    encounterMonster();
                    break;
                case "2":
                    useInventory();
                    break;
                case "3":
                    visitShop();
                    break;
                case "4":
                    gameState.setGameOver(true);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        if (player.isAlive()) {
            System.out.println("\nThanks for playing! You survived " + gameState.getCurrentFloor() + " floors!");
        } else {
            System.out.println("\nGame Over! You were defeated on floor " + gameState.getCurrentFloor());
        }

        scanner.close();
    }

    private static void showStatus() {
        Player player = gameState.getPlayer();
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Floor: " + gameState.getCurrentFloor() + " | HP: " + player.getHealth() + "/" + player.getMaxHealth() + " | Gold: " + player.getGold());
        System.out.println("Weapon: " + player.getEquippedWeapon().getName() + " (+" + player.getEquippedWeapon().getDamage() + " dmg)");
        System.out.println("=".repeat(60));
    }

    private static void encounterMonster() {
        Player player = gameState.getPlayer();
        int floor = gameState.getCurrentFloor();

        // Use Factory Method to create random monster
        MonsterSpawner factory;
        Random rand = gameState.getRandom();
        int monsterType = rand.nextInt(3);

        if (monsterType == 0) factory = new GoblinSpawner();
        else if (monsterType == 1) factory = new OrcSpawner();
        else factory = new DragonSpawner();

        Monster monster = factory.spawnMonster(floor);

        System.out.println("\n⚔️  A " + monster.getName() + " appears! (HP: " + monster.getHealth() + ")");

        // Battle loop
        while (monster.isAlive() && player.isAlive()) {
            System.out.println("\n1. Attack");
            System.out.println("2. Run away");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                int playerDamage = player.attack();
                monster.takeDamage(playerDamage);
                System.out.println("You deal " + playerDamage + " damage! " + monster.getName() + " HP: " + monster.getHealth());

                if (monster.isAlive()) {
                    int monsterDamage = monster.attack();
                    player.takeDamage(monsterDamage);
                    System.out.println(monster.getAttackMessage() + " Takes " + monsterDamage + " damage! Your HP: " + player.getHealth());
                }
            } else if (choice.equals("2")) {
                System.out.println("You fled from battle!");
                return;
            }
        }

        if (monster.isAlive() == false) {
            System.out.println("\n✨ Victory! You defeated the " + monster.getName() + "!");
            player.addGold(monster.getGoldReward());
            System.out.println("Earned " + monster.getGoldReward() + " gold!");
            gameState.nextFloor();
        }
    }

    private static void useInventory() {
        Player player = gameState.getPlayer();
        List<Weapon> inventory = player.getInventory();

        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty!");
            return;
        }

        System.out.println("\n--- Inventory ---");
        for (int i = 0; i < inventory.size(); i++) {
            Weapon item = inventory.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " (" + item.getType() + ")");
        }
        System.out.print("Use item (0 to cancel): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= inventory.size()) {
                Weapon item = inventory.get(choice - 1);
                item.use(player);
                inventory.remove(choice - 1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    private static void visitShop() {
        Player player = gameState.getPlayer();
        int floor = gameState.getCurrentFloor();

        System.out.println("\n--- Shop ---");
        System.out.println("Your gold: " + player.getGold());

        // Use Prototype pattern to create scaled items
        Weapon sword = itemRegistry.put("very_basic_sword", new Sword("Wood Sword", 5, 5));

        System.out.println("1. " + sword.getName() + " (+" + sword.getDamage() + " dmg) - " + sword.getValue() + " gold");
        System.out.println("2. Leave shop");
        System.out.print("Buy: ");

        String choice = scanner.nextLine();

        if (choice.equals("1") && player.getGold() >= sword.getValue()) {
            player.addGold(-sword.getValue());
            player.equipWeapon(sword);
        } else if (!choice.equals("2")) {
            System.out.println("Not enough gold or invalid choice!");
        }
    }
}
