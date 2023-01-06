package me.joshllc.pets;

import lombok.Getter;
import me.joshllc.pets.cmd.AdoptCMD;
import me.joshllc.pets.cmd.PetCMD;
import me.joshllc.pets.files.FileHandler;
import me.joshllc.pets.pet.PetHandler;
import me.joshllc.pets.pet.PetListener;
import me.joshllc.pets.util.command.CommandFramework;
import me.joshllc.pets.util.gui.GUIListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

@Getter
public class Pets extends JavaPlugin {

    private CommandFramework cmdFramework;
    private FileHandler fileHandler;
    private Messages messages;

    private PetHandler petHandler;

    @Override
    public void onEnable() {
        this.cmdFramework = new CommandFramework(this);
        registerHandlers();
        registerCommands(new AdoptCMD(this), new PetCMD(this));
        registerListeners(new GUIListener(), new PetListener(this));
        this.cmdFramework.registerHelp();
    }

    @Override
    public void onDisable() {
        petHandler.savePets();
    }

    private void registerHandlers() {
        this.fileHandler = new FileHandler(this);
        this.messages = new Messages(this);
        this.petHandler = new PetHandler(this);
    }

    private void registerListeners(Listener... listeners) {
        Arrays.stream(listeners).forEach(l -> getServer().getPluginManager().registerEvents(l, this));
    }

    private void registerCommands(Object... objects) {
        Arrays.stream(objects).forEach(o -> cmdFramework.registerCommands(o));
    }

    public void error(String prefix, String message) {
        getLogger().severe(prefix + " " + message);
    }
    public void info(String prefix, String message) {
        getLogger().info(prefix + " " + message);
    }
}
