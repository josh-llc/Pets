package me.joshllc.pets.cmd;

import lombok.RequiredArgsConstructor;
import me.joshllc.pets.Pets;
import me.joshllc.pets.util.command.Command;
import me.joshllc.pets.util.command.CommandArgs;

@RequiredArgsConstructor
public class PetsCMD {

    private final Pets pets;

    @Command(name = "pets", description = "View the help menu.", permission = "pets.admin", usage = "/pets")
    public void onPets(CommandArgs args) {
        if(!args.getSender().hasPermission("pets.admin")) {
            args.getSender().sendMessage(pets.getMessages().get("generic.no-permission", ""));
            return;
        }
        pets.getMessages().getList("help", "").forEach(args.getSender()::sendMessage);
    }

    @Command(name = "pets.reload", description = "Reload the plugin.", permission = "pets.admin", usage = "/pets reload")
    public void onPetsReload(CommandArgs args) {
        if(!args.getSender().hasPermission("pets.admin")) {
            args.getSender().sendMessage(pets.getMessages().get("generic.no-permission", ""));
            return;
        }
        pets.getFileHandler().getGui().reload();
        pets.getFileHandler().getMessages().reload();
        args.getSender().sendMessage(pets.getMessages().get("generic.reload", ""));
    }

}
