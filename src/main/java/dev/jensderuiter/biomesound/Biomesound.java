package dev.jensderuiter.biomesound;

import dev.jensderuiter.biomesound.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.java_websocket.WebSocket;

import java.io.IOException;
import java.util.HashMap;

public final class Biomesound extends JavaPlugin {

    MessageUtil mu = new MessageUtil();
    public static HashMap<Player, String> currentBiome = new HashMap<>();
    public static WsServer server;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        int port = this.getConfig().getInt("port");
        try {
            server = new WsServer(port);
            server.start();
            Bukkit.getConsoleSender().sendMessage(mu.color("&aBiomeSound is klaar voor gebruik!"));
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(mu.color("&cBiomeSound is kapoet!"));
        }
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleAsyncRepeatingTask(this, new BiomeThread(), 0L, 20L);
    }

    @Override
    public void onDisable() {
        try {
            server.stop();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Bukkit.getConsoleSender().sendMessage(mu.color("&cBiomeSound is uit :("));
    }
}
