package com.tmps.client.action;

import com.tmps.client.Renderer;
import com.tmps.domain.GameState;
import com.tmps.domain.model.Player;
import com.tmps.domain.model.weapon.Weapon;

import java.util.List;
import java.util.Scanner;

public class InventoryAction implements Action {
    private final Renderer ui;
    private final Scanner scanner;
    private final java.util.Map<String, Runnable> choices = new java.util.HashMap<>();

    private Player player;

    public InventoryAction(Renderer ui, Scanner scanner) {
        this.ui = ui;
        this.scanner = scanner;
        choices.put("0", this::cancelInventory);
    }

    @Override
    public void execute() {
    this.player = GameState.getInstance().getPlayer();
    List<Weapon> inventory = player.getInventory();

        if (inventory.isEmpty()) {
            ui.showEmptyInventory();
            return;
        }

    ui.showInventory(inventory);

    String input = ui.readInventoryChoice(scanner);
        Runnable action = choices.get(input);
        if (action != null) action.run();
        else handleItemSelection(input);
    }

    private void cancelInventory() {
    }

    private void handleItemSelection(String input) {
        try {
            int idx = Integer.parseInt(input) - 1;
            List<Weapon> inventory = player.getInventory();
            if (idx < 0 || idx >= inventory.size()) {
                ui.showInvalidChoice();
                return;
            }
            Weapon item = inventory.get(idx);
            item.use(player);
            inventory.remove(idx);
        } catch (NumberFormatException e) {
            ui.showInvalidChoice();
        }
    }
}
