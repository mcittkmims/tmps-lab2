# Creational Design Patterns

## Author: Adrian Vremere

----

## Objectives

* Study and implement creational design patterns in a small Java project.
* Choose a domain (simple roguelike / dungeon crawler) and apply appropriate instantiation mechanisms.
* Implement at least three creational patterns and explain their usage in the codebase.

----

## Used Design Patterns

* Singleton
* Factory Method
* Prototype

----

## Implementation

This project implements a small demo game domain (player, monsters, weapons). Below are the actual code snippets from the project for each implemented creational pattern followed by a couple of sentences describing what I did in the code (no general theory).

### 1) Singleton — `GameState`

```java
private static GameState instance;

private GameState() { /* private ctor */ }

public static GameState getInstance() {
	if (instance == null) {
		instance = new GameState();
	}
	return instance;
}
```

I implemented a lazy singleton in `GameState` by keeping a private static `instance`, a private constructor and a `getInstance()` accessor. The class holds the global game data (player, current floor, random generator) that other classes retrieve via `GameState.getInstance()`.

### 2) Factory Method — `MonsterSpawner` + `GoblinSpawner`

`MonsterSpawner` (partial):

```java
public abstract class MonsterSpawner {
	public Monster spawnMonster(int floor) {
		Monster monster = createMonster(floor);
		System.out.println("A " + monster.getName() + " appears! (HP: " + monster.getHealth() + ")");
		return monster;
	}

	protected abstract Monster createMonster(int floor);
}
```

`GoblinSpawner` (concrete):

```java
public class GoblinSpawner extends MonsterSpawner {
	@Override
	protected Monster createMonster(int floor) {
		return new Goblin(floor);
	}
}
```

I defined `spawnMonster()` in an abstract `MonsterSpawner` which delegates the actual instantiation to `createMonster(int)`. Each concrete spawner (e.g., `GoblinSpawner`) returns a specific `Monster` implementation by overriding `createMonster`, so spawn behavior is shared while type selection is delegated to subclasses.

### 3) Prototype — `Weapon` + `WeaponRegistry`

`Weapon` (clone):

```java
public abstract class Weapon implements Cloneable {
	protected String name;
	protected int damage;
	protected int value;

	@Override
	public Weapon clone() {
		try {
			return (Weapon) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
```

`WeaponRegistry` (prototype lookup):

```java
private Map<String, Weapon> prototypes = new HashMap<>();

public WeaponRegistry() {
	prototypes.put("1", new Sword("Iron Sword", 10, 50));
	// ... more prototypes
}

public Weapon getWeapon(String key) {
	Weapon weapon = prototypes.get(key);
	if (weapon == null) throw new IllegalArgumentException("No such item in the registry");
	return weapon.clone();
}
```

I made `Weapon` cloneable and implemented `clone()` to return a shallow copy. `WeaponRegistry` holds prototype instances keyed by id and returns `weapon.clone()` so callers receive independent weapon instances without building them from scratch.

----

## Conclusions

* The project implements the requested patterns in code: a lazy singleton (`GameState`) for global state, a factory-method-based spawner hierarchy for monsters, and a prototype registry (`WeaponRegistry`) for weapons.
* Working on these patterns made the object-creation responsibilities explicit and separated from gameplay logic. As a result, adding new monster types or weapon prototypes requires minimal changes and keeps client code simple.
* The implementation keeps clarity as the primary goal for the lab. Practical improvements for production code include making `GameState` thread-safe (e.g., holder idiom or synchronized accessor), adding unit tests to verify `clone()` semantics and spawner behavior, and implementing deep cloning for weapons that contain mutable nested state.
* Possible future extensions: introduce an Abstract Factory to group related spawners, add a Builder for assembling complex player/equipment setups, or add integration tests that exercise end-to-end spawning and inventory flows.
