package dev.jensderuiter.biomesound;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.java_websocket.WebSocket;

public class BiomeThread extends BukkitRunnable {
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            WebSocket conn = Biomesound.server.connections.get(player);
            if (conn != null) {
                String biome = player.getLocation().getBlock().getBiome().name();
                if (!biome.equals(Biomesound.currentBiome.get(player))) {
                    conn.send(biome);

                    Biomesound.currentBiome.put(player, biome);
                }
            }
        }
    }
}
