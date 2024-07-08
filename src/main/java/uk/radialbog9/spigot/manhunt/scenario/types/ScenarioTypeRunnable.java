/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.types;

import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioUtils;

@ScenarioRunnable
public abstract class ScenarioTypeRunnable extends BukkitRunnable implements ScenarioBase {
    public Object getConfigValue(String key) {
        return ScenarioUtils.getConfigValue(this, key);
    }
}
