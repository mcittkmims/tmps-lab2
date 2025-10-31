package com.tmps.domain.model.monster;

public class Goblin extends Monster {
    public Goblin(int floor) {
        this.name = "Goblin";
        this.maxHealth = 30 + (floor * 10);
        this.health = maxHealth;
        this.attack = 5 + (floor * 2);
        this.goldReward = 15 + (floor * 10);
    }

    public String getAttackMessage() {
        return name + " slashes with a rusty dagger!";
    }
}
