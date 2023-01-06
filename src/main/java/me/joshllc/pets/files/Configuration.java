package me.joshllc.pets.files;

import me.joshllc.pets.Pets;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Configuration {

    private final Pets pets;

    private final String fileName;

    File file;
    YamlConfiguration data;

    public Configuration(final Pets pets, final String fileName, final File dir) {
        this.pets = pets;
        this.fileName = fileName.endsWith(".yml") ? fileName : fileName + ".yml";
        saveDefault(dir);
    }

    public YamlConfiguration yaml() {
        if(this.data == null) reload();
        return this.data;
    }

    public ConfigurationSection getSection(String key) {
        return yaml().getConfigurationSection(key);
    }

    public void reload() {
        if(this.file == null) {
            this.file = new File(pets.getDataFolder(), this.fileName);
        }
        this.data = YamlConfiguration.loadConfiguration(this.file);
        InputStream stream = pets.getResource(this.fileName);
        if(stream != null) {
            YamlConfiguration defaults = YamlConfiguration.loadConfiguration(new InputStreamReader(stream));
            this.data.setDefaults(defaults);
        }
    }

    public void save() {
        if(this.file == null || this.data == null) {
            pets.error("[Files]", "Failed to save file " + this.fileName + ", file and data could not be found.");
            return;
        }
        try {
            yaml().save(this.file);
        } catch (IOException e) {
            pets.error("[Files]", "Failed to save file " + this.fileName + "\nError: " + e.getMessage());
        }
    }

    public void saveDefault(File dir) {
        if(this.file == null) {
            this.file = new File(dir, this.fileName);
        }
        if(!this.file.exists()) {
            if(pets.getResource(this.fileName) != null) {
                pets.saveResource(this.fileName, false);
            } else {
                try {
                    this.file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
