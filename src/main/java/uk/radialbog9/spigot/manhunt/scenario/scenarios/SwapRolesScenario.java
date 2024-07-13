/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.scenario.config.RunnableRequiredConfig;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfigurable;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfiguration;

import java.util.ArrayList;
import java.util.List;

@Scenario("SWAP_ROLES")
@ScenarioRunnable
@SuppressWarnings("unused")
public class SwapRolesScenario extends BukkitRunnable implements ScenarioConfigurable {
    @Override
    public void run() {
        if(ScenarioUtils.isScenarioEnabled(this)) {
            // Create a copy of the hunters and runners list
            List<Player> hunters = new ArrayList<>(GameManager.getGame().getHunters());
            List<Player> runners = new ArrayList<>(GameManager.getGame().getRunners());

            hunters.forEach(player -> {
                GameManager.getGame().getHunters().remove(player);
                GameManager.getGame().getRunners().add(player);
            });

            runners.forEach(player -> {
                GameManager.getGame().getRunners().remove(player);
                GameManager.getGame().getHunters().add(player);
            });
        }
    }

    private static class Config extends ScenarioConfiguration implements RunnableRequiredConfig {
        @Getter
        private int time = 300;
    }

    @Getter
    private Config config = new Config();


    @Override
    public void setConfig(ScenarioConfiguration config) {
        this.config = (Config) config;
    }
}
