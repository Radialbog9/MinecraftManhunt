/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import lombok.Getter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.scenario.config.RunnableRequiredConfig;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfigurable;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfiguration;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@Scenario("HUNTER_RANDOM_MOBS")
@ScenarioRunnable
public class HunterRandomMobsScenario extends BukkitRunnable implements ScenarioConfigurable {
    @Override
    public void run() {
        if(ScenarioUtils.isScenarioEnabled(this)) {
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

    private static class Config extends ScenarioConfiguration implements RunnableRequiredConfig {
        @Getter
        private int time = 260;
    }

    @Getter
    private Config config = new Config();


    @Override
    public void setConfig(ScenarioConfiguration config) {
        this.config = (Config) config;
    }
}
