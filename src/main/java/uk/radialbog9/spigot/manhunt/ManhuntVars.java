package uk.radialbog9.spigot.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class ManhuntVars {
    private static boolean gameStarted;
    private static List<Player> hunters;
    private static List<Player> runners;

    /**
     * Checks if game is started
     * @return boolean True if game is started, false if not
     */
    public static boolean isGameStarted() {
        return ManhuntVars.gameStarted;
    }

    /**
     * Sets if the game is started
     * @param gameStarted If game is started1
     */
    public static void setGameStarted(boolean gameStarted) {
        ManhuntVars.gameStarted = gameStarted;
    }

    /**
     * Current list of hunters
     * @return List&lt;Player&gt; Hunters
     */
    public static List<Player> getHunters() {
        return ManhuntVars.hunters;
    }

    /**
     * Checks if current player is hunter
     * @param p Player
     * @return boolean True if player is hunter, otherwise false.
     */
    public static boolean isHunter(Player p) {
        return ManhuntVars.hunters.contains(p);
    }

    /**
     * Adds player to hunters
     * @param p Player
     */
    public static void addHunter(Player p) {
        if(!ManhuntVars.hunters.contains(p)) {
            ManhuntVars.hunters.add(p);
        }
    }

    /**
     * Removes player from hunters
     * @param p Player
     */
    public static void removeHunter(Player p) {
        if(ManhuntVars.hunters.contains(p)) {
            ManhuntVars.hunters.remove(p);
        }
    }

    /**
     * Removes all hunters.
     */
    public static void removeAllHunters() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(ManhuntVars.hunters.contains(p)) {
                ManhuntVars.hunters.remove(p);
            }
        }
    }

    /**
     * Current list of runners
     * @return List&lt;Player&gt; Runners
     */
    public static List<Player> getRunners() {
        return ManhuntVars.runners;
    }

    /**
     * Checks if current player is runner
     * @param p Player
     * @return boolean True if player is runner, otherwise false.
     */
    public static boolean isRunner(Player p) {
        return ManhuntVars.runners.contains(p);
    }

    /**
     * Adds player to runners
     * @param p Player
     */
    public static void addRunner(Player p) {
        if(!ManhuntVars.runners.contains(p)) {
            ManhuntVars.runners.add(p);
        }
    }

    /**
     * Removes player from hunters
     * @param p Player
     */
    public static void removeRunner(Player p) {
        if(ManhuntVars.runners.contains(p)) {
            ManhuntVars.runners.remove(p);
        }
    }

    /**
     * Removes all runners.
     */
    public static void removeAllRunners() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(ManhuntVars.runners.contains(p)) {
                ManhuntVars.runners.remove(p);
            }
        }
    }
}
