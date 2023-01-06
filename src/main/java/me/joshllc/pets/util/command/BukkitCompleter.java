package me.joshllc.pets.util.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BukkitCompleter implements TabCompleter {

    private Map<String, Map.Entry<Method, Object>> completers = new HashMap<>();

    public void addCompleter(String label, Method m, Object obj) {
        completers.put(label, new AbstractMap.SimpleEntry<>(m, obj));
    }

    @SuppressWarnings("unchecked")
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        for (int i = args.length; i >= 0; i--) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(label.toLowerCase());
            for (int x = 0; x < i; x++) {
                if (!args[x].equals("") && !args[x].equals(" ")) {
                    buffer.append(".").append(args[x].toLowerCase());
                }
            }
            String cmdLabel = buffer.toString();
            if (completers.containsKey(cmdLabel)) {
                Map.Entry<Method, Object> entry = completers.get(cmdLabel);
                try {
                    return (List<String>) entry.getKey().invoke(entry.getValue(),
                            new CommandArgs(sender, command, label, args, cmdLabel.split("\\.").length - 1));
                } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
