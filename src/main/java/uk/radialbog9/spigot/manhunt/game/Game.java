/*
 * Copyright (c) 2020-2025 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class that represents a game of Manhunt.
 */
public class Game {
    @Getter
    @Setter
    private boolean gameStarted = false;

    @Getter
    private final ArrayList<Player> hunters = new ArrayList<>();

    /**
     * Returns true if the player is a hunter.
     * @param p Player the player
     * @return true if the player is a hunter, false otherwise
     */
    public boolean isHunter(Player p) { return hunters.contains(p); }

    @Getter
    private final ArrayList<OfflinePlayer> disconnectedHunters = new ArrayList<>();

    /**
     * Returns true if the player was a hunter but was disconnected.
     * @param p Player the player
     * @return true if the player was a hunter but was disconnected, false otherwise
     */
    public boolean isDisconnectedHunter(OfflinePlayer p) { return disconnectedHunters.contains(p); }

    @Getter
    private final ArrayList<Player> runners = new ArrayList<>();

    /**
     * Returns true if the player is a runner.
     * @param p Player the player
     * @return true if the player is a runner, false otherwise
     */
    public boolean isRunner(Player p) { return runners.contains(p); }

    @Getter
    private final ArrayList<OfflinePlayer> disconnectedRunners = new ArrayList<>();

    /**
     * Returns true if the player was a runner but was disconnected.
     * @param p Player the player
     * @return true if the player was a runner but was disconnected, false otherwise
     */
    public boolean isDisconnectedRunner(OfflinePlayer p) { return disconnectedRunners.contains(p); }

    @Getter
    private final ArrayList<Player> deadRunners = new ArrayList<>();

    /**
     * Returns true if the player is a dead runner.
     * @param p Player the player
     * @return true if the player is a dead runner, false otherwise
     */
    public boolean isDeadRunner(Player p) { return deadRunners.contains(p); }

    @Getter
    private final ArrayList<String> activeScenarios = new ArrayList<>();

    @Getter
    @Setter
    private Objective gameObjective = Objective.DEFEAT_ENDER_DRAGON;

    @Getter
    @Setter
    private LocalDateTime gameEndTime;

    /**
     * Gets all players in the game - both runners and hunters.
     * @return ArrayList of players in the game
     */
    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.addAll(hunters);
        players.addAll(runners);
        return players;
    }

    public Game() {
        // nothing here yet
    }
}
