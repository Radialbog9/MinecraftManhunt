/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.template;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SwapScenarioTemplate extends BukkitRunnable {
    /**
     * Swaps player 1 with player 2
     * @param player1 The first player
     * @param player2 The second player
     */
    public abstract void swap(Player player1, Player player2);

    /**
     * Gets the set of players
     * @return List of players
     */
    public abstract List<Player> getPlayerSet();

    @Override
    public void run() {
        ArrayList<Player> candidates = new ArrayList<>(getPlayerSet());
        while(!candidates.isEmpty()) {
            if(candidates.size() == 1) {
                // Only one player left, can't swap
                break;
            }

            // Randomise the list
            Collections.shuffle(candidates);

            // Pick the first player
            Player player1 = candidates.get(0);

            // Pick a random swap candidate
            Player player2 = candidates.get(1);

            // Swap the players
            swap(player1, player2);

            // Remove the players from the list
            candidates.remove(player1);
            candidates.remove(player2);
        }
    }
}
