package me.joshllc.pets.gui;

import me.joshllc.pets.Pets;
import me.joshllc.pets.pet.obj.Pet;
import me.joshllc.pets.pet.obj.PetType;
import me.joshllc.pets.prompts.ChangeNamePrompt;
import me.joshllc.pets.util.gui.GUI;
import me.joshllc.pets.util.gui.buttons.Button;
import me.joshllc.pets.util.gui.buttons.ButtonFactory;
import me.joshllc.pets.util.items.CustomHead;
import me.joshllc.pets.util.items.ItemBuilder;
import me.joshllc.pets.util.text.C;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.stream.Collectors;

public class PetGUI extends GUI {

    public PetGUI(final Pets pets, Player player) {
        super(C.colorize(pets.getFileHandler().getGui().yaml().getString("pet.title")), pets.getFileHandler().getGui().yaml().getInt("pet.rows"));

        ConfigurationSection config = pets.getFileHandler().getGui().getSection("pet");

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
        new BukkitRunnable() {
            @Override
            public void run() {
                Pet pet = pets.getPetHandler().getPet(player.getUniqueId());
                if(pet == null || getInventory().getViewers().isEmpty()) {
                    cancel();
                    return;
                }
                int slot = config.getInt("pet.slot");
                String name = config.getString("pet.display.name").replaceAll("%pet_name%", pet.getName());
                List<String> lore = config.getStringList("pet.display.lore").stream().map(s ->
                                s.replaceAll("%pet_hp%", String.valueOf(pet.getHealth()))
                                        .replaceAll("%pet_food%", String.valueOf(pet.getFood())))
                        .collect(Collectors.toList());
                ItemBuilder display = CustomHead.toItemBuilder(pet.getType().getHead());
                display.setName(C.colorize(name));
                display.setLore(C.colorize(lore));
                Button petButton = new ButtonFactory(display.build()).click(event -> { event.setCancelled(true); }).build();
                setButton(slot, petButton);
            }
        }.runTaskTimer(pets, 0L, 10L);

        Pet pet = pets.getPetHandler().getPet(player.getUniqueId());

        int changeNameSlot = config.getInt("change-name.slot");
        Material changeNameMaterial = Material.valueOf(config.getString("change-name.display.material"));
        String changeNameName = config.getString("change-name.display.name").replaceAll("%pet_name%", pets.getName());
        List<String> changeNameLore = config.getStringList("change-name.display.lore").stream().map(s ->
                        s.replaceAll("%pet_name%", pet.getName())
                ).collect(Collectors.toList());
        ItemBuilder changeNameDisplay = new ItemBuilder(changeNameMaterial);
        changeNameDisplay.setName(C.colorize(changeNameName));
        changeNameDisplay.setLore(C.colorize(changeNameLore));
        Button changeNameButton = new ButtonFactory(changeNameDisplay.build()).click(event -> {
            event.setCancelled(true);
            player.closeInventory();
            ConversationFactory cf = new ConversationFactory(pets);
            cf.withFirstPrompt(new ChangeNamePrompt(pets, player))
                    .withEscapeSequence(pets.getMessages().get("pets.rename-quit-keyword", ""))
                    .withLocalEcho(false)
                    .withPrefix(conversationContext -> C.colorize(pets.getMessages().get("pets.rename-prefix", "")))
                    .withTimeout(30)
                    .buildConversation(player).begin();
        }).build();
        setButton(changeNameSlot, changeNameButton);

        int feedSlot = config.getInt("feed.slot");
        Material feedMaterial = pet.getType().getFoodType().getFoodMaterial();
        String feedName = config.getString("feed.display.name").replaceAll("%pet_name%", pets.getName());
        List<String> feedLore = config.getStringList("feed.display.lore").stream().map(s ->
                        s.replaceAll("%pet_name%", pet.getName())
                                .replaceAll("%food_name%", pet.getType().getFoodType().getFoodName())
                ).collect(Collectors.toList());
        ItemBuilder feedDisplay = new ItemBuilder(feedMaterial);
        feedDisplay.setName(C.colorize(feedName));
        feedDisplay.setLore(C.colorize(feedLore));
        Button feedButton = new ButtonFactory(feedDisplay.build()).click(event -> {
            event.setCancelled(true);
            if(event.getClick().isLeftClick())
                pet.setFood(pet.getFood() + 1);
        }).build();
        setButton(feedSlot, feedButton);
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }
}
