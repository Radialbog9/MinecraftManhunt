/*
 * Copyright (c) 2020-2022 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import uk.radialbog9.spigot.manhunt.scenario.*;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;

@Scenario(ScenarioType.HUNTER_NO_FALL)
@ScenarioListener
public class HunterNoFallScenario implements Listener {
    @EventHandler
    public void runnerNoFall(EntityDamageEvent e) {
        if(
                ManhuntVars.isGameStarted() &&
                e.getEntityType() == EntityType.PLAYER &&
                ManhuntVars.getScenarioList().contains(ScenarioType.HUNTER_NO_FALL)
        ) {
            Player p = (Player) e.getEntity();
            if(ManhuntVars.isHunter(p) && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
            }
        }
    }
}
