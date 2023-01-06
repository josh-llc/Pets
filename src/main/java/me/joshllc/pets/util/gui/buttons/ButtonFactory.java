package me.joshllc.pets.util.gui.buttons;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class ButtonFactory {

    private Button button;

    public ButtonFactory(ItemStack icon) {
        this.button = new Button(icon);
    }

    public ButtonFactory(Material material) {
        this.button = new Button(new ItemStack(material));
    }

    public ButtonFactory(Material material, int amount) {
        this.button = new Button(new ItemStack(material, amount));
    }

    public ButtonFactory click(Consumer<InventoryClickEvent> event) {
        this.button.setClick(event);
        return this;
    }

    public Button build() {
        return this.button;
    }
}
