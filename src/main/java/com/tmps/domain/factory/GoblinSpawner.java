package com.tmps.domain.factory;

import com.tmps.domain.model.monster.Goblin;
import com.tmps.domain.model.monster.Monster;

public class GoblinSpawner extends MonsterSpawner {
    @Override
    protected Monster createMonster(int floor) {
        return new Goblin(floor);
    }
}
