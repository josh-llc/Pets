package me.joshllc.pets.gui;

import me.joshllc.pets.Pets;
import me.joshllc.pets.pet.obj.Pet;
import me.joshllc.pets.pet.obj.PetType;
import me.joshllc.pets.util.gui.GUI;
import me.joshllc.pets.util.gui.buttons.Button;
import me.joshllc.pets.util.gui.buttons.ButtonFactory;
import me.joshllc.pets.util.items.CustomHead;
import me.joshllc.pets.util.items.ItemBuilder;
import me.joshllc.pets.util.text.C;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.List;
import java.util.stream.Collectors;

public class AdoptionGUI extends GUI {

    public AdoptionGUI(final Pets pets, Player player) {
        super(C.colorize(pets.getFileHandler().getGui().yaml().getString("adoption.title")), pets.getFileHandler().getGui().yaml().getInt("adoption.rows"));

        ConfigurationSection config = pets.getFileHandler().getGui().getSection("adoption");

        boolean fill = config.getBoolean("fill.enabled");
        if(fill) {
            List<Integer> fillSlots = config.getIntegerList("fill.slots");
            Button fillButton = new ButtonFactory(new ItemBuilder(Material.valueOf(config.getString("fill.material"))).setName(" ").build())
                    .click(event -> event.setCancelled(true))
                    .build();
            for(int i : fillSlots) {
                setButton(i, fillButton);
            }
        }

        ConfigurationSection petSection = config.getConfigurationSection("pets");
        for(String petID : petSection.getKeys(false)) {
            int slot = petSection.getInt(petID + ".slot");
            PetType type = PetType.valueOf(petID.toUpperCase());
            String name = petSection.getString(petID + ".display.name");
            List<String> lore = petSection.getStringList(petID + ".display.lore").stream().map(s ->
                    s.replaceAll("%pet_hp%", String.valueOf(type.getHp()))
                    .replaceAll("%pet_food%", String.valueOf(type.getFood())))
                    .collect(Collectors.toList());
            ItemBuilder display = CustomHead.toItemBuilder(type.getHead());
            display.setName(C.colorize(name));
            display.setLore(C.colorize(lore));
            Button petButton = new ButtonFactory(display.build()).click(event -> {
                event.setCancelled(true);
                Pet pet = new Pet(type);
                pets.getPetHandler().getPlayerPets().put(player.getUniqueId(), pet);
                player.closeInventory();
                player.sendMessage(pets.getMessages().get("pets.adopted", "").replaceAll("%pet_name%", pet.getName()));
            }).build();
            setButton(slot, petButton);
        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
    }
}
