package uk.radialbog9.spigot.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Utilities to use
 */
public class Utils {

    private static String[] manhuntPermissions = {
            "manhunt.add",
            "manhunt.remove",
            "manhunt.start",
            "manhunt.stop",
            "manhunt.list",
            "manhunt.spectate"
    };

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

    /**
     * Checks for all manhunt permissions
     * @param s CommandSender
     * @return boolean if sender has no manhunt permissions
     */
    public static boolean hasNoManhuntPermissions(CommandSender s) {
        boolean hasNoMHP = true;
        for(String perm : manhuntPermissions) {
            if(s.hasPermission(perm)) hasNoMHP = false;
        }
        return hasNoMHP;
    }
    /**
     * Checks for all manhunt permissions
     * @param p Player
     * @return boolean if player has no manhunt permissions
     */
    public static boolean hasNoManhuntPermissions(Player p) {
        boolean hasNoMHP = true;
        for(String perm : manhuntPermissions) {
            if(p.hasPermission(perm)) hasNoMHP = false;
        }
        return hasNoMHP;
    }

    /**
     * Resets the game
     */
    public static void resetGame() {
        //set all players to spectator
        for(Player p : Bukkit.getOnlinePlayers()) {
            if (ManhuntVars.isRunner(p) || ManhuntVars.isHunter(p)) p.setGameMode(GameMode.SPECTATOR);
        }
        //fully end game
        ManhuntVars.setGameStarted(false);
    }
}
