package com.tmps.domain.model;

import com.tmps.domain.GameState;
import com.tmps.domain.model.weapon.Sword;
import com.tmps.domain.model.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int gold;
    private Weapon equippedWeapon;
    private List<Weapon> inventory;

    public Player(String name) {
        this.name = name;
        this.maxHealth = 150;
        this.health = maxHealth;
        this.gold = 50;
        this.equippedWeapon = new Sword("very simple simple sword", 5, 0);
        this.inventory = new ArrayList<>(List.of(equippedWeapon));
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) health = maxHealth;
    }

    public int attack() {
        // slightly increase variance and average damage
        return equippedWeapon.getDamage() + GameState.getInstance().getRandom().nextInt(8);
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void addWeapon(Weapon weapon) {
        inventory.add(weapon);
    }

    public void equipWeapon(Weapon weapon) {
        equippedWeapon = weapon;
        System.out.println("Equipped " + weapon.getName() + " (+" + weapon.getDamage() + " damage)");
    }

    public boolean isAlive() { return health > 0; }
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getGold() { return gold; }
    public List<Weapon> getInventory() { return inventory; }
    public Weapon getEquippedWeapon() { return equippedWeapon; }
}
