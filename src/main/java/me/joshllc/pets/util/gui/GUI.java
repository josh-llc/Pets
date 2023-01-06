package me.joshllc.pets.util.gui;

import com.google.common.collect.Maps;
import lombok.Getter;
import me.joshllc.pets.util.gui.buttons.Button;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.LinkedHashMap;
import java.util.Map;


public abstract class GUI implements InventoryHolder {

    public Inventory inventory;

    @Getter
    private final String title;
    @Getter
    private final int rows;

    @Getter
    private final LinkedHashMap<Integer, Button> buttons;

    public GUI(String title, int rows) {
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        this.rows = rows;
        this.buttons = Maps.newLinkedHashMap();
        this.inventory = Bukkit.createInventory(this, this.rows * 9, this.title);
    }

    public void addButton(Button button) {
        if (buttons.isEmpty()) {
            buttons.put(0, button);
        } else {
            buttons.put(getNextEmpty(), button);
        }
    }

    public void setButton(int slot, Button button) {
        buttons.put(slot, button);
        this.inventory.setItem(slot, button.getIcon());
    }

    public void removeButton(int slot) {
        buttons.remove(slot);
    }

    public Button getButton(int slot) {
        return buttons.getOrDefault(slot, null);
    }

    public int getHighestFilledSlot() {
        int slot = 0;
        for (int next : buttons.keySet()) {
            if (buttons.get(next) != null && next > slot) slot = next;
        }
        return slot;
    }

    public int getNextEmpty() {
        // Find the next empty slot
        for (int i = 0; i <= getHighestFilledSlot(); i++) {
            if (!buttons.containsKey(i)) {
                return i;
            }
        }
        // If no empty slots were found, return the next slot after the highest slot
        return getHighestFilledSlot() + 1;
    }

    public int getSize() {
        return getRows() * 9;
    }

    public void open(Player player) {
        player.openInventory(getInventory());
    }

    public void refresh(HumanEntity entity) {
        if (entity.getOpenInventory().getTopInventory().getSize() != getSize()) {
            entity.openInventory(getInventory());
            return;
        }

        entity.getOpenInventory().getTopInventory().setContents(getInventory().getContents());
    }

    @Override
    public Inventory getInventory() {
        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
            this.inventory.setItem(entry.getKey(), entry.getValue().getIcon());
        }
        return this.inventory;
    }

    public abstract void onClose(InventoryCloseEvent event);

}
