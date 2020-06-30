package dev.jensderuiter.biomesound;

import dev.jensderuiter.biomesound.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WsServer extends WebSocketServer {

    public List<WebSocket> noAuthConnections = new ArrayList<>();
    public HashMap<Player, WebSocket> connections = new HashMap<>();
    MessageUtil mu = new MessageUtil();

    public WsServer(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        noAuthConnections.add(conn);
        mu.console("Connected.");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        noAuthConnections.remove(conn);
        connections.values().remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        Player player = Bukkit.getPlayer(message);
        if (player != null) {
            noAuthConnections.remove(conn);
            connections.put(player, conn);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {

    }

    @Override
    public void onStart() {
        mu.console("Websocket server started.");
    }
}
