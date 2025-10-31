package com.tmps.client.action;

import com.tmps.client.Renderer;
import com.tmps.domain.GameState;
import com.tmps.domain.model.Player;
import com.tmps.domain.model.weapon.Weapon;
import com.tmps.domain.registry.WeaponRegistry;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class ShopAction implements Action {
    private final Renderer ui;
    private final Scanner scanner;
    private final WeaponRegistry registry;
    private final Map<String, Runnable> choices = new HashMap<>();

    public ShopAction(Renderer ui, Scanner scanner) {
        this.ui = ui;
        this.scanner = scanner;
        this.registry = new WeaponRegistry();
        Map<String, Weapon> available = registry.getAllPrototypes();
        for (String key : available.keySet()) {
            final String k = key;
            choices.put(k, () -> purchase(k));
        }
        choices.put("0", this::leaveShop);
    }

    @Override
    public void execute() {
        GameState gs = GameState.getInstance();
        Player player = gs.getPlayer();
        Map<String, Weapon> available = registry.getAllPrototypes();
        ui.showAvailableItems(available, player.getGold());

    String choice = ui.readShopChoice(scanner);
        Runnable action = choices.get(choice);
        if (action != null) action.run();
        else ui.showInvalidChoice();
    }

    private void purchase(String key) {
        GameState gs = GameState.getInstance();
        Player player = gs.getPlayer();
        try {
            Weapon w = registry.getWeapon(key);
            if (player.getGold() >= w.getValue()) {
                player.addGold(-w.getValue());
                player.addWeapon(w);
                player.equipWeapon(w);
                ui.showPurchased(w);
            } else {
                ui.showNotEnoughGold();
            }
        } catch (IllegalArgumentException ex) {
            ui.showInvalidChoice();
        }
    }

    private void leaveShop() {
    }
}
