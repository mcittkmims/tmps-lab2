package com.tmps.domain.model.monster;

public class Orc extends Monster {
    public Orc(int floor) {
        this.name = "Orc";
        this.maxHealth = 50 + (floor * 15);
        this.health = maxHealth;
        this.attack = 8 + (floor * 3);
        this.goldReward = 30 + (floor * 15);
    }

    public String getAttackMessage() {
        return name + " swings a massive club!";
    }
}
