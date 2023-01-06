package me.joshllc.pets.util.gui;

import me.joshllc.pets.util.gui.buttons.Button;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() != null) {
            if (event.getClickedInventory().getHolder() instanceof GUI) {
                GUI clickedGUI = (GUI) event.getClickedInventory().getHolder();
                Button button = clickedGUI.getButton(event.getSlot());
                if (button != null && button.getClick() != null) {
                    button.getClick().accept(event);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() != null) {
            if (event.getInventory().getHolder() instanceof GUI) {
                GUI gui = (GUI) event.getInventory().getHolder();
                gui.onClose(event);
            }
        }
    }
}
