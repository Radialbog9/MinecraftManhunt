package uk.radialbog9.spigot.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerMessages {
    /**
     * Broadcasts message to all players
     * @param msg Message
     */
    public static void broadcastServerMessage(String msg) {
        String msgSend = ChatColors.getMsgColor(msg);
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(msgSend);
        }
    }
}
