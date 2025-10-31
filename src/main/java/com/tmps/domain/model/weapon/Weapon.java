package com.tmps.domain.model.weapon;

import com.tmps.domain.model.Player;

public abstract class Weapon implements Cloneable {
    protected String name;
    protected int damage;
    protected int value;
    protected String type;

    public Weapon(String name, int damage, int value) {
        this.name = name;
        this.damage = damage;
        this.value = value;
    }

    public abstract void use(Player player);

    @Override
    public Weapon clone() {
        try {
            return (Weapon) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getName() { return name; }
    public int getDamage() { return damage; }
    public int getValue() { return value; }
    public String getType() {
        return type;
    }

    public void setName(String name) { this.name = name; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setValue(int value) { this.value = value; }
    public void setType(String type) {
        this.type = type;
    }
}