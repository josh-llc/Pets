package me.joshllc.pets.cmd;

import lombok.RequiredArgsConstructor;
import me.joshllc.pets.Pets;
import me.joshllc.pets.gui.AdoptionGUI;
import me.joshllc.pets.util.command.Command;
import me.joshllc.pets.util.command.CommandArgs;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class AdoptCMD {

    private final Pets pets;

    @Command(name = "adopt", description = "Adopt a pet.", permission = "N/A", usage = "/adopt")
    public void onAdopt(CommandArgs args) {
        if(!args.isPlayer()) {
            args.getSender().sendMessage(pets.getMessages().get("generic.console-not-player", ""));
            return;
        }
        Player player = args.getPlayer();
        AdoptionGUI gui = new AdoptionGUI(pets, player);
        gui.open(player);
    }
}
