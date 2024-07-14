/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 *  You are allowed to use this code under the GPL3 license, which allows
 *  commercial use, distribution, modification, and licensed works,
 *  providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.config.RunnableRequiredConfig;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfigurable;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfiguration;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.List;

@Scenario("RANDOM_TP")
@ScenarioRunnable
public class RandomTpScenario extends BukkitRunnable implements ScenarioConfigurable {
    private static class Config extends ScenarioConfiguration implements RunnableRequiredConfig {
        @Getter
        private int time = 300;

        @Getter
        private int maxDistance = 1000;
    }

    @Getter
    private Config config = new Config();

    @Override
    public void setConfig(ScenarioConfiguration config) {
        this.config = (Config) config;
    }

    @Override
    public void run() {
        List<Player> players = GameManager.getGame().getPlayers();
        for (Player player : players) {
            // Generate random location
            int x = Utils.getRandomInt(-config.maxDistance, config.maxDistance);
            int z = Utils.getRandomInt(-config.maxDistance, config.maxDistance);

            // Find safest location to teleport to
            Location loc = new Location(player.getWorld(), x, 0, z);
            int y = player.getWorld().getHighestBlockYAt(loc) + 1;
            loc.setY(y);

            // Teleport player
            player.teleport(loc);
        }
    }
}
