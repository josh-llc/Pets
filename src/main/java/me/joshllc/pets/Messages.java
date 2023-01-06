package me.joshllc.pets;

import me.joshllc.pets.files.Configuration;
import me.joshllc.pets.util.text.C;

import java.util.Arrays;
import java.util.List;

public class Messages {

    private Configuration config;

    public Messages(final Pets pets) {
        config = pets.getFileHandler().getMessages();
    }

    public String get(String path, String defaultMessage) {
        if(config.yaml().getString(path) != null) {
            return C.colorize(config.yaml().getString(path));
        } else {
            config.yaml().set(path, defaultMessage);
            config.save();
            return C.colorize(defaultMessage);
        }
    }

    public List<String> getList(String path, String... defaultList) {
        if(config.yaml().getString(path) != null) {
            return C.colorize(config.yaml().getStringList(path));
        } else {
            config.yaml().set(path, Arrays.asList(defaultList));
            config.save();
            return C.colorize(Arrays.asList(defaultList));
        }
    }

}
