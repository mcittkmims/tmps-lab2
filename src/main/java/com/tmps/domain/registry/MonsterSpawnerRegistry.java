package com.tmps.domain.registry;

import com.tmps.domain.factory.DragonSpawner;
import com.tmps.domain.factory.GoblinSpawner;
import com.tmps.domain.factory.MonsterSpawner;
import com.tmps.domain.factory.OrcSpawner;

import java.util.List;
import java.util.Random;

public class MonsterSpawnerRegistry {
    private final List<MonsterSpawner> spawners;
    private final Random random = new Random();

    public MonsterSpawnerRegistry() {
        spawners = List.of(new GoblinSpawner(), new OrcSpawner(), new DragonSpawner());
    }

    public MonsterSpawner getRandomSpawner() {
        if (spawners.isEmpty()) throw new IllegalStateException("No spawners registered");
        int idx = random.nextInt(spawners.size());
        return spawners.get(idx);
    }
}
