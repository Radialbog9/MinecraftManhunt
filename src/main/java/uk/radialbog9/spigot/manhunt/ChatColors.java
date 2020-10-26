package uk.radialbog9.spigot.manhunt;

import org.bukkit.ChatColor;

public class ChatColors {
    /**
     * Shorter way to get chat message colors
     * @param msg String Chat message
     * @return String Decoded message
     */
    public static String getMsgColor(String msg) {
        //Get colored message and return it
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
