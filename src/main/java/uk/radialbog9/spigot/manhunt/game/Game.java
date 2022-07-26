/*
 * Copyright (c) 2020-2022 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioType;

import java.util.ArrayList;

/**
 * Class that stores all the game variables
 */
public class Game {
    @Getter
    @Setter
    private boolean gameStarted = false;

    @Getter
    private final ArrayList<Player> hunters = new ArrayList<>();
    public boolean isHunter(Player p) { return hunters.contains(p); }
    @Getter
    private final ArrayList<OfflinePlayer> disconnectedHunters = new ArrayList<>();
    public boolean isDisconnectedHunter(OfflinePlayer p) { return disconnectedHunters.contains(p); }
    @Getter
    private final ArrayList<Player> runners = new ArrayList<>();
    public boolean isRunner(Player p) { return runners.contains(p); }
    @Getter
    private final ArrayList<OfflinePlayer> disconnectedRunners = new ArrayList<>();
    public boolean isDisconnectedRunner(OfflinePlayer p) { return disconnectedRunners.contains(p); }
    @Getter
    private final ArrayList<Player> deadRunners = new ArrayList<>();
    public boolean isDeadRunner(Player p) { return deadRunners.contains(p); }

    @Getter
    private final ArrayList<ScenarioType> activeScenarios = new ArrayList<>();

    public Game() {

    }
}
