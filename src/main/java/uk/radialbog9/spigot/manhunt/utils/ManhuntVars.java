/*
 * Copyright (c) 2021 Radialbog9 and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.playerconfig.PlayerConfig;

import java.util.ArrayList;
import java.util.HashMap;

public class ManhuntVars {
    private static boolean gameStarted;

    private static ArrayList<Player> hunters = new ArrayList<>();
    private static ArrayList<Player> runners = new ArrayList<>();

    private static HashMap<Player, PlayerConfig> playerConfigMap = new HashMap<>();

    private static boolean vanishEnabled;


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
        if(!ManhuntVars.hunters.contains(p)) ManhuntVars.hunters.add(p);
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
        for(Player p : Bukkit.getOnlinePlayers()) if(ManhuntVars.isHunter(p)) ManhuntVars.hunters.remove(p);
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
        if(!ManhuntVars.runners.contains(p)) ManhuntVars.runners.add(p);
    }

    /**
     * Removes player from runners
     * @param p Player
     */
    public static void removeRunner(Player p) {
        ManhuntVars.runners.remove(p);
    }

    /**
     * Removes all runners.
     */
    public static void removeAllRunners() {
        for(Player p : Bukkit.getOnlinePlayers()) ManhuntVars.runners.remove(p);
    }

    /**
     * Gets a player's data object
     * @param p Player
     * @return The player's config
     */
    public static PlayerConfig getPlayerConfig(Player p) {
        if(!playerConfigMap.containsKey(p)) playerConfigMap.put(p, new PlayerConfig(p));
        return playerConfigMap.get(p);
    }

    public static boolean getVanishEnabled() {
        return ManhuntVars.vanishEnabled;
    }

    public static void setVanishEnabled(boolean vanishEnabled) {
        ManhuntVars.vanishEnabled = vanishEnabled;
    }
}
