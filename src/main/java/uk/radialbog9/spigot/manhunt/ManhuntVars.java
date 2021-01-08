package uk.radialbog9.spigot.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ManhuntVars {
    private static boolean gameStarted;
    private static ArrayList<Player> hunters = new ArrayList<>();
    private static ArrayList<Player> runners = new ArrayList<>();
    private static int gamesStarted;

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
     * @return ArrayList&lt;Player&gt; Hunters
     */
    public static ArrayList<Player> getHunters() {
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
        ManhuntVars.hunters.remove(p);
    }

    /**
     * Removes all hunters.
     */
    public static void removeAllHunters() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            ManhuntVars.hunters.remove(p);
        }
    }

    /**
     * Current list of runners
     * @return ArrayList&lt;Player&gt; Runners
     */
    public static ArrayList<Player> getRunners() {
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
     * Removes player from runners
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

    /**
     * Gets the number of games started (resets when game is stopped)
     * @return int Number of games started
     */
    public static int getGamesStarted() {
        return ManhuntVars.gamesStarted;
    }

    /**
     * Sets the amount of games started to specified parameter (resets when game is stopped)
     * @param gamesStarted Amount of games started
     */
    public static void setGamesStarted(int gamesStarted) {
        ManhuntVars.gamesStarted = gamesStarted;
    }

    /**
     * Adds the specified parameter to the amount of games started (resets when game is stopped)
     * @param gamesStarted Amount of games started
     */
    public static void addGamesStarted(int gamesStarted) {
        ManhuntVars.gamesStarted += gamesStarted;
    }
}
