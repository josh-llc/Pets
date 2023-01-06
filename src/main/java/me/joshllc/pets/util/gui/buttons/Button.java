package me.joshllc.pets.util.gui.buttons;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Getter
@Setter
public class Button {

    private final ItemStack icon;
    private Consumer<InventoryClickEvent> click;
}
