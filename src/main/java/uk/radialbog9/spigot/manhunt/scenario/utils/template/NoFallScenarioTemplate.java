/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.utils.template;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import uk.radialbog9.spigot.manhunt.scenario.utils.ScenarioUtils;

import java.util.List;

public abstract class NoFallScenarioTemplate implements Listener {
    public abstract List<Player> getPlayerSet();

    @EventHandler
    public void noFallScenario(EntityDamageEvent e) {
        if(
                ScenarioUtils.isScenarioEnabled(this) &&
                e.getEntityType() == EntityType.PLAYER
        ) {
            Player p = (Player) e.getEntity();
            if(getPlayerSet().contains(p) && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
            }
        }
    }
}
