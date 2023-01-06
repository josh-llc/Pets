package me.joshllc.pets.pet;

import lombok.RequiredArgsConstructor;
import me.joshllc.pets.Pets;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class PetListener implements Listener {

    private final Pets pets;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        pets.getPetHandler().fetchPet(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        pets.getPetHandler().savePet(event.getPlayer().getUniqueId());
    }
}
