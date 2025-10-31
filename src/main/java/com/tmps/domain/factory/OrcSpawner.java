package com.tmps.domain.factory;

import com.tmps.domain.model.monster.Monster;
import com.tmps.domain.model.monster.Orc;

public class OrcSpawner extends MonsterSpawner{
    @Override
    protected Monster createMonster(int floor) {
        return new Orc(floor);
    }
}
