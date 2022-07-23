/*
 * Copyright (c) 2020-2022 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioType;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;

@Scenario(ScenarioType.RUNNER_CREATIVE)
@ScenarioRunnable
@SuppressWarnings({"unused"})
public class RunnerCreativeScenario extends BukkitRunnable {
    @Override
    public void run() {
        if(
                ManhuntVars.isGameStarted() &&
                        ManhuntVars.getScenarioList().contains(ScenarioType.RUNNER_CREATIVE)
        ) {
            for(Player p : ManhuntVars.getRunners()) {
                p.setGameMode(GameMode.CREATIVE);
                p.setAllowFlight(Manhunt.getInstance().getConfig().getBoolean("scenarios.RUNNER_CREATIVE.allow-fly"));
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    for(Player p : ManhuntVars.getRunners()) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.setAllowFlight(false);
                        p.setFlying(false);
                    }
                }
            }.runTaskLater(Manhunt.getInstance(), Manhunt.getInstance().getConfig().getInt("scenarios.RUNNER_CREATIVE.duration") * 20L);
        } else {
            this.cancel();
        }
    }
}
