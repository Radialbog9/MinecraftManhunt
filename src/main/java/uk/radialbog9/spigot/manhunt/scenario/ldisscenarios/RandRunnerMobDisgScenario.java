/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.ldisscenarios;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.utils.DependencySupport;
import uk.radialbog9.spigot.manhunt.utils.LibsDisguisesUtils;

@Scenario("RUNNER_RANDOM_MOB_DISGUISE")
@ScenarioRunnable
@SuppressWarnings({"unused"})
public class RandRunnerMobDisgScenario extends BukkitRunnable {
    @Override
    public void run() {
        if(
                DependencySupport.isLibsDisguisesEnabled() &&
                GameManager.getGame().isGameStarted() &&
                GameManager.getGame().getActiveScenarios().contains("RUNNER_RANDOM_MOB_DISGUISE")
        ) {
            for(Player p : GameManager.getGame().getRunners()) {
                LibsDisguisesUtils.disguisePlayerRandomMob(p);
            }
        } else {
            this.cancel();
        }
    }
}
