package com.tmps.domain.factory;

import com.tmps.domain.model.monster.Monster;

public abstract class MonsterSpawner {
    public Monster spawnMonster(int floor) {
        Monster monster = createMonster(floor);
        System.out.println("A " + monster.getName() + " appears! (HP: " + monster.getHealth() + ")");
        return monster;
    }

    protected abstract Monster createMonster(int floor);
}
