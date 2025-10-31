package com.tmps.domain.model.monster;

import com.tmps.domain.GameState;

public abstract class Monster {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int attack;
    protected int goldReward;

    public abstract String getAttackMessage();

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int attack() {
        return attack + GameState.getInstance().getRandom().nextInt(5);
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getGoldReward() { return goldReward; }
}
