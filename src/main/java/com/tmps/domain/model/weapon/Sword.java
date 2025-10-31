package com.tmps.domain.model.weapon;

import com.tmps.domain.model.Player;

public class Sword extends Weapon {
    public Sword(String name, int damage, int value) {
        super(name, damage, value);
        this.type = "ranged";
    }

    @Override
    public void use(Player player) {
        player.equipWeapon(this);
        System.out.println("You feel the weight of " + name + " in your hands. It's sharp and ready!");
    }
}
