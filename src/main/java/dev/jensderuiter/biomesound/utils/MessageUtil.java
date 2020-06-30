package dev.jensderuiter.biomesound.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageUtil {

    public String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public void console(String str) {
        Bukkit.getConsoleSender().sendMessage(color(str));
    }

}
