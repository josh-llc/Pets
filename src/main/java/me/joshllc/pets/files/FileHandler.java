package me.joshllc.pets.files;

import lombok.Getter;
import me.joshllc.pets.Pets;

@Getter
public class FileHandler {

    private final Configuration messages, gui;

    public FileHandler(final Pets pets) {
        this.messages = new Configuration(pets, "messages.yml", pets.getDataFolder());
        this.gui = new Configuration(pets, "gui.yml", pets.getDataFolder());
    }
}
