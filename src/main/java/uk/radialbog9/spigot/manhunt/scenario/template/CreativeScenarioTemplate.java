/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.template;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.scenario.types.ScenarioTypeRunnable;

import java.util.List;
import java.util.Map;

public abstract class CreativeScenarioTemplate extends ScenarioTypeRunnable {
    public abstract List<Player> getPlayerSet();

    @Override
    public Map<String, Object> getDefaultConfig() {
        return Map.of(
                "time", 300,
                "allow-fly", false,
                "duration", 5
        );
    }

    @Override
    public void run() {
        String scenarioName = this.getClass().getAnnotation(Scenario.class).value();
        if(ScenarioUtils.isScenarioEnabled(this)) {
            for(Player p : getPlayerSet()) {
                p.setGameMode(GameMode.CREATIVE);
                p.setAllowFlight((boolean) getConfigValue("allow-fly"));
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    for(Player p : GameManager.getGame().getHunters()) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.setAllowFlight(false);
                        p.setFlying(false);
                    }
                }
            }.runTaskLater(
                    Manhunt.getInstance(),
                    (int) getConfigValue("duration") * 20L
            );
        }
    }
}
