package com.tmps.domain.registry;

import com.tmps.domain.model.weapon.Bow;
import com.tmps.domain.model.weapon.Sword;
import com.tmps.domain.model.weapon.Weapon;

import java.util.HashMap;
import java.util.Map;

public class WeaponRegistry {
    private Map<String, Weapon> prototypes = new HashMap<>();

    public WeaponRegistry() {
        prototypes.put("basic_sword", new Sword("Iron Sword", 10, 50));
        prototypes.put("basic_bow", new Bow("Wooden Bow", 8, 40, 15));
        prototypes.put("epic_bow", new Bow("Unbreaking Bow", 12, 80, 20));
        prototypes.put("legendary_bow", new Bow("Infinite Bow", 18, 200, 30));
        prototypes.put("epic_sword", new Sword("Netherite Sword", 15, 100));
        prototypes.put("legendary_sword", new Sword("Excalibur Sword", 100, 1000));
    }

    public Weapon getWeapon(String key, int floor) {
        Weapon weapon = prototypes.get(key);
        if(weapon == null) {
            throw new IllegalArgumentException("No such item in the registry");
        }
        return weapon.clone();
    }

    public Weapon put(String key, Weapon weapon) {
        prototypes.put(key, weapon);
        return weapon;
    }
}
