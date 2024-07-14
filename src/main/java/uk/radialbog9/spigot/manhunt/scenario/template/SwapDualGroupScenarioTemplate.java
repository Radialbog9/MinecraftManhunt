/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.template;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class SwapDualGroupScenarioTemplate extends BukkitRunnable {
    /**
     * Swaps player 1 with player 2
     * @param player1 Player 1 (e.g. runner)
     * @param player2 Player 2 (e.g. hunter)
     */
    public abstract void swap(Player player1, Player player2);

    /**
     * Gets the first set of players (e.g. runners)
     * @return List of players
     */
    public abstract List<Player> getPlayerSet1();

    /**
     * Gets the second set of players (e.g. hunters)
     * @return List of players
     */
    public abstract List<Player> getPlayerSet2();

    @Override
    public void run() {
        List<Player> playerSet1 = getPlayerSet1();
        List<Player> playerSet2 = getPlayerSet2();

        List<Player> swapCandidates2 = new ArrayList<>(playerSet2);

        for(Player player : playerSet1) {
            if(swapCandidates2.isEmpty()) {
                // No more players to swap with
                break;
            }
            // Pick a random swap candidate
            Player swapCandidate = swapCandidates2.get(
                    Utils.getRandomInt(0, swapCandidates2.size() - 1)
            );

            swap(player, swapCandidate);

            swapCandidates2.remove(swapCandidate);
        }
    }
}
