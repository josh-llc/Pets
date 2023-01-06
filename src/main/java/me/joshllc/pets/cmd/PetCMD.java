package me.joshllc.pets.cmd;

import lombok.RequiredArgsConstructor;
import me.joshllc.pets.Pets;
import me.joshllc.pets.gui.PetGUI;
import me.joshllc.pets.util.command.Command;
import me.joshllc.pets.util.command.CommandArgs;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class PetCMD {

    private final Pets pets;

    @Command(name = "pet", description = "Manage your pet.", permission = "N/A", usage = "/pet")
    public void onPet(CommandArgs args) {
        if(!args.isPlayer()) {
            args.getSender().sendMessage(pets.getMessages().get("generic.console-not-player", ""));
            return;
        }
        Player player = args.getPlayer();
        if(pets.getPetHandler().getPet(player.getUniqueId()) == null) {
            player.sendMessage(pets.getMessages().get("pets.no-pet", ""));
            return;
        }
        PetGUI gui = new PetGUI(pets, player);
        gui.open(player);
    }
}
