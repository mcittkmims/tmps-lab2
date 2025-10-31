package com.tmps.domain.model.weapon;

import com.tmps.domain.model.Player;

public class Bow extends Weapon {
    private int range;

    public Bow(String name, int damage, int value, int range) {
        super(name, damage, value);
        this.range = range;
        this.type = "ranged";
    }

    @Override
    public void use(Player player) {
        player.equipWeapon(this);
        System.out.println("You ready your " + name + " and string an arrow. Time to strike from afar!");
    }

    public int getRange() { return range; }
    public void setRange(int range) { this.range = range; }

    @Override
    public Bow clone() {
        return (Bow) super.clone();
    }
}
