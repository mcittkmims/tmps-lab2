package com.tmps.domain.model.weapon;

import com.tmps.domain.model.Player;

/**
 * A consumable that heals the player when used.
 */
public class Potion extends Weapon {
    private final int healAmount;

    public Potion(String name, int healAmount, int value) {
        super(name, 0, value);
        this.healAmount = healAmount;
        this.type = "consumable";
    }

    @Override
    public void use(Player player) {
        player.heal(healAmount);
        System.out.println("You drink the " + name + " and recover " + healAmount + " HP.");
    }

    @Override
    public Potion clone() {
        return (Potion) super.clone();
    }
}
