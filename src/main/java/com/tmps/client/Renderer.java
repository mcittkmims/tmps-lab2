package com.tmps.client;

import com.tmps.domain.GameState;
import com.tmps.domain.model.Player;
import com.tmps.domain.model.monster.Monster;
import com.tmps.domain.model.weapon.Weapon;

import java.util.List;
import java.util.Scanner;

public class Renderer {
    public void printBanner() {
        System.out.println("=".repeat(60));
        System.out.println("DUNGEON CRAWLER");
        System.out.println("=".repeat(60));
    }

    public String readPlayerName(Scanner scanner) {
        System.out.print("\nEnter your hero's name: ");
        return scanner.nextLine();
    }

    public String readMainMenuChoice(Scanner scanner) {
        System.out.print("\nChoose action: ");
        return scanner.nextLine();
    }

    public String readBattleChoice(Scanner scanner) {
        System.out.print("\nChoose: ");
        return scanner.nextLine();
    }

    public String readInventoryChoice(Scanner scanner) {
        System.out.print("\nUse item (0 to cancel): ");
        return scanner.nextLine();
    }

    public String readShopChoice(Scanner scanner) {
        System.out.print("\nBuy (key): ");
        return scanner.nextLine();
    }

    public void showStatus(GameState state, Player player) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Floor: " + state.getCurrentFloor() + " | HP: " + player.getHealth() + "/" + player.getMaxHealth() + " | Gold: " + player.getGold());
        System.out.println("Weapon: " + player.getEquippedWeapon().getName() + " (+" + player.getEquippedWeapon().getDamage() + " dmg)");
        System.out.println("=".repeat(60));
    }

    public void showMainMenu() {
        System.out.println("\n1. Explore deeper (fight monster)");
        System.out.println("2. Use item from inventory");
        System.out.println("3. Visit shop");
        System.out.println("4. Quit");
    }

    public void showMonsterEncounter(Monster m) {
        System.out.println("\n⚔️  A " + m.getName() + " appears! (HP: " + m.getHealth() + ")");
    }

    public void showBattleOptions() {
        System.out.println("\n1. Attack");
        System.out.println("2. Run away");
    }

    public void showInventory(List<Weapon> inventory) {
        System.out.println("\n--- Inventory ---");
        for (int i = 0; i < inventory.size(); i++) {
            Weapon item = inventory.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " (" + item.getType() + ")");
        }
    }

    public void showEmptyInventory() {
        System.out.println("Your inventory is empty!");
    }

    public void showAvailableItems(java.util.Map<String, Weapon> items, int playerGold) {
        System.out.println("\n--- Shop ---");
        System.out.println("Your gold: " + playerGold);
        for (java.util.Map.Entry<String, Weapon> e : items.entrySet()) {
            Weapon w = e.getValue();
            System.out.println(e.getKey() + ". " + w.getName() + " (+" + w.getDamage() + " dmg) - " + w.getValue() + " gold");
        }
        System.out.println("0. Leave shop");
    }

    public void showVictory(Monster m, int gold) {
        System.out.println("\n✨ Victory! You defeated the " + m.getName() + "!");
        System.out.println("Earned " + gold + " gold!");
    }

    public void showFled() {
        System.out.println("You fled from battle!");
    }

    public void showInvalidChoice() {
        System.out.println("Invalid choice!");
    }

    public void showNotEnoughGold() {
        System.out.println("Not enough gold or invalid choice!");
    }

    public void showPurchased(Weapon w) {
        System.out.println("You purchased and equipped " + w.getName());
    }

    public void showWelcome(String playerName) {
        System.out.println();
        System.out.println("Welcome, " + playerName + "!");
        System.out.println("Your quest: Survive the dungeon and defeat monsters!");
    }

    public void showThanks(int floors) {
        System.out.println("\nThanks for playing! You survived " + floors + " floors!");
    }

    public void showGameOver(int floor) {
        System.out.println("\nGame Over! You were defeated on floor " + floor);
    }

    public void showPlayerDealtDamage(int damage, Monster m) {
        System.out.println("You deal " + damage + " damage! " + m.getName() + " HP: " + m.getHealth());
    }

    public void showMonsterAttack(String attackMessage, int damage, int playerHp) {
        System.out.println(attackMessage + " Takes " + damage + " damage! Your HP: " + playerHp);
    }
}
