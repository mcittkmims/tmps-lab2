package com.tmps.domain.factory;

import com.tmps.domain.model.monster.Dragon;
import com.tmps.domain.model.monster.Monster;

public class DragonSpawner extends MonsterSpawner{
    @Override
    protected Monster createMonster(int floor) {
        return new Dragon(floor);
    }
}
