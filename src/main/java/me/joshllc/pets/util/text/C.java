package me.joshllc.pets.util.text;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class C {

    public static String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> colorize(List<String> s) {
        return s.stream().map(C::colorize).collect(Collectors.toList());
    }

}
