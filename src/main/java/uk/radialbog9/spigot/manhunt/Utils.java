package uk.radialbog9.spigot.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Utilities to use
 */
public class Utils {
    /**
     * Shorter way to get chat message colors
     * @param msg String Chat message
     * @return String Decoded message
     */
    public static String getMsgColor(String msg) {
        //Get colored message and return it
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    /**
     * Broadcasts message to all players
     * @param msg Message
     */
    public static void broadcastServerMessage(String msg) {
        String msgSend = Utils.getMsgColor(msg);
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(msgSend);
        }
    }
}
