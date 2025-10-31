package com.tmps.domain.registry;

import com.tmps.domain.model.weapon.Bow;
import com.tmps.domain.model.weapon.Potion;
import com.tmps.domain.model.weapon.Sword;
import com.tmps.domain.model.weapon.Weapon;

import java.util.HashMap;
import java.util.Map;

public class WeaponRegistry {
    private Map<String, Weapon> prototypes = new HashMap<>();

    public WeaponRegistry() {
        prototypes.put("1", new Sword("Iron Sword", 10, 50));
        prototypes.put("2", new Bow("Wooden Bow", 8, 40, 15));
        prototypes.put("3", new Bow("Unbreaking Bow", 12, 80, 20));
        prototypes.put("4", new Bow("Infinite Bow", 18, 200, 30));
        prototypes.put("5", new Sword("Netherite Sword", 15, 100));
        prototypes.put("6", new Sword("Excalibur Sword", 100, 1000));
        prototypes.put("7", new Potion("Healing Potion", 50, 25));
    }

    public Weapon getWeapon(String key) {
        Weapon weapon = prototypes.get(key);
        if(weapon == null) {
            throw new IllegalArgumentException("No such item in the registry");
        }
        return weapon.clone();
    }

    public Map<String, Weapon> getAllPrototypes() {
        return Map.copyOf(prototypes);
    }

    public Weapon put(String key, Weapon weapon) {
        prototypes.put(key, weapon);
        return weapon;
    }

    public int size(){
        return prototypes.size();
    }
}
