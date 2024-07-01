/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 *  You are allowed to use this code under the GPL3 license, which allows
 *  commercial use, distribution, modification, and licensed works,
 *  providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@Scenario("HUNTER_RANDOM_MOBS")
@ScenarioRunnable
public class HunterRandomMobsScenario extends BukkitRunnable {
    @Override
    public void run() {
        if(
                GameManager.getGame().isGameStarted() &&
                        GameManager.getGame().getActiveScenarios().contains("HUNTER_RANDOM_MOBS")
        ) {
            // Get all entity types
            EntityType[] entities = EntityType.values();

            // Pick random mob
            EntityType chosenType = entities[Utils.getRandomInt(0, entities.length - 1)];

            // Loop throygh all hunters
            for (Player hunter : GameManager.getGame().getHunters()) {
                // Spawn the mob at the hunter's location
                hunter.getWorld().spawnEntity(hunter.getLocation(), chosenType);
            }
        } else {
            this.cancel();
        }
    }
}
