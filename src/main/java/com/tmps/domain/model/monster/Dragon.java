package com.tmps.domain.model.monster;

public class Dragon extends Monster {
    public Dragon(int floor) {
        this.name = "Dragon";
        this.maxHealth = 100 + (floor * 20);
        this.health = maxHealth;
        this.attack = 15 + (floor * 4);
        this.goldReward = 75 + (floor * 30);
    }

    public String getAttackMessage() {
        return name + " breathes fire!";
    }
}
